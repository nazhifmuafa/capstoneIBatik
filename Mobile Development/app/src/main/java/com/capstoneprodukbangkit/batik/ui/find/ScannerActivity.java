package com.capstoneprodukbangkit.batik.ui.find;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.ml.TfliteModel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ScannerActivity extends AppCompatActivity {

    Button snap, upload;
    ImageView imageView;
    TextView result;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scanner);

        snap = findViewById(R.id.select_btn);
        upload = findViewById(R.id.uploadu_btn);
        imageView = findViewById(R.id.img_upload);
        result = findViewById(R.id.upload_output);

        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 3);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                    }
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void scanPattern(Bitmap image) {

        try {
            TfliteModel model = TfliteModel.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intOutput = new int[imageSize * imageSize];
            image.getPixels(intOutput, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;

            for (int i = 0; i < imageSize; i ++){
                for (int j = 0; j < imageSize; j++){
                    int val = intOutput[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val & 0xFF) * (1.f / 1)));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            TfliteModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();

            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++){
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Batik Celup \n\nBatik kawung dapat disebut dengan motifnya yang begitu geometris yang berjajar dengan rapi. Batik ini termasuk salah satu motif batik yang tertua ditemukan di daerah jawa. Motif batik tersebut lebih tepatnya yang berasal dari kota Yogyakarta. Contoh gambar motif batik kawung sangat sederhana yang dimana batik kawung ini mempunyai cerita atau sejarah dan filosofi tersendiri. Batik kawung sudah di bagusin menjadi berbagai macam produk yang terutama produk pakaian. Dikarenakan motifnya yang begitu unik, batik tersebut sering digunakan untuk kombinasi pondasi baju oleh para seorang desainer yang terkenal.", "Batik Parang \n\nMotif batik dengan pola geometris yang terinspirasi dari bentuk parang atau golok.", "Batik Betawi \n\nMotif batik khas Betawi yang menggambarkan kekayaan budaya dan sejarah Jakarta.", "Batik Megamendung \n\nMega mendung menjadi salah satu motif batik khas Cirebon, Jawa Barat, yang populer di kalangan wisatawan berkat bentuknya dan perpaduan warnanya yang unik.  Di tangan para perajin batik, lahirlah beragam kreatifitas warna-warna lembut dari motif mega mendung ini. Motif batik Mega Mendung cukup sederhana namun memberi kesan mewah. Motif mendung di langit mega yang berwarna cerah inilah yang membuat batik Mega Mendung sangat cocok dipakai orang tua maupun anak muda, baik perempuan maupun laki-laki.", "Batik-Kawung \n\nMotif batik kuno yang terdiri dari lingkaran-lingkaran dan melambangkan keindahan dan kebesaran."};
            result.setText(classes[maxPos]);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == 3) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                scanPattern(image);
            } else {
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                scanPattern(image);
            }
            }
    }
}