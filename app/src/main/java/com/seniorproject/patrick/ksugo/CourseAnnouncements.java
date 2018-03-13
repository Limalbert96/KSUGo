package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CourseAnnouncements extends AppCompatActivity {
    private EditText courseAnnoucements;
    private ArrayList<Course> courses = D2L.courses1;
    private Course selectedCourse = new Course();
    private ArrayList<String> list=new ArrayList<>();
    private ListView listView;
    private  ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(D2L.member.isStudent()==false){
        setContentView(R.layout.activity_course_annoucements);}
        else{
            setContentView(R.layout.activity_course_annoucements_student);
        }
        TextView  textView=(TextView) findViewById(R.id.courseAnnoucementsText);
        textView.setText(String.format("%s Announcements", D2L.selectedCourse));
        listView=(ListView) findViewById(R.id.courseAnnoucements);
        setCourse();
        setCourseAnnoucements();
    }


    public void onClick(View view) {
        finish();
    }
    public void setCourse(){
        for (Course course : courses) {
            if (course.getCourseName() == D2L.selectedCourse) {
                selectedCourse = course;
            }
        }
    }
    public void setCourseAnnoucements() {

        for (Annoucements announcemnt : selectedCourse.getAnnouncemnts()) {
            list.add(announcemnt.getAnnoucementName());
        }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
    }

    public void addCourseAnnoucement(View view){
        courseAnnoucements=(EditText) findViewById(R.id.annoucementTextField);
        for(Course course : courses){
            if(course.getCourseName()==D2L.selectedCourse){
                Annoucements annoucement= new Annoucements(courseAnnoucements.getText().toString());
                course.addAnnoucements(annoucement);
            }
        }
        list.add(0,courseAnnoucements.getText().toString());
        adapter.notifyDataSetChanged();
        courseAnnoucements.setText("");
    }
}
