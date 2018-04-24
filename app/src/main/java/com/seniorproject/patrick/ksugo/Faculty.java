package com.seniorproject.patrick.ksugo;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Albert on 4/22/18.
 */

public class Faculty {
    private String ksuID;
    private String firstName;
    private String lastName;
    private String dept;
    private String email;
    private String phone;
    private String office;
    private String hours;

    public Faculty() {
    }

    public Faculty(String firstName, String lastName, String email, String dept, String phone, String office, String hours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dept = dept;
        this.phone = phone;
        this.office = office;
        this.hours = hours;
    }


    public String getKsuID() {
        return ksuID;
    }

    public void setKsuID(String ksuID) {
        this.ksuID = ksuID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

}
