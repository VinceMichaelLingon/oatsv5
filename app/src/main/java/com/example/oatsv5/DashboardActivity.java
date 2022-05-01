package com.example.oatsv5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.oatsv5.Fragments.ThesisFragment;
import com.example.oatsv5.Models.LoginGuestResult;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.100.141:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
                actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

                drawerLayout.addDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

                actionBarDrawerToggle.syncState();


//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<LoginGuestResult> call =retrofitInterface.executeLogin(map);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.home){
            Toast.makeText(DashboardActivity.this,"Home", Toast.LENGTH_LONG).show();
        }else if (menuItem.getItemId() == R.id.thesis_files ){

            Toast.makeText(DashboardActivity.this, "Thesis Files", Toast.LENGTH_LONG).show();


            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new ThesisFragment());
            fragmentTransaction.commit();
            drawerLayout.close();
        }else if (menuItem.getItemId() == R.id.borrow ){
            Toast.makeText(DashboardActivity.this, "Borrowing", Toast.LENGTH_LONG).show();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new MainFragment());
            fragmentTransaction.commit();
            drawerLayout.close();
        }else if (menuItem.getItemId() == R.id.search ){
            Toast.makeText(DashboardActivity.this, "Search", Toast.LENGTH_LONG).show();


        }else if (menuItem.getItemId() == R.id.logout ){
            Toast.makeText(DashboardActivity.this, "Logging Out", Toast.LENGTH_LONG).show();

            startActivity( new Intent(DashboardActivity.this, AuthActivity.class));
            finish();
        }
        return true;


    }
}