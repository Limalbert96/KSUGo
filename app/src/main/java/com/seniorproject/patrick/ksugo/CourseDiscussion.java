package com.seniorproject.patrick.ksugo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class CourseDiscussion extends AppCompatActivity {
    private ArrayList<DiscussionBoard> discussionBoard = new ArrayList<>();//All discussions of selected course
    public static Course course = new Course();//Selected Course
    private Spinner spinner;
    private ListView listView;
    public static String selection;//Discussion Board selected from spinner
    public static String selectedDiscussion;//Same as above
    public static int responsePosition;//Position of selected discussion user wants to respond to in listview
    public static DiscussionBoard selectedDiscussionBoard;//Selected discussion from spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_discussions);
        TextView textView = (TextView) findViewById(R.id.courseDiscussionsText);
        textView.setText(String.format("%s Discussions", D2L.selectedCourse));
        for (int i = 0; i < D2L.courses1.size(); i++) {
            if (D2L.selectedCourse == D2L.courses1.get(i).getCourseName()) {
                course = D2L.courses1.get(i);
            }
        }
        if (course.getDiscussionBoard().size() > 0) {
            this.discussionBoard = course.getDiscussionBoard();
        }

        createDiscussionBoard();
        createOnClickListener();
        for (int i = 0; i < discussionBoard.size(); i++) {
            if (selectedDiscussion == discussionBoard.get(i).getTitle()) {
                if (Login.member.isStudent()) {
                    spinner.setSelection(i);
                } else {
                    spinner.setSelection(1 + i);
                }
            }
        }

        listView = (ListView) findViewById(R.id.discuListView);
    }

    public void createOnClickListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Button button = (Button) findViewById(R.id.createDiscussionBoard);
                selection = spinner.getSelectedItem().toString();
                selectedDiscussion = spinner.getSelectedItem().toString();

                if (selection == "+ Create New Discussion Board"||selection=="No Discussions Found") {
                    if(!D2L.member.isStudent()){
                    button.setVisibility(View.VISIBLE);}
                    findViewById(R.id.discuListView).setVisibility(View.INVISIBLE);
                    findViewById(R.id.createNewDiscussion).setVisibility(View.INVISIBLE);

                } else {
                    button.setVisibility(View.INVISIBLE);
                    addDiscussionBoard();
                    findViewById(R.id.discuListView).setVisibility(View.VISIBLE);
                    findViewById(R.id.createNewDiscussion).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClick(View view) {
        selectedDiscussion = "";
        finish();
    }

    public void addDiscussionBoard() {
        ArrayList<String> discussions = new ArrayList<>();
        int position = 0;
        for (DiscussionBoard discussionBoard : discussionBoard) {
            if (discussionBoard.getTitle() == selection) {
                selectedDiscussionBoard = discussionBoard;
                discussions.add(discussionBoard.getTitle() + ":\n" + discussionBoard.getTopic());

                for (Discussion discussion : discussionBoard.getDiscussionBoard()) {
                    if (!discussion.isResponse()) {
                        discussions.add(discussion.toString());
                    }

                }
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.assignment_layoutfile, R.id.discussion, discussions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                responsePosition = position;
                if (position > 0) {
                    startActivity(new Intent(CourseDiscussion.this, DiscussionView.class));
                }
            }
        });


    }

    public void createDiscussionBoard() {
        ArrayList<String> discussions = new ArrayList<String>();
        if (!D2L.member.isStudent()) {
            discussions.add("+ Create New Discussion Board");
            for (int i = 0; i < course.getDiscussionBoard().size(); i++) {
                discussions.add(course.getDiscussionBoard().get(i).getTitle());
            }
        } else if (course.getDiscussionBoard().size() == 0) {
            discussions.add("No Discussions Found");
        } else {
            for (int i = 0; i < course.getDiscussionBoard().size(); i++) {
                discussions.add(course.getDiscussionBoard().get(i).getTitle());
            }
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, discussions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.discussionSelector);
        spinner.setAdapter(spinnerAdapter);

    }

    public void createNewDiscussionBoard(View view) {
        setContentView(R.layout.new_discussion_board);
    }

    public void addDiscussionBoard(View view) throws ParseException {
        EditText title = (EditText) findViewById(R.id.DiscussionBoardTitle);
        EditText text = (EditText) findViewById(R.id.DiscussionBoardText);
        String titleString = title.getText().toString();
        String textString = text.getText().toString();
        for (Course course : D2L.courses1) {
            if (course.getCourseName() == D2L.selectedCourse) {
                course.createDiscussionBoard(titleString, textString);
            }
        }
        setContentView(R.layout.activity_course_discussions);
        finish();
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }

    public void goToPostDiscussion(View view) {
        setContentView(R.layout.post_discussion);
    }

    public void cancelNewDiscussionBoard(View view) {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void post(View view) {
        EditText title = (EditText) findViewById(R.id.discussionTitle);
        EditText discussion = (EditText) findViewById(R.id.responseText);
        for (Course course : D2L.courses1) {
            if (course.getCourseName() == D2L.selectedCourse) {
                for (DiscussionBoard discussionBoard : course.getDiscussionBoard()) {
                    if (discussionBoard.getTitle() == selectedDiscussion) {
                        Discussion discussion1= new Discussion();
                        discussion1.setDiscussion(discussion.getText().toString());
                        discussion1.setTitle(title.getText().toString());
                        discussion1.setCreatorName(Login.member.getName());
                        discussion1.setDatePosted(new Date());
                        discussionBoard.addDiscussion(discussion1);
                    }
                }
            }
        }
        finish();
        startActivity(getIntent());


    }

}
