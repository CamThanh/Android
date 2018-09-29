package com.camthanh.vn.camthanhbook.category;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.camthanh.vn.camthanhbook.R;
import com.camthanh.vn.camthanhbook.demodata.DemoData;
import com.camthanh.vn.camthanhbook.models.Category;

import java.util.List;

public class CategoryTopAdapter extends RecyclerView.Adapter<CategoryTopAdapter.CategoryTopItemHolders> {
    List<Category> mList;

    public CategoryTopAdapter(List<Category> list) {
        mList = list;
    }

    @NonNull
    @Override
    public CategoryTopAdapter.CategoryTopItemHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_top_item, null);
        CategoryTopAdapter.CategoryTopItemHolders itemRowHolder = new CategoryTopAdapter.CategoryTopItemHolders(v);
        return itemRowHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryTopItemHolders holder, int position) {
        holder.categoryName.setText(mList.get(position).getCategoryTitle());
        holder.categoryBooksNumber.setText(mList.get(position).getNumberOfBook() + "");
        holder.layout.setBackgroundColor(Color.parseColor(DemoData.categoryBg[position]));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CategoryTopItemHolders extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        public ConstraintLayout layout;
        public TextView categoryName;
        public TextView categoryBooksNumber;

        public CategoryTopItemHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryBooksNumber = itemView.findViewById(R.id.category_bookNum);
            layout = itemView.findViewById(R.id.containerTopItem);
        }

        @Override
        public void onClick(View view) {
        }
    }
}