package com.example.oatsv5.Models;

public class LoginUserResult {

    private String email;


    private String password;
    private String departments;
    private String courses;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartments() {
        return departments;
    }

    public String getCourses() {
        return courses;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }
}
