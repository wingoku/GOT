package com.wingoku.gameofthrones.presentation.adapters.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wingoku.gameofthrones.R;
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;

public class CategoryListAdapter extends ListAdapter<Category, CategoryListAdapter.CategoryListRecyclerViewHolder> {

    private OnItemClickListener<Category> itemClickListener;

    public CategoryListAdapter(OnItemClickListener<Category> listener) {
        super(DIFF_CALLBACK);
        itemClickListener = listener;
    }

    @Override
    public CategoryListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_item_card, parent, false);

        return new CategoryListRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryListRecyclerViewHolder holder, int position) {
        holder.bind(getItem(position), itemClickListener);
    }

    static class CategoryListRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView catName;
        ImageView imageView;

        public CategoryListRecyclerViewHolder(View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final Category category, final OnItemClickListener<Category> listener) {
            catName.setText(category.categoryName);
            Picasso.get().load(R.drawable.place_holder).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    listener.onItemClick(category);
                }
            });
        }
    }

    private static final DiffUtil.ItemCallback<Category> DIFF_CALLBACK = new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.get_pid() == newItem.get_pid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getCategoryName().equals(newItem.getCategoryName());
        }
    };
}