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
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    createServer();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


            TextView assignmentTitle = (TextView) findViewById(R.id.assignmentName);
        assignmentTitle.setText(AssignmentsFrag.selectedAssignment.getAssignmentName());
        TextView assignmentDueDate = (TextView) findViewById(R.id.assignmentDueDate);
        assignmentDueDate.setText(String.format("Due: %s %s", AssignmentsFrag.selectedAssignment.dateToString(), AssignmentsFrag.selectedAssignment.getDueTime()));
    }

    public void back(View view) {
        finish();
    }

   /* public void retrieveData() throws IOException, JSONException {
        String path="/api/users/courses/"+Login.member.getUsername();
        socket.readServer(path);
        jsonObject =socket.getJsonObject();

        TextView assignmentInfo = (TextView) findViewById(R.id.assignmentInfo);
        assignmentInfo.setText(jsonObject.getString("Courses"));


    }
    public void connect() throws IOException {
        socket=new KSUSocket();
    }*/
    public void createServer() throws IOException, JSONException {
        String username=Login.member.getUsername();
       // KSUSocket socket=new KSUSocket();
       // socket.createServer("courses/IT4153/section/2/announcements");
        //String content=socket.getJsonObject().getString("Announcements");
        //JSONArray array= new JSONArray(content);
        String content2="";
        /*
        for(int i = 0; i< courses1.size(); i++){
            for(DiscussionBoard discussionBoard:courses1.get(i).getDiscussionBoard()){
                //for (Discussion discussion:discussionBoard.getDiscussionBoard()){
                    content2+=discussionBoard.getDiscussionBoardID();
                  //  }
            }


        }*/
        content2=Integer.toString(courses1.get(4).getDiscussionBoard().get(1).getDiscussionBoard().get(0).getDiscussionID());
       /* for(Course course:HomepageStudentTeacher.courses1){
        for (DiscussionBoard discussionBoard : course.getDiscussionBoard()) {
            String id = Integer.toString(discussionBoard.getDiscussionBoardID());
            String dlPath = "discussionslist/" + id + "/discussions";
            KSUSocket discussionResponseSocket = new KSUSocket();
            discussionResponseSocket.createServer(dlPath);
            JSONObject dlJsON = discussionResponseSocket.getJsonObject();
            if (dlJsON.has("Discussions")) {
                JSONArray dlArray = new JSONArray(dlJsON.getString("Discussions"));
                for (int i = 0; i < dlArray.length(); i++) {
                    JSONObject responses = dlArray.getJSONObject(i);
                    String discussionid = responses.getString("discussionid");
                    content2 += discussionid+" ";
                }}}}*/

        TextView assignmentInfo = (TextView) findViewById(R.id.assignmentInfo);
        assignmentInfo.setText(content2);
    }


}


