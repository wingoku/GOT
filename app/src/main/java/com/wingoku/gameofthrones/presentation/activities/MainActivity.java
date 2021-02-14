package com.wingoku.gameofthrones.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.wingoku.gameofthrones.R;
import com.wingoku.gameofthrones.presentation.application.GameOfThronesApplication;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private Toolbar toolbar;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GameOfThronesApplication) getApplicationContext()).getAppComponent().inject(this);
        setContentView(R.layout.layout_activity_main);

        progressBar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);

        //to make splash screen fragment and categories fragment as TOP LEVEL FRAGMENTS cuz we don't want to show the BACK button in toolbar
        //https://developer.android.com/guide/navigation/navigation-ui#appbarconfiguration
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.splashScreenFragment, R.id.categoriesFragment).build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(navHostFragment);

        setSupportActionBar(toolbar);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void test() {
//        catViewModel.returnLiveData().observe(this, new Observer<List<Category>>() {
//            @Override
//            public void onChanged(List<Category> categories) {
//
//
//            }
//        });
    }


    private void loadMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}