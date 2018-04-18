package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;

public class HomepageStudentTeacher extends AppCompatActivity {

    private ImageButton D2L;
    private ImageButton OwlLife;
    private ImageButton Handshake;
    private ImageButton BOB;
    private ImageButton ContactDirectory;
    private ImageButton InteractiveMap;
    private ImageButton News;
    private ImageButton Events;
    private ImageButton Emergency;
    public static KSUSocket socket;
    public static ArrayList<Grades> allGrades = new ArrayList<>();
    public static MemberKSU member;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    public static ArrayList<Course> courses1 = new ArrayList<Course>();
    private String name;
    public static JSONObject eventsObject;
    public static JSONArray eventsJSONArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepagestudentteacher);
        member = Login.member;
        if(name==null||name!=member.getName()){
            courses1.clear();
            allGrades.clear();
        }
        if (courses1.isEmpty()) {
            Executors.newSingleThreadExecutor().submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        // String path="/api/users/courses/"+Login.member.getUsername();
                        retrieveData();
                        addAnnouncements();
                        addAssignements();
                        addAllGrades();
                        allEvents();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        D2L = (ImageButton) findViewById(R.id.D2LButton);
        OwlLife = (ImageButton) findViewById(R.id.OwllifeButton);
        Handshake = (ImageButton) findViewById(R.id.HandshakeButton);
        BOB = (ImageButton) findViewById(R.id.BOBButton);
        ContactDirectory = (ImageButton) findViewById(R.id.ContactDirectoryButton);
        InteractiveMap = (ImageButton) findViewById(R.id.CampusMap);
        News = (ImageButton) findViewById(R.id.NewsButton);
        Events = (ImageButton) findViewById(R.id.EventsButton);
        Emergency = (ImageButton) findViewById(R.id.emergencyButton);


        // D2L - Still Need to implement these.
        D2L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, D2L.class));
            }
        }); //Need a method to get User Type.

        // Emergency
        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, EmergencyActivity.class));
            }
        });

        // Directory
        ContactDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, Directory.class));
            }
        });

        // NewsFeed
        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, NewsFeed.class));
            }
        });

        // Owl Life
        OwlLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, OwlLife.class));


            }
        });

        // Handshake
        Handshake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, Handshake.class));
            }
        });

        // BOB
        BOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, BOB.class));


            }
        });

        // Maps
        InteractiveMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }); //Need User Type


        // Events
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, Events.class));
            }
        });


    }

    public void retrieveData() throws IOException, JSONException, ParseException {
        String username = Login.member.getUsername();
        KSUSocket courseSocket = new KSUSocket();
        //Courses
        courseSocket.createServer("users/courses/" + username);
        JSONObject courses = courseSocket.getJsonObject();
        JSONArray jsonArray = new JSONArray(courses.getString("Courses"));
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
    }
    //Assignments
    ///courses/IT4153/section/2/assignments

    public void addAssignements() throws IOException, JSONException, ParseException {
        for (Course course : courses1) {
            String courseSection = course.getCourseSectionNumber();
            String courseID = course.getCourseID();
            KSUSocket assignmentsSocket = new KSUSocket();
            assignmentsSocket.createServer("courses/" + courseID + "/section/" + courseSection + "/assignments");
            JSONObject assignements = assignmentsSocket.getJsonObject();
            if (!assignements.getString("Assignments").isEmpty()) {
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
                    //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");//Might be causing the issue
                    //Date date = dateFormat.parse(dueDate);//You will get date object relative to server/client timezone wherever it is parsed
                    assignment.setDueDate(new Date(2018, 3, 24));
                    assignment.setDueTime(duetime);
                    course.addAssignment(assignment);
                }
            }
        }
    }

    //Still within first for loop
    //Annoucements
    //13.59.236.94:3000/api/courses/:course_id/section/:section_id/announcements
    public void addAnnouncements() throws IOException, JSONException, ParseException {
        for (Course course : courses1) {
            String courseID = course.getCourseID();
            String courseSection = course.getCourseSectionNumber();
            KSUSocket announcementSocket = new KSUSocket();
            announcementSocket.createServer("courses/" + courseID + "/section/" + courseSection + "/announcements");
            JSONObject announcements = announcementSocket.getJsonObject();
            if (announcements.has("Announcements")) {
                JSONArray announcementsArray = new JSONArray(announcements.getString("Announcements"));
                for (int j = 0; j < announcementsArray.length(); j++) {
                    JSONObject announcementObject = announcementsArray.getJSONObject(j);
                    Annoucements annoucement = new Annoucements();
                    String subject = announcementObject.getString("subject");
                    String announcementName = announcementObject.getString("announcement");
                    String dateString = announcementObject.getString("date");
                    annoucement.setAnnoucementName(announcementName);
                    //   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    // Date date = dateFormat.parse(dateString);//You will get date object relative to server/client timezone wherever it is parsed
                    annoucement.setDate(new Date(2018, 3, 24));
                    course.addAnnoucements(annoucement);
                }
            }
        }
    }

    //Grades
    //13.59.236.94:3000/api/users/nwilso54/grades/IT4153
    public void addAllGrades() throws IOException, JSONException {
        for (Course course : courses1) {
            KSUSocket gradesSocket = new KSUSocket();

            gradesSocket.createServer("users/" + Login.member.getUsername() + "/grades/" + course.getCourseID());
            JSONObject grades = gradesSocket.getJsonObject();
            if (grades.has("grades")) {
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
                    Grades grade = new Grades(Double.parseDouble(value), assignment, gradeStudentID, gradecourseID, gradeSection);
                    allGrades.add(grade);
                }
            }

        }


    }
    public void allEvents() throws IOException, JSONException {
        String path="events";
        KSUSocket eventsSocket=new KSUSocket();
        eventsSocket.createServer(path);
        eventsObject=eventsSocket.getJsonObject();
        eventsJSONArray=new JSONArray(eventsObject.getString("Events"));
    }
}



