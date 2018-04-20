package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseAssignments extends AppCompatActivity {
    private ArrayList<Assignments> assignments=new ArrayList<Assignments>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_assignments);

       TextView textView=(TextView) findViewById(R.id.courAssignText);
        textView.setText(String.format("%s Assignments", D2L.selectedCourse));
        addAssignmentTable();

    }



    public void onClick(View view) {
        finish();

    }
    public void addAssignmentTable(){
      ArrayList<Course> courses = D2L.courses1;
        Course selectedCourse= new Course();
        for (Course course : courses) {
            if (course.getCourseName() == D2L.selectedCourse) {
                selectedCourse = course;
            }
        }

        TableLayout assignmentsTable=(TableLayout) findViewById(R.id.courseAssignmentTable);
            for (int j=0;j<selectedCourse.getAssignments().size();j++){
                TableRow row= new TableRow(getApplicationContext());
                row.setBackgroundColor(getResources().getColor(R.color.rowBackground));

                TextView assignmentName=new TextView(getApplicationContext());
                TextView timeDue=new TextView(getApplicationContext());
                TextView dueDate=new TextView(getApplicationContext());
                assignmentName.setTextColor(getResources().getColor(R.color.black));
                timeDue.setTextColor(getResources().getColor(R.color.black));
                dueDate.setTextColor(getResources().getColor(R.color.black));
                timeDue.setTextColor(getResources().getColor(R.color.black));

                if(j==0){
                    TableRow row2= new TableRow(getApplicationContext());
                    dueDate.setText(selectedCourse.getAssignments().get(j).dateToString()
                    );
                    row.addView(dueDate);

                    assignmentName.setText(selectedCourse.getAssignments().get(j).getAssignmentName()+"   ");
                    timeDue.setText(selectedCourse.getAssignments().get(j).timeToString());

                    row2.addView(assignmentName);
                    row2.addView(timeDue);
                    assignmentsTable.addView(row);
                    assignmentsTable.addView(row2);

                }

                else if(selectedCourse.getAssignments().get(j).getDueDate()==selectedCourse.getAssignments().get(j-1).getDueDate()){
                    assignmentName.setText(selectedCourse.getAssignments().get(j).getAssignmentName()+" | ");
                    timeDue.setText(selectedCourse.getAssignments().get(j).timeToString());


                    row.addView(assignmentName);
                    row.addView(timeDue);
                    assignmentsTable.addView(row);


                }
                else {
                    TableRow row2= new TableRow(getApplicationContext());
                    dueDate.setText(selectedCourse.getAssignments().get(j).dateToString());
                    row.addView(dueDate);

                    assignmentName.setText(selectedCourse.getAssignments().get(j).getAssignmentName());
                    timeDue.setText(selectedCourse.getAssignments().get(j).timeToString());

                    row2.addView(assignmentName);
                    row2.addView(timeDue);

                    assignmentsTable.addView(row);
                    assignmentsTable.addView(row2);


                }

            }
    }

}


