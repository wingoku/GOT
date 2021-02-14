package com.wingoku.gameofthrones.presentation.adapters.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wingoku.gameofthrones.R;
import com.wingoku.gameofthrones.domain.models.House;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;
import com.wingoku.gameofthrones.utils.Utils;

public class HouseListAdapter extends ListAdapter<House, HouseListAdapter.HouseListRecyclerViewHolder> {
    private OnItemClickListener<House> itemClickListener;

    public HouseListAdapter(OnItemClickListener<House>  listener) {
        super(DIFF_CALLBACK);
        itemClickListener = listener;
    }

    @Override
    public HouseListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_item_card, parent, false);

        return new HouseListRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HouseListRecyclerViewHolder holder, int position) {
        holder.bind(getItem(position), itemClickListener);

    }

    static class HouseListRecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView catImage;
        TextView catName;
        TextView description;
        CardView cardView;

        public HouseListRecyclerViewHolder(View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.tv_name);
            catImage = itemView.findViewById(R.id.imageView);
            description = itemView.findViewById(R.id.tv_desc);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void bind(final House house, final OnItemClickListener<House> listener) {
            catName.setText(house.getName());
            description.setText(house.getRegion());
            String imageUrl = Utils.getFlagImageUrl(house.getRegion());

            if(!imageUrl.isEmpty())
                Picasso.get().load(imageUrl).placeholder(R.drawable.place_holder).into(catImage);
            else
                Picasso.get().load(R.drawable.place_holder).into(catImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    listener.onItemClick(house);
                }
            });
        }
    }

    private static final DiffUtil.ItemCallback<House> DIFF_CALLBACK = new DiffUtil.ItemCallback<House>() {
        @Override
        public boolean areItemsTheSame(@NonNull House oldItem, @NonNull House newItem) {
            return oldItem.get_pid() == newItem.get_pid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull House oldItem, @NonNull House newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };
}