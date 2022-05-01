package com.example.oatsv5.Models;

import com.example.oatsv5.Models.Courses.Courses;
import com.example.oatsv5.Models.Courses.CoursesJSONResponse;
import com.example.oatsv5.Models.Departments.Departments;

public class GuestUser {
    private String _id;
    private String guest_fname;
    private String guest_lname;
    private String guest_mail;



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGuest_fname() {
        return guest_fname;
    }

    public void setGuest_fname(String guest_fname) {
        this.guest_fname = guest_fname;
    }

    public String getGuest_lname() {
        return guest_lname;
    }

    public void setGuest_lname(String guest_lname) {
        this.guest_lname = guest_lname;
    }

    public String getGuest_mail() {
        return guest_mail;
    }

    public void setGuest_mail(String guest_mail) {
        this.guest_mail = guest_mail;
    }


    public GuestUser(String _id, String guest_fname, String guest_lname, String guest_mail) {
        this._id = _id;
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.guest_mail = guest_mail;
    }
}
