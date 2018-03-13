package com.seniorproject.patrick.ksugo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by patri on 2/14/2018.
 */

public class Assignments {
    private Date dueDate;
    private String assignmentName;
    private String dueTime;



    public Assignments(){}
    public Assignments(Date dueDate, String assignmentName, String dueTime) {

        this.dueDate = dueDate;
        this.assignmentName = assignmentName;
        this.dueTime=dueTime;
    }


    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate)  {
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
    public String dateToString(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("E, MMMM dd");
        return  dateFormat.format(dueDate);
    }

    public String toString(){
        return assignmentName+"  |  "+dueTime;
    }


}
