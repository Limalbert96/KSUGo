package com.seniorproject.patrick.ksugo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CourseDiscussion extends AppCompatActivity {
    private ListView discussionBoard;
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_discussions);
        discussionBoard=(ListView) findViewById(R.id.discussion_boards);
        TextView textView=(TextView) findViewById(R.id.courseDiscussionsText);
        textView.setText(String.format("%s Discussions", D2L.selectedCourse));
        button=new Button(this);
    }

    public void onClick(View view) {
        finish();
    }

    public void addDiscussionBoard(){


    }

}
