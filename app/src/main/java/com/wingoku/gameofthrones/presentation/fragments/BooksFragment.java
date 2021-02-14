package com.wingoku.gameofthrones.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wingoku.gameofthrones.R;
import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.models.Book;
import com.wingoku.gameofthrones.domain.models.ItemDetails;
import com.wingoku.gameofthrones.domain.viewModels.BooksViewModel;
import com.wingoku.gameofthrones.presentation.activities.MainActivity;
import com.wingoku.gameofthrones.presentation.adapters.recyclerView.BookListAdapter;
import com.wingoku.gameofthrones.presentation.interfaces.OnItemClickListener;

import java.util.LinkedHashMap;
import java.util.List;

public class BooksFragment extends Fragment {

    private static final String TAG = "BooksFragment";
    private NavController navController;
    private BookListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Inside books fragment");
        View view = inflater.inflate(R.layout.layout_recyclerview, container, false);
        initRecyclerView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)requireActivity()).showToolbar();
        initSharedViewModel();
    }

    public void initSharedViewModel() {
        BooksViewModel booksViewModel = new ViewModelProvider(getActivity()).get(BooksViewModel.class);
        booksViewModel.getBooks().observe(getViewLifecycleOwner(), new Observer<Resource<List<Book>>>() {
            @Override
            public void onChanged(Resource<List<Book>> listResource) {

                if(listResource.status == Resource.Status.LOADING)
                    ((MainActivity)getActivity()).showProgressBar();
                else {
                    ((MainActivity)getActivity()).hideProgressBar();
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
        adapter = new BookListAdapter(getOnItemClickListener());
        recyclerView.setAdapter(adapter);
    }

    private OnItemClickListener<Book> getOnItemClickListener() {
        return new OnItemClickListener<Book>() {
            @Override
            public void onItemClick(Book book) {
                //;

                navController.navigate(BooksFragmentDirections.actionBooksFragmentToItemDetailsFragment(getBookDetails(book)));
            }
        };
    }

    private ItemDetails getBookDetails(Book book) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Name", book.getName());
        map.put("Page Count", String.valueOf(book.getNumberOfPages()));
        map.put("Country", book.getCountry());
        map.put("Release Date", book.getReleased());

        StringBuilder sb = new StringBuilder();
        for(String author : book.getAuthors()) {
            sb.append(author).append(", ");
        }

        if(sb.length() > 0)
            sb.setLength(sb.length()-1);

        map.put("Author(s)", sb.toString());

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