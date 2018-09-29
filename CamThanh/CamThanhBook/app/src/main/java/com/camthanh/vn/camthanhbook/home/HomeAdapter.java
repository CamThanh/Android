package com.camthanh.vn.camthanhbook.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.camthanh.vn.camthanhbook.R;
import com.camthanh.vn.camthanhbook.category.TopCategoryGrid;
import com.camthanh.vn.camthanhbook.demodata.DemoData;
import com.camthanh.vn.camthanhbook.models.BookList;
import com.camthanh.vn.camthanhbook.models.Category;
import com.camthanh.vn.camthanhbook.utils.RowType;

import java.util.List;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

public class HomeAdapter extends RecyclerView.Adapter {
    private List<RowType> mListData;
    private Context mContext;

    public HomeAdapter(List<RowType> listData, Context context) {
        mListData = listData;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (mListData != null) {
            RowType object = mListData.get(position);
            if (object != null) {
                return object.getItemViewType();
            }
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case RowType.COVER_ROW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cover_flow_books, parent, false);
                return new CoverBookRow(view);
            case RowType.NEW_ROW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_books_layout, parent, false);
                return new NewBookRow(view);
            case RowType.RECOMEND_ROW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_books_layout, parent, false);
                return new RecomendBookRow(view);
            case RowType.WEEKLY_ROW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_books_layout, parent, false);
                return new WeeklyBookRow(view);
            case RowType.CATEGORY_FAVORITE_ROW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_top, parent, false);
                return new TopCategoryGrid(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowType rowType = mListData.get(position);
        switch (rowType.getItemViewType()) {
            case RowType.COVER_ROW_TYPE:
                ((CoverBookRow) holder).setBooksData((BookList)rowType.getData(), mContext);
                break;
            case RowType.NEW_ROW_TYPE:
                ((NewBookRow) holder).setBooksData((BookList)rowType.getData(), mContext);
                ((NewBookRow) holder).imgContainer.setHasFixedSize(true);
                ((NewBookRow) holder).imgContainer.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                NewBookRow.NewBookAdapter adapter = new NewBookRow.NewBookAdapter();
                ((NewBookRow) holder).imgContainer.setAdapter(adapter);
                break;
            case RowType.RECOMEND_ROW_TYPE:
                ((RecomendBookRow) holder).setBooksData((BookList)rowType.getData(), mContext);
                break;
            case RowType.WEEKLY_ROW_TYPE:
                ((ViewOfBooks) holder).setBooksData((BookList)rowType.getData(), mContext);
                break;
            case RowType.CATEGORY_FAVORITE_ROW_TYPE:
                ((TopCategoryGrid) holder).setCategoryData((List<Category>) rowType.getData(),mContext);
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class CoverBookRow extends RecyclerView.ViewHolder implements ViewOfBooks {
        ViewPager pager;
        PagerContainer container;

        public CoverBookRow(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.pager_container);
            pager = container.getViewPager();
        }

        @Override
        public void setBooksData(BookList list, Context context) {
            pager.setAdapter(new CoverBookAdapter());
            pager.setClipChildren(false);
            //
            pager.setOffscreenPageLimit(15);

            container.setPageItemClickListener(new PageItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                }
            });

            boolean showTransformer = true;


            if (showTransformer) {

                new CoverFlow.Builder()
                        .with(pager)
                        .scale(0.3f)
                        .pagerMargin(context.getResources().getDimensionPixelSize(R.dimen.pager_margin))
                        .spaceSize(0f)
                        .build();

            }
        }

        public class CoverBookAdapter extends PagerAdapter {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_cover, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
                imageView.setImageDrawable(container.getContext().getResources().getDrawable(DemoData.covers[position]));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return DemoData.covers.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return (view == object);
            }
        }
    }

    public static class NewBookRow extends RecyclerView.ViewHolder implements ViewOfBooks {
        RecyclerView imgContainer;
        TextView layoutTitle;

        public NewBookRow(View itemView) {
            super(itemView);
            layoutTitle = itemView.findViewById(R.id.layoutTitle);
            imgContainer = itemView.findViewById(R.id.listView);
        }

        @Override
        public void setBooksData(BookList list, Context context) {
            layoutTitle.setText(list.getListTitle());
//            imgContainer.setAdapter(new NewBookAdapter());
        }

        public static class NewBookAdapter extends RecyclerView.Adapter<NewBookAdapter.ItemRowHolder> {

            @NonNull
            @Override
            public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, null);
                ItemRowHolder itemRowHolder = new ItemRowHolder(v);
                return itemRowHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {
                holder.image_view.setImageResource(DemoData.recomend[position]);
                holder.image_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }


            @Override
            public int getItemCount() {
                return DemoData.recomend.length;
            }

            public class ItemRowHolder extends RecyclerView.ViewHolder {
                ImageView image_view;
                TextView name;

                public ItemRowHolder(View itemView) {
                    super(itemView);
                    image_view = itemView.findViewById(R.id.image_view);
                    name = itemView.findViewById(R.id.name);
                }
            }
        }
    }

    public static class RecomendBookRow extends RecyclerView.ViewHolder implements ViewOfBooks {

        public RecomendBookRow(View itemView) {
            super(itemView);
        }

        @Override
        public void setBooksData(BookList list, Context context) {

        }
    }

    public static class WeeklyBookRow extends RecyclerView.ViewHolder implements ViewOfBooks {

        public WeeklyBookRow(View itemView) {
            super(itemView);
        }

        @Override
        public void setBooksData(BookList list, Context context) {

        }
    }
}
