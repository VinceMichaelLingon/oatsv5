package com.example.oatsv5.Models.Departments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentJSONResponse {
    @SerializedName("department")
    @Expose
    private Departments[] departments;


    public Departments[] getDepartments() {
        return departments;
    }

    public void setDepartments(Departments[] departments) {
        this.departments = departments;
    }
}
