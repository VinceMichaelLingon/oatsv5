package com.example.oatsv5.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.oatsv5.Adapters.ThesisRecyclerAdapter;
import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.Models.Thesis.ThesesResult;
import com.example.oatsv5.Models.Thesis.ThesisJSONResponse;
import com.example.oatsv5.R;
import com.example.oatsv5.RetrofitInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ThesisFragment extends Fragment {
    private FloatingActionButton fab1;
    private Button button;
    private RecyclerView recyclerView;
    private View view;
    String token;
    private SwipeRefreshLayout refreshLayout;
    List<ThesesResult> thesisList;
//    List<ThesesResult.KeywordsResult> keywordsList;
    private TextView thesisFileResult;
//    private String BASE_URL = "http://192.168.100.141:8000/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_thesis, container, false);

        recyclerView = view.findViewById(R.id.thesisRecyclerView);


        init();
        return view;

    }


    public void init(){
            recyclerView = view.findViewById(R.id.thesisRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            refreshLayout = view.findViewById(R.id.swipeThesis);

        thesisList();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                thesisList();
                refreshLayout.setRefreshing(false);
            }
        });


//            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    getActivity();
//                }
//            });
        }

    private void thesisList() {




        //setting up the retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
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



            public void onFailure(Call<ThesisJSONResponse> call, Throwable t) {


            }
        });


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

    }
        private void PutDataIntoRecyclerView(List<ThesesResult> thesisList) {

            ThesisRecyclerAdapter thesisRecyclerAdapter = new ThesisRecyclerAdapter(getContext(), thesisList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(thesisRecyclerAdapter);
        }


    }

