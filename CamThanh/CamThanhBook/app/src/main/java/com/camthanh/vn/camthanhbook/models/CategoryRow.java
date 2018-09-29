package com.camthanh.vn.camthanhbook.models;

import android.support.v7.widget.RecyclerView;

import com.camthanh.vn.camthanhbook.utils.RowType;

import java.util.List;

public class CategoryRow implements RowType {
    List<Category> categories;
    int rowType;

    public CategoryRow(List<Category> categories, int rowType) {
        this.categories = categories;
        this.rowType = rowType;
    }

    @Override

    public int getItemViewType() {
        return rowType;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public Object getData() {
        return categories;
    }


}
