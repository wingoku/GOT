package com.wingoku.gameofthrones.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wingoku.gameofthrones.R;
import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.models.House;
import com.wingoku.gameofthrones.domain.models.ItemDetails;
import com.wingoku.gameofthrones.domain.viewModels.HousesViewModel;
import com.wingoku.gameofthrones.presentation.activities.MainActivity;
import com.wingoku.gameofthrones.presentation.adapters.recyclerView.HouseListAdapter;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;

import java.util.LinkedHashMap;
import java.util.List;

import static com.wingoku.gameofthrones.utils.Utils.getFlagImageUrl;

public class HousesFragment extends BaseFragment {

    private static final String TAG = "HousesFragment";
    private NavController navController;
    private HouseListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Inside houses fragment");
        View view = inflater.inflate(R.layout.layout_recyclerview, container, false);
        initRecyclerView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);;
        ((MainActivity)requireActivity()).showToolbar();
        initSharedViewModel();
    }

    public void initSharedViewModel() {
        HousesViewModel houseViewModel = new ViewModelProvider(getActivity()).get(HousesViewModel.class);
        houseViewModel.getHouses().observe(getViewLifecycleOwner(), new Observer<Resource<List<House>>>() {
            @Override
            public void onChanged(Resource<List<House>> listResource) {
                if(listResource.status == Resource.Status.LOADING)
                    ((MainActivity)getActivity()).showProgressBar();
                else {
                    ((MainActivity)getActivity()).hideProgressBar();

                    if(listResource.status == Resource.Status.ERROR) {
                        showSnackBar(listResource.message);
                    }
                    else
                        adapter.submitList(listResource.data);
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        //passing item click listener to cat adapter cuz it's cleaner and best practice: https://stackoverflow.com/questions/49969278/recyclerview-item-click-listener-the-right-way
        adapter = new HouseListAdapter(getOnItemClickListener());
        recyclerView.setAdapter(adapter);
    }

    private OnItemClickListener<House> getOnItemClickListener() {
        return new OnItemClickListener<House>() {
            @Override
            public void onItemClick(House house) {
                navController.navigate(HousesFragmentDirections.actionHousesFragmentToItemDetailsFragment2(getHouseDetails(house)));
            }
        };
    }

    private ItemDetails getHouseDetails(House house) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Name", house.getName());
        map.put("Region", house.getRegion());
        map.put("Title", house.getTitle());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setDetailsMap(map);
        itemDetails.setImageUrl(getFlagImageUrl(house.getRegion()));

        return itemDetails;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}