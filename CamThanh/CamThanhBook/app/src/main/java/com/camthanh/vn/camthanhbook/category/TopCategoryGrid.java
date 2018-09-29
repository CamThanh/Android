package com.camthanh.vn.camthanhbook.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.camthanh.vn.camthanhbook.R;
import com.camthanh.vn.camthanhbook.demodata.DemoData;
import com.camthanh.vn.camthanhbook.models.Category;

import java.util.List;

public class TopCategoryGrid extends RecyclerView.ViewHolder implements ViewOfCategory {
    private final RecyclerView mRecyclerView;
    private CategoryTopAdapter mAdapter;
    public Context mContext;

    public TopCategoryGrid(View itemView) {
        super(itemView);
        mRecyclerView = itemView.findViewById(R.id.category_hot);
    }

    @Override
    public void setCategoryData(List<Category> list, Context context) {
        mRecyclerView.setHasFixedSize(true);
        mContext = context;
        GridLayoutManager glm = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(glm);
        mAdapter = new CategoryTopAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }


}
