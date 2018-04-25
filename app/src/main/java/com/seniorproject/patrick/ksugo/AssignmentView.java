package com.seniorproject.patrick.ksugo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

import static com.seniorproject.patrick.ksugo.HomepageStudentTeacher.courses1;


public class AssignmentView extends AppCompatActivity {
    private JSONObject jsonObject;
    private KSUSocket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_view);
        TextView assignmentInfo = (TextView) findViewById(R.id.assignmentInfo);
        assignmentInfo.setText(String.format("Course ID: %s (%s)\nDue: %s %s", AssignmentsFrag.selectedAssignment.getCourseName(),AssignmentsFrag.selectedAssignment.getCourseSection(), AssignmentsFrag.selectedAssignment.dateToString(), AssignmentsFrag.selectedAssignment.timeToString()));
        TextView assignmentTitle = (TextView) findViewById(R.id.assignmentName);
        assignmentTitle.setText(AssignmentsFrag.selectedAssignment.getAssignmentName());
       TextView assignmentDueDate = (TextView) findViewById(R.id.assignmentDueDate);
        assignmentDueDate.setText("");
    }

    public void back(View view) {
        finish();
    }

}


