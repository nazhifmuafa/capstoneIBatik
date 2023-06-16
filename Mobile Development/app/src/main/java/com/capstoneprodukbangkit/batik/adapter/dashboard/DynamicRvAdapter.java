package com.capstoneprodukbangkit.batik.adapter.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.model.DynamicRvModel;

import java.util.ArrayList;

public class DynamicRvAdapter extends RecyclerView.Adapter<DynamicRvAdapter.DynamicRvHolder> {

    public ArrayList<DynamicRvModel> dynamicRvModels;
    private OnItemClickListener mvListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mvListener)  {
        this.mvListener = mvListener;
    }

    public DynamicRvAdapter(ArrayList<DynamicRvModel> dynamicRvModels) {
        this.dynamicRvModels = dynamicRvModels;
    }

    public class DynamicRvHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public TextView textDetail;
        ConstraintLayout constraintLayout;

        public DynamicRvHolder(@NonNull View itemView, final OnItemClickListener mvListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);
            textDetail = itemView.findViewById(R.id.details);
            constraintLayout = itemView.findViewById(R.id.constraintLayout2);

            itemView.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    if (mvListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mvListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public DynamicRvAdapter.DynamicRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item_layout,parent,false);
        DynamicRvHolder dynamicRvHolder = new DynamicRvHolder(view, mvListener);
        return dynamicRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRvAdapter.DynamicRvHolder holder, int position) {
        DynamicRvModel currentItem = dynamicRvModels.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textDetail.setText(currentItem.getDetail());
        holder.textView.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return dynamicRvModels.size();
    }
}

