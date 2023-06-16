package com.capstoneprodukbangkit.batik.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.GridLayout;

import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.adapter.dashboard.SearchRvAdapter;
import com.capstoneprodukbangkit.batik.model.SearchRvModel;
import com.capstoneprodukbangkit.batik.model.StaticRvModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Integer> batikImage;
    List<String> batikName;
    List<String> batikLocation;
    SearchRvAdapter searchRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);

        ArrayList<SearchRvModel> item = new ArrayList<>();
        item.add(new SearchRvModel(R.drawable.lok_chan, "Lok Chan", "Tuban"));
        item.add(new SearchRvModel(R.drawable.gajah_oling, "Gajah Oling", "Banyuwangi"));



        recyclerView = findViewById(R.id.rv_search);

        searchRvAdapter = new SearchRvAdapter(item, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchRvAdapter);

    }
}