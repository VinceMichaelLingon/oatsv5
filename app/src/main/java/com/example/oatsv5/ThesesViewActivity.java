package com.example.oatsv5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThesesViewActivity extends AppCompatActivity {
    private TextView thesisFileResult ;
    private String BASE_URL = "http://192.168.100.141:5000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theses_view);

        thesisFileResult = findViewById(R.id.thesis_file_result );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<ThesesResult>> call = retrofitInterface.getThesesResult();

        call.enqueue(new Callback<List<ThesesResult>>() {
            @Override
            public void onResponse(Call<List<ThesesResult>> call, Response<List<ThesesResult>> response) {
                if(!response.isSuccessful()){
                    thesisFileResult.setText("Code:" + response.code());
                    return;
                }
                List<ThesesResult> thesesResults = response.body();

                for (ThesesResult thesesResult : thesesResults) {
                    String details = "";

                    details += "Title :" + thesesResult.getTitle() + "\n" + "\n";
//                    details += "Base64 File :" + thesesResult.getBase64() + "\n";

                    thesisFileResult.append(details);

                }
            }

            @Override
            public void onFailure(Call<List<ThesesResult>> call, Throwable t) {
                thesisFileResult.setText(t.getMessage());
            }
        });

    }
}