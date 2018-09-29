package com.camthanh.vn.camthanhbook.models;

import android.support.v7.widget.RecyclerView;

import com.camthanh.vn.camthanhbook.utils.RowType;

import java.util.List;

public class BookList implements RowType {
    String listTitle;
    int listType;
    String listDescription;
    List<Book> listBook;

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    @Override
    public int getItemViewType() {
        return listType;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
    }

    @Override
    public Object getData() {
        return this;
    }
}
