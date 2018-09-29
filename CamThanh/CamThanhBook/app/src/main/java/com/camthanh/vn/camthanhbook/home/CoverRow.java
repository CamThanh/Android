package com.camthanh.vn.camthanhbook.home;

import android.support.v7.widget.RecyclerView;

import com.camthanh.vn.camthanhbook.utils.RowType;

public class CoverRow implements RowType {
    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public Object getData() {
        return null;
    }
}
