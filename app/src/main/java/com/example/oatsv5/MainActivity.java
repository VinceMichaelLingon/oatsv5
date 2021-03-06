package com.example.oatsv5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        //this syntax will pause the app for 1.5 secs and then any thing in run method will run.
        Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isFirstTime();
                    }
                }, 1500);
    }

        private void isFirstTime() {
        //for checking if the app is running for the very first time
            SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
            boolean isFirstTime = preferences.getBoolean("isFirstTime",true);

            if(isFirstTime) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isFirstTime", true);
                editor.apply();

                startActivity( new Intent(MainActivity.this,OnboardActivity.class));
                finish();
            }

            else{
                startActivity(new Intent(MainActivity.this,AuthActivity.class));
                finish();
            }
        }

}