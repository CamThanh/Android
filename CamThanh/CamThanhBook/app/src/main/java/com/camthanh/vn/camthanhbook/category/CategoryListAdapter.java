package com.camthanh.vn.camthanhbook.category;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.camthanh.vn.camthanhbook.R;
import com.camthanh.vn.camthanhbook.models.Category;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryItemHolders> {
    List<Category> mList;

    public CategoryListAdapter(List<Category> list) {
        mList = list;
    }

    @NonNull
    @Override
    public CategoryItemHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, null);
        CategoryItemHolders itemRowHolder = new CategoryItemHolders(v);
        return itemRowHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemHolders holder, int position) {
        holder.categoryName.setText(mList.get(position).getCategoryTitle());
        holder.categoryBooksNumber.setText(mList.get(position).getNumberOfBook()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CategoryItemHolders extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        public ConstraintLayout layout;
        public TextView categoryName;
        public TextView categoryBooksNumber;

        public CategoryItemHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryBooksNumber = itemView.findViewById(R.id.category_bookNum);
            layout = itemView.findViewById(R.id.container);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
