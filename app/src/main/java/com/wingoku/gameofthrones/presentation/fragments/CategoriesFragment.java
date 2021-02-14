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
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.domain.viewModels.SharedViewModel;
import com.wingoku.gameofthrones.presentation.activities.MainActivity;
import com.wingoku.gameofthrones.presentation.adapters.recyclerView.CategoryListAdapter;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;

import java.util.List;

public class CategoriesFragment extends BaseFragment {

    private static final String TAG = "CategoriesFragment";
    private NavController navController;
    private CategoryListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        SharedViewModel sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getCategoryData().observe(getViewLifecycleOwner(), new Observer<Resource<List<Category>>>() {
            @Override
            public void onChanged(Resource<List<Category>> listResource) {
                Log.d(TAG, "onChanged: setting cat list in adapter");
                MainActivity activity = ((MainActivity)getActivity());
                if(listResource.status == Resource.Status.LOADING) {
                    if(activity != null)
                        activity.showProgressBar();
                }
                else {
                    if(activity != null)
                        activity.hideProgressBar();
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
        adapter = new CategoryListAdapter(getOnItemClickListener());
        recyclerView.setAdapter(adapter);
    }

    private OnItemClickListener<Category> getOnItemClickListener() {
        return new OnItemClickListener<Category>() {
            @Override
            public void onItemClick(Category item) {
                switch (item.getType()) {
                    case 0:
                        navController.navigate(CategoriesFragmentDirections.actionCategoriesFragmentToBooksFragment2());
                        break;
                    case 1:
                        navController.navigate(CategoriesFragmentDirections.actionCategoriesFragmentToHousesFragment());
                        break;
                    case 2:
                        navController.navigate(CategoriesFragmentDirections.actionCategoriesFragmentToCharactersFragment2());
                        break;
                }
            }
        };
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}