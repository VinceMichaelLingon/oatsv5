package com.example.oatsv5.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ThesesResult  {



    private String title;

    @SerializedName("publishedAt")
    private Integer published;

    private ArrayList authors;

    private ArrayList keywords;


    @SerializedName("abstract")
    private String abstrct;

    private String upload;

    private String status;


    private Object department;

    private Object course;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public ArrayList getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList authors) {
        this.authors = authors;
    }

    public ArrayList getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList keywords) {
        this.keywords = keywords;
    }

    public String getAbstrct() {
        return abstrct;
    }

    public void setAbstrct(String abstrct) {
        this.abstrct = abstrct;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    public Object getCourse() {
        return course;
    }

    public void setCourse(Object course) {
        this.course = course;
    }

    @SerializedName("image")
    private String base64;





    public String getBase64() {
        return base64;
    }
}
