package com.camthanh.vn.camthanhbook.utils;

import android.support.v7.widget.RecyclerView;

public interface RowType {
    int COVER_ROW_TYPE = 0;
    int NEW_ROW_TYPE = 1;
    int RECOMEND_ROW_TYPE = 2;
    int WEEKLY_ROW_TYPE = 3;
    int CATEGORY_FAVORITE_ROW_TYPE = 4;

    int getItemViewType();

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder);
    Object getData();
}
