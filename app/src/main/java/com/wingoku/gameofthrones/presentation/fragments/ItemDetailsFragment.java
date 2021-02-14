package com.wingoku.gameofthrones.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.wingoku.gameofthrones.R;
import com.wingoku.gameofthrones.domain.models.ItemDetails;
import com.wingoku.gameofthrones.presentation.activities.MainActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ItemDetailsFragment extends Fragment {

    private static final String TAG = "ItemDetailsFragment";
    private ItemDetails itemDetails;
    private List<Integer> textViewTitleIDMap;
    private List<Integer> textViewValueIDMap;
    private List<Integer> imageViewIDMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemDetails = (ItemDetails) ItemDetailsFragmentArgs.fromBundle(requireArguments()).getItemDetails();
        View view = inflater.inflate(R.layout.layout_fragment_item_details, container, false);
        initViewIdsList();
        populateUI(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)requireActivity()).hideToolbar();
    }

    private void initViewIdsList() {
        textViewTitleIDMap = new ArrayList<>();
        textViewValueIDMap = new ArrayList<>();
        imageViewIDMap = new ArrayList<>();

        textViewTitleIDMap.add(R.id.tv_title);
        textViewTitleIDMap.add(R.id.tv_description_title);
        textViewTitleIDMap.add(R.id.tv_description2_title);
        textViewTitleIDMap.add(R.id.tv_description3_title);
        textViewTitleIDMap.add(R.id.tv_description4_title);

        textViewValueIDMap.add(R.id.tv_title_value);
        textViewValueIDMap.add(R.id.tv_description_value);
        textViewValueIDMap.add(R.id.tv_description2_value);
        textViewValueIDMap.add(R.id.tv_description3_value);
        textViewValueIDMap.add(R.id.tv_description4_value);

        imageViewIDMap.add(R.id.imageView_1);
        imageViewIDMap.add(R.id.imageView_2);
        imageViewIDMap.add(R.id.imageView_3);
        imageViewIDMap.add(R.id.imageView_4);
        imageViewIDMap.add(R.id.imageView_5);
    }

    private void populateUI(View view) {
        int index = 0;
        LinkedHashMap<String, String> detailsMap = itemDetails.getDetailsMap();
        for(String key : detailsMap.keySet()) {

            if(index >= textViewTitleIDMap.size())
                break;


            TextView titleTextView = view.findViewById(textViewTitleIDMap.get(index));
            titleTextView.setText(key);

            TextView descTextView = view.findViewById(textViewValueIDMap.get(index));
            descTextView.setText(detailsMap.get(key));

            ImageView imageView = view.findViewById(imageViewIDMap.get(index));

            String imageUrl = itemDetails.getImageUrl();

            if(imageUrl == null || imageUrl.isEmpty())
                Picasso.get().load(R.drawable.place_holder).into(imageView);
            else
                Picasso.get().load(imageUrl).placeholder(R.drawable.place_holder).into(imageView);

            index++;
        }
    }
}