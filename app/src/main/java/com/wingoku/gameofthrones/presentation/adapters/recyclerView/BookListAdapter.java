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
import com.wingoku.gameofthrones.domain.models.Book;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;

public class BookListAdapter extends ListAdapter<Book, BookListAdapter.BookListRecyclerViewHolder> {

    private OnItemClickListener<Book> itemClickListener;

    public BookListAdapter(OnItemClickListener<Book> listener) {
        super(DIFF_CALLBACK);
        itemClickListener = listener;
    }

    @Override
    public BookListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_item_card, parent, false);

        return new BookListRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookListRecyclerViewHolder holder, int position) {
        holder.bind(getItem(position), itemClickListener);
    }

    static class BookListRecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView;

        public BookListRecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final Book book, final OnItemClickListener<Book> listener) {
            nameTextView.setText(book.getName());
            Picasso.get().load(R.drawable.place_holder).into(imageView);
            itemView.setOnClickListener(v -> {

                listener.onItemClick(book);
            });
        }
    }

    private static final DiffUtil.ItemCallback<Book> DIFF_CALLBACK = new DiffUtil.ItemCallback<Book>() {
        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.get_pid() == newItem.get_pid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };
}