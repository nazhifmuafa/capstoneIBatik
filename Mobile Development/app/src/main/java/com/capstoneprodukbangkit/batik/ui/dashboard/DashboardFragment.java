package com.capstoneprodukbangkit.batik.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.adapter.dashboard.DynamicRvAdapter;
import com.capstoneprodukbangkit.batik.adapter.dashboard.StaticRvAdapter;
import com.capstoneprodukbangkit.batik.interfaces.UpdateRecyclerView;
import com.capstoneprodukbangkit.batik.model.DynamicRvModel;
import com.capstoneprodukbangkit.batik.model.StaticRvModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements UpdateRecyclerView {

    private RecyclerView recyclerView, recyclerView2;
    private StaticRvAdapter staticRvAdapter;

    ArrayList<DynamicRvModel> items = new ArrayList();
    DynamicRvAdapter dynamicRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);

        final ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.trending_icon, "Trending"));
        item.add(new StaticRvModel(R.drawable.teknik_pembuatan_icon, "Teknik"));
        item.add(new StaticRvModel(R.drawable.ciri_khas_icon, "Ciri Khas"));
        item.add(new StaticRvModel(R.drawable.jenis_kain_icon, "Jenis Kain"));

        recyclerView = root.findViewById(R.id.rv1);
        staticRvAdapter = new StaticRvAdapter(item, getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        items = new ArrayList<>();

        recyclerView2 = root.findViewById(R.id.rv2);
        dynamicRvAdapter = new DynamicRvAdapter(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(dynamicRvAdapter);


        return root;
    }

    @Override
    public void callback(int position, ArrayList<DynamicRvModel> items) {
        dynamicRvAdapter = new DynamicRvAdapter(items);
        dynamicRvAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRvAdapter);

    }
}
