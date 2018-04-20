package com.seniorproject.patrick.ksugo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import static com.seniorproject.patrick.ksugo.CourseDiscussion.selectedDiscussion;

public class DiscussionView extends AppCompatActivity {
    private DiscussionBoard discussionBoard = new DiscussionBoard();
    private Discussion discussion = new Discussion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_view);
        discussionBoard = CourseDiscussion.selectedDiscussionBoard;
        getResponses();

    }

    public void getResponses() {
        ArrayList<String> discussions = new ArrayList<>();
        discussion = discussionBoard.getDiscussionBoard().get(CourseDiscussion.responsePosition - 1);
        discussions.add(discussion.toString());
        discussions.add("Responses:");
        for(Discussion discussion: discussionBoard.getDiscussionBoard()){
            for(Discussion discussion1:discussion.getReplies()){
                discussions.add(discussion1.responseToString());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.assignment_layoutfile, R.id.discussion, discussions);
        ListView listView = (ListView) findViewById(R.id.selectedDiscussionListView);
        listView.setAdapter(adapter);
    }
    public void reply(View view){
        setContentView(R.layout.response_discussion);
        TextView orginalText=(TextView) findViewById(R.id.originalDiscussionText);
        orginalText.setText(discussion.toString());
    }
    public void postReply(View view){
        EditText discussion=(EditText) findViewById(R.id.responseText);
        String responseText=discussion.getText().toString();
        for (Course course : D2L.courses1) {
            if (course.getCourseName() == D2L.selectedCourse) {
                for (DiscussionBoard discussionBoard : course.getDiscussionBoard()) {
                    Discussion response=new Discussion();
                    response.setDiscussion(responseText);
                    response.setCreatorName(D2L.member.getName());
                    response.setResponseID(Integer.toString(this.discussion.getDiscussionID()));
                    if(discussionBoard.getTitle()==this.discussionBoard.getTitle()){
                        discussionBoard.addDiscussion(response);
                    }

                }
            }

        }
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }
    public void cancel(View view){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void back(View view) {
        finish();
    }
}
