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


import java.lang.reflect.Array;
import java.util.ArrayList;

public class D2L extends AppCompatActivity implements CoursesFrag.OnFragmentInteractionListener,AssignmentsFrag.OnFragmentInteractionListener,
        NewsFrag.OnFragmentInteractionListener{

    private TabLayout tabLayout;
    private TabItem news;
    private ViewPager viewPager;
    private Button assignment;
    private Button courseContent;
    private Button grades;
    private Button discussions;
    private Button annoucements;
    private int Student=000111222333;
    public static ArrayList<Course> courses1=new ArrayList<Course>();
    public static String selectedCourse;
    public static ArrayList<Grades> allGrades=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2_l);
        tabLayout = (TabLayout) findViewById(R.id.all_tabs);
        news = (TabItem) findViewById(R.id.news);
        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);
       if(courses1.isEmpty()){ insertCourses();}
       if(allGrades.isEmpty()){ addGrades();}
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
    public void home(View view){
        finish();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void onClick(View view){
        TextView textView;


        switch (view.getId()){
            case R.id.annoucementButton:{

                startActivity(new Intent(D2L.this, CourseAnnouncements.class));
                break;
            }
            case R.id.assignmentsButton:{

                startActivity(new Intent(D2L.this, CourseAssignments.class));

                break;
            }
            case R.id.contentButton:{

                startActivity(new Intent(D2L.this, CourseContent.class));

                break;
            }
            case R.id.discussionsButton:{

                startActivity(new Intent(D2L.this, CourseDiscussion.class));
                break;
            }
            case R.id.gradesButton:{

                startActivity(new Intent(D2L.this, CourseGrades.class));
                break;
            }
        }
    }

  public void insertCourses(){
        Assignments assignment1=new Assignments();
        Assignments assignment2=new Assignments();
        Assignments assignment3=new Assignments();
        Assignments assignment4=new Assignments();
        Assignments assignment5=new Assignments();

        assignment1.setAssignmentName("Quiz 1");
        assignment1.setDueDate("March 5th");
        assignment1.setDueTime("11:59pm");

          assignment2.setAssignmentName("Assignment 1");
          assignment2.setDueDate("March 10th");
          assignment2.setDueTime("11:59pm");

          assignment3.setAssignmentName("Test 1");
          assignment3.setDueDate("March 15th");
          assignment3.setDueTime("10:00pm");

          assignment4.setAssignmentName("Assignment 2");
          assignment4.setDueDate("March 20th");
          assignment4.setDueTime("11:59pm");

          assignment5.setAssignmentName("Essay");
          assignment5.setDueDate("March 25th");
          assignment5.setDueTime("11:59pm");
         assignment5.setAssignmentName("SDD");
      assignment5.setDueDate("Feb 22");
      assignment5.setDueTime("11:59pm");
        Course chemistry=new Course();
        Course internetProgramming=new Course();
        Course seniorProject=new Course();
        chemistry.setCourseID("chem1211");
        seniorProject.setCourseName("Senior Project");
        seniorProject.setCourseID("cs4850");
        internetProgramming.setCourseName("Internet Programming");
        internetProgramming.setCourseID("cs4720");
        chemistry.setCourseName("Chemistry");
        chemistry.addAssignment(assignment1);
        chemistry.addAssignment(assignment2);
        chemistry.addAssignment(assignment3);
        internetProgramming.addAssignment(assignment4);
        seniorProject.addAssignment(assignment5);

        Annoucements annoucement=new Annoucements("Class is canceled today");
        Annoucements annoucement1=new Annoucements("Study guide will be up this weekend");
        Annoucements annoucement2=new Annoucements("Grades are up for test 1. Most of you did well.");
        Annoucements annoucement3=new Annoucements("Whoever left his/hers phone, you have a cut dog. Please pick up your phone by the end of the week. Or your dog is mine");
        Annoucements annoucement4=new Annoucements("Quiz 1 is ending soon. For those of you who haven't taken please do.");
        Annoucements annoucement5=new Annoucements("Presentation next week. Be prepared to have a working prototype");

        chemistry.addAnnoucements(annoucement);
        seniorProject.addAnnoucements(annoucement5);
        internetProgramming.addAnnoucements(annoucement3);
        internetProgramming.addAnnoucements(annoucement4);
        chemistry.addAnnoucements(annoucement2);
        chemistry.addAnnoucements(annoucement1);

        courses1.add(chemistry);
        courses1.add(internetProgramming);
        courses1.add(seniorProject);


  }

public void addGrades(){
      Grades grade= new Grades(92,"Assignment 1",000111222333,"chem1211");
      Grades grade1= new Grades(80,"Assignment 2",000111222333,"chem1211");
      Grades grade2= new Grades(100,"Assignment 3",000111222333,"chem1211");
      Grades grade3= new Grades(100,"Quiz 1",000111222333,"chem1211");
      Grades grade4= new Grades(85,"Quiz 2",000111222333,"chem1211");
      Grades grade5= new Grades(94,"In-Class Assignment 1 ",000111222333,"cs4720");
      Grades grade6= new Grades(92,"Mid-Term",000111222333,"cs4720");
      Grades grade7= new Grades(100,"SRS",000111222333,"cs4850");
      Grades grade8= new Grades(86,"Assignment 1",000111222333,"cs4720");
      Grades grade9= new Grades(85,"Test 1",000111222333,"cs4720");
      Grades grade10= new Grades(93,"Quiz 2",000111222333,"cs4720");
      Grades grade11= new Grades(79,"Essay",000111222333,"chem1211");
      Grades grade12= new Grades(85,"Weekly Report 1",000111222333,"cs4850");
      Grades grade13= new Grades(75,"Weekly Report 2",000111222333,"cs4850");
      Grades grade14= new Grades(80,"Weekly Report 3",000111222333,"cs4850");
      Grades grade15= new Grades(95,"Weekly Report 4",000111222333,"cs4850");
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


}




