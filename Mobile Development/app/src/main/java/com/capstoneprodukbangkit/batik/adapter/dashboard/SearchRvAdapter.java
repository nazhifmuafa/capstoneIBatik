package com.capstoneprodukbangkit.batik.adapter.dashboard;

import android.app.Activity;
import android.content.Context;
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
import com.capstoneprodukbangkit.batik.model.SearchRvModel;
import com.capstoneprodukbangkit.batik.model.StaticRvModel;

import java.util.ArrayList;
import java.util.List;

public class SearchRvAdapter extends RecyclerView.Adapter<SearchRvAdapter.SearchRvViewHolder> {

    private final ArrayList<SearchRvModel> items;
    Activity activity;

    public SearchRvAdapter(ArrayList<SearchRvModel> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SearchRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_search, parent, false);
        SearchRvViewHolder searchRvViewHolder = new SearchRvAdapter.SearchRvViewHolder(view);
        return searchRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRvViewHolder holder, final int position) {

        SearchRvModel currentItem = items.get(position);
        holder.batikImage.setImageResource(currentItem.getBatikImage());
        holder.batikName.setText(currentItem.getBatikName());
        holder.batikLocation.setText(currentItem.getBatikName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SearchRvViewHolder extends RecyclerView.ViewHolder {

        ImageView batikImage;
        TextView batikName;
        TextView batikLocation;
        LinearLayout linearLayout;

        public SearchRvViewHolder(@NonNull View itemView) {
            super(itemView);
            batikImage = itemView.findViewById(R.id.img_search);
            batikName = itemView.findViewById(R.id.batik_named);
            batikLocation = itemView.findViewById(R.id.batik_lc);
            linearLayout = itemView.findViewById(R.id.rv_search);
        }
    }
}
