package com.example.oatsv5.Models;

import com.example.oatsv5.Models.Courses.Courses;
import com.example.oatsv5.Models.Departments.Departments;

public class StudentUser {
    private String _id;
    private String user_fname;
    private String user_lname;
    private String user_tupmail;

    private Courses[] courses;

    public Courses[] getCourses() {
        return courses;
    }

    public void setCourses(Courses[] courses) {
        this.courses = courses;
    }

    private Departments[] departments;

    public Departments[] getDepartments() {
        return departments;
    }

    public void setDepartments(Departments[] departments) {
        this.departments = departments;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_tupmail() {
        return user_tupmail;
    }

    public void setUser_tupmail(String user_tupmail) {
        this.user_tupmail = user_tupmail;
    }

    public StudentUser(String _id, String user_fname, String user_lname, String user_tupmail) {
        this._id = _id;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_tupmail = user_tupmail;
    }
}
