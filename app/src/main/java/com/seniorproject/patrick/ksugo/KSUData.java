package com.seniorproject.patrick.ksugo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class KSUData extends Service {
    private KSUSocket socket;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    public static JSONObject users;
    public static ArrayList<Course> courses1;
    public static ArrayList<Grades> allGrades;

    public KSUData() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Welcome to KSUGo!",Toast.LENGTH_LONG).show();
      /*  Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    retrieveData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });*/

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void retrieveData() throws IOException, JSONException, ParseException {
        String username = Login.member.getUsername();
        KSUSocket socket = new KSUSocket();
        //Courses
        socket.createServer("users/courses/" + username);
        JSONObject courses = socket.getJsonObject();
        JSONArray jsonArray = new JSONArray(jsonObject.getString("Courses"));
        for (int i = 0; i < jsonArray.length(); i++) {//Adds all courses
            Course course = new Course();
            JSONObject courseInfo = jsonArray.getJSONObject(i);
            String courseName = courseInfo.getString("course id");
            String courseID = courseInfo.getString("course id");
            String courseSection = courseInfo.getString("section number");
            course.setCourseSectionNumber(courseSection);
            course.setCourseID(courseID);
            course.setCourseName(courseName);
            courses1.add(course);
        }

        //Assignments
        ///courses/IT4153/section/2/assignments
        for (int i = 0; i < courses1.size(); i++) {
            String courseSection = courses1.get(i).getCourseSectionNumber();
            String courseID = courses1.get(i).getCourseID();
            KSUSocket assignmentsSocket=new KSUSocket();

            assignmentsSocket.createServer("courses/" + courseID + "/section/" + courseSection + "/assignments");
            JSONObject assignements = assignmentsSocket.getJsonObject();
            JSONArray assignmentArray = new JSONArray(assignements.getString("Assignments"));
            for (int j = 0; j < assignmentArray.length(); j++) {
                Assignments assignment = new Assignments();
                JSONObject assignmentObject = assignmentArray.getJSONObject(j);
                String assignementName = assignmentObject.getString("assignment");
                String courseName = assignmentObject.getString("course id");
                String dueDate = assignmentObject.getString("duedate");
                String duetime = assignmentObject.getString("duetime");
                String section = assignmentObject.getString("section number");
                assignment.setCourseName(courseName);
                assignment.setAssignmentName(assignementName);
                assignment.setCourseSection(section);
                assignment.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(dueDate));
                assignment.setDueTime(duetime);
                courses1.get(i).addAssignment(assignment);
            }
            //Still within first for loop
            //Annoucements
            //13.59.236.94:3000/api/courses/:course_id/section/:section_id/announcements
            KSUSocket announcementSocket=new KSUSocket();
            announcementSocket.createServer("courses/" + courseID + "/section/" + courseSection + "/announcements");
            JSONObject announcements = announcementSocket.getJsonObject();
            JSONArray announcementsArray = new JSONArray(announcements.getString("Announcements"));
            for (int j=0;j<announcementsArray.length();j++){
                JSONObject announcementObject=announcementsArray.getJSONObject(j);
                Annoucements annoucement=new Annoucements();
                String subject=announcementObject.getString("subject");
                String announcementName=announcementObject.getString("announcement");
                String date=announcementObject.getString("date");
                annoucement.setAnnoucementName(announcementName);
                annoucement.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
                courses1.get(i).addAnnoucements(annoucement);
            }

            //Grades
            //13.59.236.94:3000/api/users/grades/KSU_ID&COURSE_ID
            KSUSocket gradesSocket=new KSUSocket();

            gradesSocket.createServer("users/grades" + Login.member.getUsername() + "&" + courses1.get(i).getCourseName());
            JSONObject grades = gradesSocket.getJsonObject();
            JSONArray gradesArray = new JSONArray(grades.getString("grades"));
            for (int j = 0; j < gradesArray.length(); j++) {
                JSONObject gradeObject = gradesArray.getJSONObject(j);
                // Grades grade = new Grades();
                //Grades grade1 = new Grades(92, "Assignment 1", 000111222333, "chem1211");
                String value = gradeObject.getString("grade");
                String assignment = gradeObject.getString("assignment");
                String gradecourseID = gradeObject.getString("course id");
                String gradeSection = gradeObject.getString("section number");
                String student = gradeObject.getString("ksu id");
                String gradeStudentID = Login.member.getUsername();
                Grades grade = new Grades(Double.parseDouble(value), assignment, gradeStudentID, gradecourseID, gradecourseID);
                allGrades.add(grade);
            }

        }


    }

}
