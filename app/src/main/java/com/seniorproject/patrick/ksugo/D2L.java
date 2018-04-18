package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.Executors;

public class D2L extends AppCompatActivity implements CoursesFrag.OnFragmentInteractionListener, AssignmentsFrag.OnFragmentInteractionListener,
        NewsFrag.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private TabItem news;
    private ViewPager viewPager;
    private Button assignment;
    private Button courseContent;
    private Button grades;
    private Button discussions;
    private Button annoucements;
    //  private int Student = 000111222333;
    public static ArrayList<Course> courses1 = new ArrayList<Course>();
    // public static ArrayList<Course> courses = new ArrayList<Course>();
    public static String selectedCourse;
    public static ArrayList<Grades> allGrades = new ArrayList<>();
    public static MemberKSU member;
    public static ArrayList<Annoucements> globalAnnouncements = new ArrayList<>();
    public static ArrayList<Assignments> allAssignments = new ArrayList<>();
    //  private KSUSocket socket;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2_l);
        member = Login.member;
        courses1=HomepageStudentTeacher.courses1;
        allGrades=HomepageStudentTeacher.allGrades;
     /*   allGrades=KSUData.allGrades;
        courses1=KSUData.courses1;*/
        if (courses1.isEmpty()) {

           /* Executors.newSingleThreadExecutor().submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        // String path="/api/users/courses/"+Login.member.getUsername();
                        retrieveData();
                        addAnnouncements();
                        addAssignements();
                        addAllGrades();
                        insertCourses();
                        addGrades();


                        //  serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });*/
        }
        tabLayout = (TabLayout) findViewById(R.id.all_tabs);
        news = (TabItem) findViewById(R.id.news);
        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);


        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });


    }

    public void home(View view) {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.annoucementButton: {

                startActivity(new Intent(D2L.this, CourseAnnouncements.class));
                break;
            }
            case R.id.assignmentsButton: {

                startActivity(new Intent(D2L.this, CourseAssignments.class));

                break;
            }
            case R.id.contentButton: {

                startActivity(new Intent(D2L.this, CourseContent.class));

                break;
            }
            case R.id.discussionsButton: {

                startActivity(new Intent(D2L.this, CourseDiscussion.class));
                break;
            }
            case R.id.gradesButton: {

                startActivity(new Intent(D2L.this, CourseGrades.class));
                break;
            }
        }
    }

   /* public void addCourses() throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            Course course = new Course();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String courseName = jsonObject.getString("course id");
            String courseSection = jsonObject.getString("section number");
            course.setCourseName(courseName);
            course.setCourseID(courseName);
            course.setCourseSectionNumber(courseSection);
            courses.add(course);
        }

    }*/

    public void insertCourses() {
        Assignments assignment1 = new Assignments();
        Assignments assignment2 = new Assignments();
        Assignments assignment3 = new Assignments();
        Assignments assignment4 = new Assignments();
        Assignments assignment5 = new Assignments();

        assignment1.setAssignmentName("Quiz 1");
        assignment1.setDueDate(new Date(2018, 3, 27));
        assignment1.setDueTime("11:59pm");

        assignment2.setAssignmentName("Assignment 1");
        assignment2.setDueDate(new Date(2018, 3, 26));
        assignment2.setDueTime("11:59pm");

        assignment3.setAssignmentName("Test 1");
        assignment3.setDueDate(new Date(2018, 3, 24));
        assignment3.setDueTime("10:00pm");

        assignment4.setAssignmentName("Assignment 2");
        assignment4.setDueDate(new Date(2018, 4, 10));
        assignment4.setDueTime("11:59pm");

        assignment5.setAssignmentName("Essay");
        assignment5.setDueDate(new Date(2018, 3, 6));
        assignment5.setDueTime("11:59pm");
        assignment5.setAssignmentName("SDD");
        assignment5.setDueDate(new Date(2018, 3, 6));
        assignment5.setDueTime("11:59pm");

        Course chemistry = new Course();
        Course internetProgramming = new Course();
        Course seniorProject = new Course();
        chemistry.setCourseID("chem1211");
        seniorProject.setCourseName("Senior Project");
        seniorProject.setCourseID("cs4850");
        internetProgramming.setCourseName("Internet Programming");
        internetProgramming.setCourseID("cs4720");
        chemistry.setCourseName("Chemistry");

        assignment1.setCourseName(chemistry.getCourseName());
        assignment2.setCourseName(chemistry.getCourseName());
        assignment3.setCourseName(chemistry.getCourseName());
        chemistry.addAssignment(assignment1);
        chemistry.addAssignment(assignment2);
        chemistry.addAssignment(assignment3);

        assignment4.setCourseName(internetProgramming.getCourseName());
        internetProgramming.addAssignment(assignment4);

        assignment5.setCourseName(seniorProject.getCourseName());
        seniorProject.addAssignment(assignment5);

        Annoucements annoucement = new Annoucements("Class is canceled today");
        Annoucements annoucement1 = new Annoucements("Study guide will be up this weekend");
        Annoucements annoucement2 = new Annoucements("Grades are up for test 1. Most of you did well.");
        Annoucements annoucement3 = new Annoucements("Whoever left his/her phone, you have a cute dog. Please pick up your phone by the end of the week. Or your dog is mine");
        Annoucements annoucement4 = new Annoucements("Quiz 1 is ending soon. For those of you who haven't taken please do.");
        Annoucements annoucement5 = new Annoucements("Presentation next week. Be prepared to have a working prototype");

        chemistry.addAnnoucements(annoucement);
        seniorProject.addAnnoucements(annoucement5);
        internetProgramming.addAnnoucements(annoucement3);
        internetProgramming.addAnnoucements(annoucement4);
        chemistry.addAnnoucements(annoucement2);
        chemistry.addAnnoucements(annoucement1);
        chemistry.createDiscussionBoard("Introductions", "Please let your classmates get to know you. Write 1-2 " +
                "paragraphs introducing yourself. Then, respond to 2-3 classmates.");
        chemistry.getDiscussionBoard().get(0).addDiscussion("John", "Introduction", "Hi, my name is John", new Date());

        chemistry.createDiscussionBoard("Atoms", "Please do some research on 2 different types of atoms. " +
                "Then, discuss the differences of both. Respond 2 at least 2 different " +
                "classmates");
        courses1.add(chemistry);
        courses1.add(internetProgramming);
        courses1.add(seniorProject);
        for (Course course : courses1) {
            course.sortAssignments();
            for (Assignments assignment : course.getAssignments()) {
                allAssignments.add(assignment);
            }
        }
        Collections.sort(allAssignments, new Comparator<Assignments>() {
            @Override
            public int compare(Assignments assignment1, Assignments assignment2) {
                return assignment1.getDueDate().compareTo(assignment2.getDueDate());
            }
        });
    }

    public void addGrades() {
        Grades grade = new Grades(92, "Assignment 1", "000111222333", "chem1211");
        Grades grade1 = new Grades(80, "Assignment 2", "000111222333", "chem1211");
        Grades grade2 = new Grades(100, "Assignment 3", "000111222333", "chem1211");
        Grades grade3 = new Grades(100, "Quiz 1", "000111222333", "chem1211");
        Grades grade4 = new Grades(85, "Quiz 2", "000111222333", "chem1211");
        Grades grade5 = new Grades(94, "In-Class Assignment 1 ", "000111222333", "cs4720");
        Grades grade6 = new Grades(92, "Mid-Term", "000111222333", "cs4720");
        Grades grade7 = new Grades(100, "SRS", "000111222333", "cs4850");
        Grades grade8 = new Grades(86, "Assignment 1", "000111222333", "cs4720");
        Grades grade9 = new Grades(85, "Test 1", "000111222333", "cs4720");
        Grades grade10 = new Grades(93, "Quiz 2", "000111222333", "cs4720");
        Grades grade11 = new Grades(79, "Essay", "000111222333", "chem1211");
        Grades grade12 = new Grades(85, "Weekly Report 1", "000111222333", "cs4850");
        Grades grade13 = new Grades(75, "Weekly Report 2", "000111222333", "cs4850");
        Grades grade14 = new Grades(80, "Weekly Report 3", "000111222333", "cs4850");
        Grades grade15 = new Grades(95, "Weekly Report 4", "000111222333", "cs4850");
        allGrades.add(grade);
        allGrades.add(grade1);
        allGrades.add(grade2);
        allGrades.add(grade3);
        allGrades.add(grade4);
        allGrades.add(grade5);
        allGrades.add(grade6);
        allGrades.add(grade7);
        allGrades.add(grade8);
        allGrades.add(grade9);
        allGrades.add(grade10);
        allGrades.add(grade11);
        allGrades.add(grade12);
        allGrades.add(grade13);
        allGrades.add(grade14);
        allGrades.add(grade15);

    }

    /*public void connect() throws IOException {
        socket = new KSUSocket();
    }*/

    public void retrieveData() throws IOException, JSONException, ParseException {
        String username = Login.member.getUsername();
        KSUSocket courseSocket = new KSUSocket();
        //Courses
        courseSocket.createServer("users/courses/" + username);
        JSONObject courses = courseSocket.getJsonObject();
        JSONArray jsonArray = new JSONArray(courses.getString("Courses"));
        for (int i = 0; i < jsonArray.length(); i++)
        {//Adds all courses
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
        for (Course course:courses1) {
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
                    Grades grade = new Grades(Double.parseDouble(value), assignment, student, gradecourseID, gradeSection);
                    allGrades.add(grade);
                }
            }

        }


    }

    public void setTabs() {

    }

}







