package com.camthanh.vn.camthanhbook.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camthanh.vn.camthanhbook.R;
import com.camthanh.vn.camthanhbook.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    RecyclerView listCategory;
    RecyclerView mRecyclerGrid;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        //List Category
        listCategory = view.findViewById(R.id.listCategory);
        String[] allNames = getContext().getResources().getStringArray(R.array.list_category);
        int[] allNum = getContext().getResources().getIntArray(R.array.number_books_category_list);
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < allNames.length; i++) {
            Category category = new Category();
            category.setCategoryDescription("description");
            category.setNumberOfBook(allNum[i]);
            category.setCategoryTitle(allNames[i]);
            categoryList.add(category);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listCategory.setLayoutManager(linearLayoutManager);
        CategoryListAdapter adapter = new CategoryListAdapter(categoryList);
        listCategory.setAdapter(adapter);

        // Add Top Category
        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        mRecyclerGrid = view.findViewById(R.id.category_hot);
        mRecyclerGrid.setLayoutManager(glm);
        String[] allHotCat = getContext().getResources().getStringArray(R.array.my_category);
        int[] allNumHotCat = getContext().getResources().getIntArray(R.array.number_top_category);
        List<Category> listTopCat = new ArrayList<>();
        for (int i = 0; i < allHotCat.length; i++) {
            Category category = new Category();
            category.setCategoryDescription("description");
            category.setNumberOfBook(allNumHotCat[i]);
            category.setCategoryTitle(allHotCat[i]);
            listTopCat.add(category);
        }
        CategoryTopAdapter mTopAdapter = new CategoryTopAdapter(listTopCat);
        mRecyclerGrid.setItemViewCacheSize(0);
        mRecyclerGrid.setAdapter(mTopAdapter);
        return view;
    }
}
