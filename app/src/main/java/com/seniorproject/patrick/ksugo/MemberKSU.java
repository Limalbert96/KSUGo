package com.seniorproject.patrick.ksugo;

import java.util.ArrayList;

/**
 * Created by patri on 3/10/2018.
 */

public class MemberKSU {
    private String id;
    private boolean isStudent=true;
    private String name;
    private ArrayList<String> courseName;//This will be equal to course name such as chem1211 plus the section number so, chem121110

    public MemberKSU() {
    }


    public MemberKSU(String id, boolean isStudent, String name, ArrayList<String> courseName) {
        this.id = id;
        this.isStudent = isStudent;
        this.name = name;
        this.courseName = courseName;
    }

    public MemberKSU(String id, boolean isStudent, String name) {
        this.id = id;
        this.isStudent = isStudent;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(ArrayList<String> courseName) {
        this.courseName = courseName;
    }
}
