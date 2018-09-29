package com.camthanh.vn.camthanhbook.category;

import android.content.Context;

import com.camthanh.vn.camthanhbook.models.Category;

import java.util.List;

public interface ViewOfCategory {
    void setCategoryData(List<Category> list, Context context);
}
