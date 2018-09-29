package com.camthanh.vn.camthanhbook.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camthanh.vn.camthanhbook.R;
import com.camthanh.vn.camthanhbook.models.BookList;
import com.camthanh.vn.camthanhbook.models.Category;
import com.camthanh.vn.camthanhbook.models.CategoryRow;
import com.camthanh.vn.camthanhbook.utils.RowType;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = view.findViewById(R.id.home_recycler_view);
        BookList bookList = new BookList();
        bookList.setListType(RowType.COVER_ROW_TYPE);
        bookList.setListTitle("Cover");
        BookList bookList2 = new BookList();
        bookList2.setListType(RowType.NEW_ROW_TYPE);
        bookList2.setListTitle("New Book");
        BookList bookList3 = new BookList();
        bookList3.setListType(RowType.NEW_ROW_TYPE);
        bookList3.setListTitle("New Book");
        List<RowType> listData = new ArrayList<>();
        listData.add(bookList);
        listData.add(bookList2);
        listData.add(bookList3);
        String[] allNames = getContext().getResources().getStringArray(R.array.my_category);
        int[] allNum = getContext().getResources().getIntArray(R.array.number_top_category);
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < allNames.length; i++) {
            Category category = new Category();
            category.setCategoryDescription("description");
            category.setNumberOfBook(allNum[i]);
            category.setCategoryTitle(allNames[i]);
            categoryList.add(category);
        }
        CategoryRow categoryRow = new CategoryRow(categoryList,RowType.CATEGORY_FAVORITE_ROW_TYPE);
        listData.add(categoryRow);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(listData, getContext());
        recyclerView.setAdapter(homeAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
