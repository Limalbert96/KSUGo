package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CourseAnnouncements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_annoucements);
      TextView  textView=(TextView) findViewById(R.id.courseAnnoucementsText);
        textView.setText(String.format("%s Announcements", D2L.selectedCourse));
        addCourseAnnoucements();
    }


    public void onClick(View view) {
        finish();
    }

    public void addCourseAnnoucements() {
        ArrayList<Course> courses = D2L.courses1;
        Course selectedCourse = new Course();
        ArrayList<String> annoucements = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseName() == D2L.selectedCourse) {
                selectedCourse = course;
            }
        }
        ArrayList<String> list=new ArrayList<>();

        for (Annoucements announcemnt : selectedCourse.getAnnouncemnts()) {
            list.add(announcemnt.getAnnoucementName());
        }
            ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            ListView listView=(ListView) findViewById(R.id.courseAnnoucements);
            listView.setAdapter(adapter);


    }
}
