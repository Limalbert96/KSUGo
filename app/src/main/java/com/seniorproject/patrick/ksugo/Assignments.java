package com.seniorproject.patrick.ksugo;

import java.util.Formatter;
import java.util.Locale;

/**
 * Created by patri on 2/14/2018.
 */

public class Assignments {
    private String dueDate;
    private String assignmentName;
    private String dueTime;



    public Assignments(){}
    public Assignments( String dueDate, String assignmentName, String dueTime) {

        this.dueDate = dueDate;
        this.assignmentName = assignmentName;
        this.dueTime=dueTime;
    }


    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String toString(){
        return assignmentName+"  |  "+dueTime;
    }


}
