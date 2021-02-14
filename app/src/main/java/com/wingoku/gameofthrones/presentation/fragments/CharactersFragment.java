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
import com.wingoku.gameofthrones.domain.models.Charactor;
import com.wingoku.gameofthrones.domain.models.ItemDetails;
import com.wingoku.gameofthrones.domain.viewModels.CharactersViewModel;
import com.wingoku.gameofthrones.presentation.activities.MainActivity;
import com.wingoku.gameofthrones.presentation.adapters.recyclerView.CharacterListAdapter;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;

import java.util.LinkedHashMap;
import java.util.List;

public class CharactersFragment extends BaseFragment {

    private static final String TAG = "CharactersFragment";
    private NavController navController;
    private CharacterListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Inside CharactersFragment");
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
        CharactersViewModel charactersViewModel = new ViewModelProvider(getActivity()).get(CharactersViewModel.class);
        charactersViewModel.getCharacters().observe(getViewLifecycleOwner(), new Observer<Resource<List<Charactor>>>() {
            @Override
            public void onChanged(Resource<List<Charactor>> listResource) {
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
        adapter = new CharacterListAdapter(getOnItemClickListener());
        recyclerView.setAdapter(adapter);
    }

    private OnItemClickListener<Charactor> getOnItemClickListener() {
        return new OnItemClickListener<Charactor>() {
            @Override
            public void onItemClick(Charactor character) {
                navController.navigate(CharactersFragmentDirections.actionCharactersFragmentToItemDetailsFragment(getCharacterDetails(character)));
            }
        };
    }

    private ItemDetails getCharacterDetails(Charactor character) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Name", character.getName());
        map.put("Gender", character.getGender());
        map.put("Father", character.getFather());
        map.put("Mother", character.getMother());

        StringBuilder sb = new StringBuilder();
        for(String player : character.getPlayedBy()) {
            sb.append(player).append(", ");
        }

        if(sb.length() > 0)
            sb.setLength(sb.length()-1);

        map.put("Player(s)", sb.toString());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setDetailsMap(map);

        return itemDetails;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}