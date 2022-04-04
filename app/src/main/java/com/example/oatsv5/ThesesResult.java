package com.example.oatsv5;

import com.google.gson.annotations.SerializedName;

public class ThesesResult {



    private String title;

    @SerializedName("image")
    private String base64;



    public String getTitle() {
        return title;
    }

    public String getBase64() {
        return base64;
    }
}
