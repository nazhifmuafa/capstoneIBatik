package com.capstoneprodukbangkit.batik.interfaces;

import com.capstoneprodukbangkit.batik.model.DynamicRvModel;

import java.util.ArrayList;

public interface UpdateRecyclerView {
    public void callback(int position, ArrayList<DynamicRvModel> items);
}
