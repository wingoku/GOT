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
import com.wingoku.gameofthrones.domain.models.Charactor;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;

public class CharacterListAdapter extends ListAdapter<Charactor, CharacterListAdapter.CharacterListRecyclerViewHolder> {

    private OnItemClickListener<Charactor> itemClickListener;

    public CharacterListAdapter(OnItemClickListener<Charactor> listener) {
        super(DIFF_CALLBACK);
        itemClickListener = listener;
    }

    @Override
    public CharacterListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_item_card, parent, false);

        return new CharacterListRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CharacterListRecyclerViewHolder holder, int position) {
        holder.bind(getItem(position), itemClickListener);
    }

    static class CharacterListRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageView;

        public CharacterListRecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final Charactor character, final OnItemClickListener<Charactor> listener) {
            nameTextView.setText(character.getName());
            Picasso.get().load(R.drawable.place_holder).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    listener.onItemClick(character);
                }
            });
        }
    }

    private static final DiffUtil.ItemCallback<Charactor> DIFF_CALLBACK = new DiffUtil.ItemCallback<Charactor>() {
        @Override
        public boolean areItemsTheSame(@NonNull Charactor oldItem, @NonNull Charactor newItem) {
            return oldItem.get_pid() == newItem.get_pid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Charactor oldItem, @NonNull Charactor newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };
}