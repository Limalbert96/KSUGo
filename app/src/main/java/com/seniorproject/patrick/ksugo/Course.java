package com.seniorproject.patrick.ksugo;

import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by patri on 2/20/2018.
 */

public class Course {
    private ArrayList<Assignments> assignments=new ArrayList<Assignments>();
    private String courseName;
    private String courseID;
    private String courseSectionNumber;
    private String professorName;
    private ArrayList<String> studentList=new ArrayList<String>();
    private ArrayList<Annoucements> announcemnts= new ArrayList<>();
    public Course(){
        courseName=null;
        courseSectionNumber=null;
        professorName=null;

    }

    public ArrayList<Annoucements> getAnnouncemnts() {
        return announcemnts;
    }

    public Course(String courseID, String courseName, String courseSectionNumber, String professorName) {
        this.courseName = courseName;
        this.courseID=courseID;
        this.courseSectionNumber = courseSectionNumber;
        this.professorName = professorName;
    }
    public void populateStudents(){}
    public void populateAssignments(){}

    public String getCourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    public void addAssignment(Assignments assignment){
        assignments.add(assignment);
    }
    public void addAnnouncement(int index,Annoucements annoucement){
        announcemnts.add(index,annoucement);
    }
    public void addAnnoucements(Annoucements annoucement){
        announcemnts.add(0,annoucement);
    }
    public ArrayList<Assignments> getAssignments() {
        return assignments;
    }
    public void setAssignments(ArrayList<Assignments> assignments) {
        this.assignments = assignments;
    }

    public void setAnnouncemnts(ArrayList<Annoucements> announcemnts) {
        this.announcemnts = announcemnts;
    }
    public void sortAssignments(){
        Collections.sort(assignments, new Comparator<Assignments>() {
            @Override
            public int compare(Assignments assignment1, Assignments assignment2) {
                return assignment1.getDueDate().compareTo(assignment2.getDueDate()) ;
            }
        });    }

    public void deleteAnnoucement(int annoucementIndex){
        announcemnts.remove(annoucementIndex);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSectionNumber() {
        return courseSectionNumber;
    }

    public void setCourseSectionNumber(String courseSectionNumber) {
        this.courseSectionNumber = courseSectionNumber;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
    public String getStudent(int studentID){
        return studentList.get(studentID);
    }

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        this.studentList = studentList;
    }


}

