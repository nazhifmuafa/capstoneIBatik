import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
from PIL import Image
import io
import json
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

app = Flask(__name__)

cred = credentials.Certificate("./serviceAccountKey.json")
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://capstoneproduct23.firebaseio.com/'
})

deskripsi_batik_ref = db.reference('dataBatik')
predict_ref = db.reference('predict')

# Load the saved model
model_path = 'batik.h5'
model = tf.keras.models.load_model(model_path)

# Preprocess image function
def preprocess_image(image):
    img = image.resize((224, 224))
    img_array = tf.keras.preprocessing.image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    preprocessed_img = tf.keras.applications.mobilenet_v2.preprocess_input(img_array)
    return preprocessed_img

# Predict function
def predict_image(image):
    preprocessed_img = preprocess_image(image)
    prediction = model.predict(preprocessed_img)
    predicted_class = np.argmax(prediction)
    similarity_score = np.max(prediction) * 100  # Persentase kesamaan dengan motif
    return predicted_class, similarity_score

@app.route('/predict', methods=['POST'])
def predict():
    file = request.files['file']
    if file is None or file.filename == "":
        return jsonify({'error': 'no file'})

    image_bytes = file.read()
    image = Image.open(io.BytesIO(image_bytes))
    image = image.resize((224, 224), Image.NEAREST)

    # Predict the class index and similarity score using the loaded model
    class_index, similarity_score = predict_image(image)

    # Get the class name based on the predicted index
    label = ["Betawi", "Celup", "Parang", "Megamendung", "Kawung"]
    class_name = label[class_index]

    # Get the batik data based on the predicted class
    deskripsi_batik = deskripsi_batik_ref.order_by_child('nama').equal_to(class_name).get()

    # Generate unique ID for the prediction data
    unique_id = predict_ref.push().key

    # Store the prediction data in Firebase Realtime Database
    predict_ref.child(unique_id).set({
        'nama_batik': class_name,
        'kemiripan_gambar': similarity_score,
        'deskripsi_batik': deskripsi_batik
    })

    response = {
        'unique_id': unique_id,
        'nama_batik': class_name,
        'kemiripan_gambar': similarity_score,
        'deskripsi_batik': deskripsi_batik
    }

    return jsonify(response)

@app.route('/predict/<unique_id>', methods=['GET'])
def get_prediction(unique_id):
    # Retrieve the prediction data from Firebase Realtime Database
    prediction = predict_ref.child(unique_id).get()

    if prediction is None:
        return jsonify({'error': 'prediction not found'})

    return jsonify(prediction)

@app.route('/predict/<unique_id>', methods=['DELETE'])
def get_prediction_delete(unique_id):
    # Retrieve the prediction data from Firebase Realtime Database
    prediction = predict_ref.child(unique_id).get()

    if prediction is None:
        return jsonify({'error': 'prediction not found'})

    # Delete the prediction entry from Firebase Realtime Database
    predict_ref.child(unique_id).delete()

    return jsonify({'message': 'prediction deleted successfully'})


if __name__ == '__main__':
    app.run(debug=True)
