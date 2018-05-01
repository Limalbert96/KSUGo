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
    public static ArrayList<Course> courses1 = new ArrayList<Course>();
    public static String selectedCourse;
    public static String selectedCourseID;
    public static ArrayList<Grades> allGrades = new ArrayList<>();
    public static MemberKSU member;
    public static ArrayList<Annoucements> globalAnnouncements = new ArrayList<>();
    public static ArrayList<Assignments> allAssignments = new ArrayList<>();
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2_l);
        member = Login.member;
        courses1=HomepageStudentTeacher.courses1;
        allGrades=HomepageStudentTeacher.allGrades;
        if (courses1.isEmpty()) {

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
}







