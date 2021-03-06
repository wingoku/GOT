package com.wingoku.gameofthrones.presentation.fragments;

import android.os.Bundle;
import android.os.Handler;
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

import com.airbnb.lottie.LottieAnimationView;
import com.wingoku.gameofthrones.R;
import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.domain.viewModels.SharedViewModel;
import com.wingoku.gameofthrones.presentation.activities.MainActivity;

import java.io.IOException;
import java.util.List;

public class SplashScreenFragment extends Fragment {
    private static final String TAG = "SplashScreenFragment";

    private SharedViewModel sharedViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_activity_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: inside SPLASH FRAGMENT");
        try {
            loadSplashAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)requireActivity()).hideToolbar();

        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.returnLiveData().observe(getViewLifecycleOwner(), new Observer<Resource<List<Category>>>() {
            @Override
            public void onChanged(Resource<List<Category>> listResource) {


                //cuz we don't want to navigate to the next fragment when Resource.class status is LOADING/
                if(listResource.status != Resource.Status.LOADING) {
                    sharedViewModel.setCategoryData(listResource);
                    navigateToCategoryFragment();
                }
            }
        });

    }

    private void navigateToCategoryFragment() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: calling navigation controller");
                navController.navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToCategoriesFragment());
            }
        }, 1000);
    }

    private void loadSplashAnimation() throws IOException {
        LottieAnimationView animView = getActivity().findViewById(R.id.view_splash_anim);
        animView.setAnimation(getActivity().getAssets().open("sword_anim.json"), "");
        animView.playAnimation();
    }
}