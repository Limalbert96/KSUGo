package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseGrades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_grades);
         TextView textView=(TextView) findViewById(R.id.courseGradesText);
        textView.setText(String.format("%s Grades", D2L.selectedCourse));
        addGrades();

    }


    public void onClick(View view) {
        finish();
        //startActivity(new Intent(CourseGrades.this,D2L.class));

    }
    public void addGrades(){
        TableLayout gradesTable= (TableLayout) findViewById(R.id.courseGradesTable);
        for(Grades grade:D2L.allGrades){
                if(D2L.selectedCourseID.equals(grade.getCourseID())) {
                    TableRow row = new TableRow(getApplicationContext());
                    TextView assignmentName = new TextView(getApplicationContext());
                    assignmentName.setGravity(View.FOCUS_RIGHT);
                    TextView assignmentGrade = new TextView(getApplicationContext());
                    assignmentGrade.setGravity(View.FOCUS_LEFT);
                    assignmentName.setTextColor(getResources().getColor(R.color.black));
                    assignmentGrade.setTextColor(getResources().getColor(R.color.black));
                    assignmentName.setText(grade.getAssignment()+": ");
                    assignmentGrade.setText(Double.toString(grade.getGrade()));
                    row.addView(assignmentName);
                    row.addView(assignmentGrade);
                    gradesTable.addView(row);
                }
        }
        /*
        ArrayList<Grades>grades=D2L.allGrades;
        String selectedCourse="";
        for(Course course: D2L.courses1){
            if(course.getCourseID()==D2L.selectedCourse){
                selectedCourse=course.getCourseID();
            }
        }
        TableLayout gradesTable= (TableLayout) findViewById(R.id.courseGradesTable);
        for(Grades grade : D2L.allGrades){
            TableRow row= new TableRow(getApplicationContext());
            TextView assignmentName=new TextView(getApplicationContext());
            TextView assignmentGrade=new TextView(getApplicationContext());
            assignmentName.setTextColor(getResources().getColor(R.color.black));
            assignmentGrade.setTextColor(getResources().getColor(R.color.black));

            if(grade.getCourseID()==D2L.selectedCourse){
                assignmentName.setText(grade.getAssignment()+" : ");
                assignmentGrade.setText(Double.toString(grade.getGrade()));
                row.addView(assignmentName);
                row.addView(assignmentGrade);
                gradesTable.addView(row);
            }

        }*/

    }


}
