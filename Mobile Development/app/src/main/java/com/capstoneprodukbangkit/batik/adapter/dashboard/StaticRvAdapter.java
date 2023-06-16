package com.capstoneprodukbangkit.batik.adapter.dashboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.interfaces.UpdateRecyclerView;
import com.capstoneprodukbangkit.batik.model.DynamicRvModel;
import com.capstoneprodukbangkit.batik.model.StaticRvModel;

import java.util.ArrayList;


public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder> {

    private final ArrayList<StaticRvModel> items;
    int row_index = -1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, final int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if (check){

            ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
            items.add(new DynamicRvModel("Batik Asana","Rekomendasi yang pertama untuk kemeja batik terbaik pria adalah batik Asana. Ini merupakan salah satu pilihan batik yang menyediakan banyak sekali pilihan, terutama untuk acara formal. Keunggulan lainnya dari Batik Asana adalah cocok untuk suasana formal maupun sehari-hari.", R.drawable.asana));
            items.add(new DynamicRvModel("Batik Agrapana","Rekomendasi lainnya untuk kemeja batik terbaik bagi pria adalah Batik Agrapana. Salah satu keunggulan dari produk ini adalah kombinasi warna dan corak yang elegan, sehingga menghasilkan produk yang sangat menarik.", R.drawable.agrapana));
            items.add(new DynamicRvModel("Batik Trusmi", "Pilihan lainnya untuk kemeja batik pria terbaik adalah Batik Trusmi. Ini merupakan batik lokal yang berasal dari Cirebon, ciri khas dari batik adalah motif mega mendung yang lengkap dan bisa Anda dapatkan dengan harga terjangkau.", R.drawable.trusmi));
            items.add(new DynamicRvModel("Batik Arjuna Weda", "Pilihan kemeja batik lainnya yang juga bisa Anda gunakan dalam berbagai kondisi adalah Batik Arjuna Weda. Merek batik ini sendiri sudah ada sejak tahun 1987 dan merupakan salah satu merk batik pertama yang dijual di berbagai department store di Indonesia.", R.drawable.arjuna_weda));
            items.add(new DynamicRvModel("Batik Danar Hadi", "Jika Anda mencari kemeja batik yang kualitasnya sudah tidak perlu diragukan, maka Batik Danar Hadi merupakan pilihan yang tepat. Batik ini sendiri sudah ada sejak 1967 dan sampai sekarang masih terus berinovasi.", R.drawable.danar_hadi));
            items.add(new DynamicRvModel("Batik Semar", "Rekomendasi lainnya untuk kemeja batik yang bisa menjadi pilihan Anda adalah Batik Semar. Nama brand ini tentu saja sudah tidak asing lagi bagi banyak orang. Brand ini sendiri sudah ada sejak tahun 1947. Salah satu keunggulan dari brand yang satu ini adalah koleksi kemeja batiknya yang cukup banyak dan juga berkualitas.", R.drawable.semar));
            items.add(new DynamicRvModel("Batik Keris", "Rekomendasi lainnya yang juga tidak boleh Anda lewatkan ketika memilih kemeja batik adalah Batik Keris. Ini juga merupakan salah satu brand batik yang sudah ada sejak tahun 90an.", R.drawable.keris));
            items.add(new DynamicRvModel("Batik Alleira", "Batik lokal namun memiliki kualitas internasional, ini mungkin hal yang bisa Anda sematkan pada Batik Alleira. Ini juga merupakan salah satu brand batik yang memiliki kualitas produk premium.", R.drawable.alleira));
            items.add(new DynamicRvModel("Batik Alisan", "Alisan sendiri memang sudah sangat terkenal dengan produk kemeja mereka yang berkualitas. Salah satu dari produk mereka yang berkualitas tersebut adalah kemeja batik pria terbaik yang bisa Anda gunakan.", R.drawable.alisan));
            items.add(new DynamicRvModel("Batik Hadinata", "Dengan desain yang sangat trendy dan juga mengusung banyak sekali warna cerah Inilah hal yang menjadi salah satu ciri khas dari Batik Hadinata. Banyak orang yang bilang jika produk dari Batik Hadinata membuat penggunanya menjadi terlihat awet muda.", R.drawable.hadinata));

            updateRecyclerView.callback(position, items);

            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if (position==0) {

                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Batik Asana","Rekomendasi yang pertama untuk kemeja batik terbaik pria adalah batik Asana. Ini merupakan salah satu pilihan batik yang menyediakan banyak sekali pilihan, terutama untuk acara formal. Keunggulan lainnya dari Batik Asana adalah cocok untuk suasana formal maupun sehari-hari.", R.drawable.asana));
                    items.add(new DynamicRvModel("Batik Agrapana","Rekomendasi lainnya untuk kemeja batik terbaik bagi pria adalah Batik Agrapana. Salah satu keunggulan dari produk ini adalah kombinasi warna dan corak yang elegan, sehingga menghasilkan produk yang sangat menarik.", R.drawable.agrapana));
                    items.add(new DynamicRvModel("Batik Trusmi", "Pilihan lainnya untuk kemeja batik pria terbaik adalah Batik Trusmi. Ini merupakan batik lokal yang berasal dari Cirebon, ciri khas dari batik adalah motif mega mendung yang lengkap dan bisa Anda dapatkan dengan harga terjangkau.", R.drawable.trusmi));
                    items.add(new DynamicRvModel("Batik Arjuna Weda", "Pilihan kemeja batik lainnya yang juga bisa Anda gunakan dalam berbagai kondisi adalah Batik Arjuna Weda. Merek batik ini sendiri sudah ada sejak tahun 1987 dan merupakan salah satu merk batik pertama yang dijual di berbagai department store di Indonesia.", R.drawable.arjuna_weda));
                    items.add(new DynamicRvModel("Batik Danar Hadi", "Jika Anda mencari kemeja batik yang kualitasnya sudah tidak perlu diragukan, maka Batik Danar Hadi merupakan pilihan yang tepat. Batik ini sendiri sudah ada sejak 1967 dan sampai sekarang masih terus berinovasi.", R.drawable.danar_hadi));
                    items.add(new DynamicRvModel("Batik Semar", "Rekomendasi lainnya untuk kemeja batik yang bisa menjadi pilihan Anda adalah Batik Semar. Nama brand ini tentu saja sudah tidak asing lagi bagi banyak orang. Brand ini sendiri sudah ada sejak tahun 1947. Salah satu keunggulan dari brand yang satu ini adalah koleksi kemeja batiknya yang cukup banyak dan juga berkualitas.", R.drawable.semar));
                    items.add(new DynamicRvModel("Batik Keris", "Rekomendasi lainnya yang juga tidak boleh Anda lewatkan ketika memilih kemeja batik adalah Batik Keris. Ini juga merupakan salah satu brand batik yang sudah ada sejak tahun 90an.", R.drawable.keris));
                    items.add(new DynamicRvModel("Batik Alleira", "Batik lokal namun memiliki kualitas internasional, ini mungkin hal yang bisa Anda sematkan pada Batik Alleira. Ini juga merupakan salah satu brand batik yang memiliki kualitas produk premium.", R.drawable.alleira));
                    items.add(new DynamicRvModel("Batik Alisan", "Alisan sendiri memang sudah sangat terkenal dengan produk kemeja mereka yang berkualitas. Salah satu dari produk mereka yang berkualitas tersebut adalah kemeja batik pria terbaik yang bisa Anda gunakan.", R.drawable.alisan));
                    items.add(new DynamicRvModel("Batik Hadinata", "Dengan desain yang sangat trendy dan juga mengusung banyak sekali warna cerah Inilah hal yang menjadi salah satu ciri khas dari Batik Hadinata. Banyak orang yang bilang jika produk dari Batik Hadinata membuat penggunanya menjadi terlihat awet muda.", R.drawable.hadinata));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==1) {

                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Batik Tulis", "Teknik pembuatan batik tulis atau canting adalah metode paling tua dan tradisional. Proses pembuatan batik masih menggunakan alat canting tradisional yang diisi dengan lilin panas sebelum digunakan untuk menggambar pola di atas kain.", R.drawable.tulis));
                    items.add(new DynamicRvModel("Batik Cap", "Teknik pembuatan batik cap muncul sekitar abad ke-20. Metode ini tidak menggunakan canting, melainkan cap yang terbuat dari tembaga berukuran 20 x 20 cm. Bagian tengah cap memiliki motif ukiran batik. Stempel akan dicelupkan ke dalam cairan malam lalu ditekan dengan keras di atas kain.", R.drawable.cap));
                    items.add(new DynamicRvModel("Batik Kombinasi", "Batik kombinasi adalah perpaduan antara batik tulis (canting) dengan batik cap. Teknik pembuatan batik ini diciptakan untuk menyempurnakan hasil batik cap yang hanya bisa membuat motif besar. Detail motif yang ukurannya lebih kecil kemudian ditambahkan menggunakan canting.", R.drawable.kombinasi));
                    items.add(new DynamicRvModel("Batik Ikat Celup", "Teknik pembuatan batik dengan metode ikat celup juga tergolong modern. Teknik ini banyak digunakan untuk membuat batik yang lebih berwarna-warni. Di Jawa teknik ini disebut Jumputan, di Palembang lebih dikenal dengan nama Cinde, sedangkan di Banjarmasin namanya Sasirangan.", R.drawable.ikat_celup));
                    items.add(new DynamicRvModel("Batik Lukis", "Teknik pembuatan batik tradisional hanya menghasilkan 1 – 2 warna saja. Namun berbeda dengan teknik pembuatan batik lukis atau colet. Dengan teknik ini, Anda bisa membuat batik beraneka warna. Teknik ini juga membutuhkan keterampilan seni yang tinggi. Semakin bagus hasilnya, maka semakin mahal harganya.", R.drawable.lukis));
                    items.add(new DynamicRvModel("Batik Printing", "Metode pembuatan batik printing adalah teknik yang paling modern dan paling banyak digunakan saat ini. Selain lebih cepat, proses pembuatan batik printing tidak membutuhkan keterampilan khusus. Oleh karena itu, teknik ini paling banyak digunakan oleh pemula untuk membuat banyak batik dalam waktu singkat.", R.drawable.printing));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==2) {

                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Batik Pedalaman", "Batik pedalaman merupakan batik tertua yang ada di Indonesia, biasanya berasal dari keraton atau sudah ada sejak zaman kerajaan. Disebut juga sebagai batik tradisional, Monique menjelaskan bahwa batik pedalaman memiliki ciri khas warna seperti putih, cokelat, dan biru.", R.drawable.pedalaman));
                    items.add(new DynamicRvModel("Batik Pesisir", "Kemudian ada batik pesisir yang motif hingga warnanya banyak terinspirasi oleh budaya asing yang datang ke Indonesia sejak zaman dahulu. Makanya, jenis batik yang satu ini lebih kaya dari segi warna, motif, hingga filosofi akulturasi budaya di baliknya.", R.drawable.pesisir));
                    items.add(new DynamicRvModel("Batik Nusantara", "Motif batik ini terinspirasi dari sejarah daerah asal perajin yang membuatnya, atau jika perajin tersebut merupakan seorang pendatang, biasanya ia akan membuat motif yang mengombinasikan budaya daerah asalnya dan daerah tempat tinggalnya saat ini.", R.drawable.nusantara));
                    items.add(new DynamicRvModel("Batik Kontemporer", "Terakhir ada batik kontemporer yang lebih modern dari ketiga jenis batik lainnya, sebab motifnya lebih variatif dan inovatif, mengikuti perkembangan zaman. Yang terakhir ada batik kontemporer di mana perkembangannya semakin ke sini banyak kreator-kreator motif batik yang jauh lebih inovatif dalam mengembangkan motif. Jadi tidak hanya mengadopsi motif-motif leluhur atau tradisional, tetapi juga membuat motif-motif sendiri,” tuturnya.", R.drawable.kontemporer));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==3) {

                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Kain Grey", "Kain grey biasa di katakan sebagai “bahan unfinish” yang tidak mengalami proses pemutihan sehingga warna alaminya masih tetap terjaga. Sebagai bahan dasar batik, kain grey ini kebanyakan dibuat dari tenunan banang kapas jadi sangat cocok digunakan sebagai busana wanita maupun aksesoris.", R.drawable.grey));
                    items.add(new DynamicRvModel("Kain Dobby", "Kain tenun doby yang biasa disebut juga sebagai kain tenun timbul pada dasarnya termasuk ke dalam jenis bahan kain yang diperoleh dari kombinasi antara katun dan polyester. Campuran bahan alami dan sintetis ini akan menghasilkan kain yang memiliki pola cukup menarik misalnya motif serat kotak, garis atau abstrak", R.drawable.dobby));
                    items.add(new DynamicRvModel("Kain Serat Nanas", "Kain serat nanas secara umum memiliki tekstur yang lumayan kasar mirip seperti kain dobi. Pemanfaatan serat nanas sebagai bahan dasar kain sendiri konon sudah dilakukan sejak lama bahkan sebelum kapas diolah menjadi kain katun. Disisi lain kain serat nanas pun sering digunakan sebagai bahan untuk membatik tho.", R.drawable.serat_nanas));
                    items.add(new DynamicRvModel("Kain Paris", "Kain paris untuk batik termasuk ke dalam jenis kain yang teksturnya lembut dan jatuh. Sepintas bahan kain paris ini memang tampak tipis, padahal kalau dilihat dari segi kekuatannya kain paris tergolong lumayan bagus jika dibandingkan dengan jenis kain untuk batik yang lainnya.", R.drawable.paris));
                    items.add(new DynamicRvModel("Kain Mori", "Kain mori merupakan sejenis kain berwarna putih polos yang memiliki ketebalan. kehalusan, dan kerapatan yang sempurna sehingga sangat sesuai jika digunakan untuk membatik. Jenis kain yang biasa disebut sebagai ‘cambric” pada dasarnya juga termasuk kedalam jenis kain tenun benang kapas yang dibuat dengan teknik anyaman polos dan diputinkan", R.drawable.mori));
                    items.add(new DynamicRvModel("Kain Rayon", "Kain rayon dapat dikategorikan ke dalam jenis kain hasil regenerasi serat selulosa yang sifatnya hampir menyerupai kapas tapi kekuatannya lebih rendah terutama terhadap zat alkali. Meski tingkat kekuatannya tidak sebagus kain katun, kain rayon ini tetap memiliki keistimewaan tersendiri lho.", R.drawable.rayon));
                    items.add(new DynamicRvModel("Kain Kaos Katun", "Kain kaos katun termasuk ke dalam jenis kain katun hasil rajutan yang biasa dibuat batik dalam bentuk produk kaos oblong atau T-shirt. Tidak jauh berbeda dengan teknik batik yang diterapkan pada kain katun ataupun kain mori, bahan kaos ini sebenarnya juga bisa di batik dengan teknik batik tulis, teknik batik cap dan teknik batik tulis.", R.drawable.kaos_katun));
                    items.add(new DynamicRvModel("Kain Sutra", "Kain sutra termasuk ke dalam jenis kain yang memiliki sifat sangat fleksibel, seratnya tidak mudah robek, lembut dan halus seta nyaman di kulit. Dengan karakteristiknya yang begitu unik. kain sutra ini sangat cocok jika digunakan untuk membuat kain batik yang berkualitas tinggi dan memiliki keistimewaan tersendiri.", R.drawable.sutra));
                    items.add(new DynamicRvModel("Kain Poliester", "Kain poliester merupakan bahan dasar batik yang berasal dari keluarga kain serat sintetis. Karena dibuat dari serat sintetis maka kain ini secara keseluruhan mempunyai sifat tahan kusut, tahan asam. tahan terhadap reaksi kimia, tahan terhadap jamur dan mempunyai daya serap terhadap air yang lemah.", R.drawable.poliester));

                    updateRecyclerView.callback(position, items);

                }
            }
        });

        if (select) {
            if (position==0)
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            select=false;
        }
        else{
            if (row_index == position) {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            } else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_item);
            textView = itemView.findViewById(R.id.text_item);
            linearLayout = itemView.findViewById(R.id.LinearLayout);
        }

    }

}
