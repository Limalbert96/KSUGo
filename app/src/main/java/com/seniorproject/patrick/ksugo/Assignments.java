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
    private Date dueTime;
    private String assignmentInformation;
    private String courseName;
    private String courseSection;



    public Assignments(){}
    public Assignments(Date dueDate, String assignmentName, Date dueTime) {

        this.dueDate = dueDate;
        this.assignmentName = assignmentName;
        this.dueTime=dueTime;
    }

    public Assignments(Date dueDate, String assignmentName, Date dueTime, String courseName) {
        this.dueDate = dueDate;
        this.assignmentName = assignmentName;
        this.dueTime = dueTime;
        this.courseName = courseName;
    }

    public Assignments(Date dueDate, String assignmentName, Date dueTime, String assignmentInformation, String courseName, String courseSection) {
        this.dueDate = dueDate;
        this.assignmentName = assignmentName;
        this.dueTime = dueTime;
        this.assignmentInformation = assignmentInformation;
        this.courseName = courseName;
        this.courseSection = courseSection;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    public Assignments(Date dueDate, String assignmentName, Date dueTime, String courseName, String courseSection) {

        this.dueDate = dueDate;
        this.assignmentName = assignmentName;
        this.dueTime = dueTime;
        this.courseName = courseName;
        this.courseSection = courseSection;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }
    public String dateToString(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("E, MMMM dd");
        return  dateFormat.format(dueDate);
    }
    public String timeToString(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("h:mm a");
        return  dateFormat.format(dueTime);

    }

    public String getAssignmentInformation() {
        return assignmentInformation;
    }

    public void setAssignmentInformation(String assignmentInformation) {
        this.assignmentInformation = assignmentInformation;
    }

    public String toString(){
        return assignmentName+"  |  "+dueTime;
    }


}
