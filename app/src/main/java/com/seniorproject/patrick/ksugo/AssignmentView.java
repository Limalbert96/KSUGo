package com.seniorproject.patrick.ksugo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AssignmentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_view);


        TextView assignmentTitle = (TextView) findViewById(R.id.assignmentName);
        assignmentTitle.setText(AssignmentsFrag.selectedAssignment.getAssignmentName());
        TextView assignmentDueDate = (TextView) findViewById(R.id.assignmentDueDate);
        assignmentDueDate.setText(String.format("Due: %s %s", AssignmentsFrag.selectedAssignment.dateToString(), AssignmentsFrag.selectedAssignment.getDueTime()));
    }
    public void back(View view){
        finish();
    }

}
