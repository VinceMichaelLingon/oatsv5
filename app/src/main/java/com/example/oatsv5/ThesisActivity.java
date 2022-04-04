package com.example.oatsv5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.TextView;

import com.example.oatsv5.Adapters.ThesisRecyclerAdapter;
import com.example.oatsv5.Models.ThesesResult;
import com.example.oatsv5.Models.ThesisJSONResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThesisActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    List<ThesesResult>thesisList;
    private TextView thesisFileResult ;
    private String BASE_URL = "http://192.168.100.141:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis);
//        thesisFileResult = findViewById(R.id.thesis_file_result);

        recyclerView = findViewById(R.id.thesisRecyclerView);
        thesisList = new ArrayList<>();


        //setting up the retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //call the interface
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ThesisJSONResponse> call = retrofitInterface.getThesesResult();


        call.enqueue(new Callback<ThesisJSONResponse>() {
            @Override
            public void onResponse(Call<ThesisJSONResponse> call, Response<ThesisJSONResponse> response) {

                ThesisJSONResponse thesisJSONResponse = response.body();
                thesisList = new ArrayList<>(Arrays.asList(thesisJSONResponse.getThesesArray()));

                PutDataIntoRecyclerView(thesisList);
            }

            @Override
            public void onFailure(Call<ThesisJSONResponse> call, Throwable t) {


            }
        });
    }
//        call.enqueue(new Callback<List<ThesesResult>>() {
//
//
//            @Override
//            public void onResponse(Call<List<ThesesResult>> call, Response<List<ThesesResult>> response) {
//                if(!response.isSuccessful()){
//                    thesisFileResult.setText("Code:" + response.code());
//                    return;
//                }
//                List<ThesesResult> thesesResults = response.body();
//
//                for (ThesesResult thesesResult : thesesResults) {
////                    String details = "";
////
////                    details += "Title :" + thesesResult.getTitle() + "\n" + "\n";
//////                    details += "Base64 File :" + thesesResult.getBase64() + "\n";
////
////                    thesisFileResult.append(details);
//
////                    String title = thesesResult.getTitle();
//
//                    thesisList.add(thesesResult);
//
//                }
//                    PutDataIntoRecyclerView(thesisList);
//
//            }
//
//
//
//            @Override
//            public void onFailure(Call<List<ThesesResult>> call, Throwable t) {
//                thesisFileResult.setText(t.getMessage());
//            }
//        });
//        }
    private void PutDataIntoRecyclerView(List<ThesesResult> thesisList) {

        ThesisRecyclerAdapter thesisRecyclerAdapter = new ThesisRecyclerAdapter(this, thesisList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(thesisRecyclerAdapter);
    }
}