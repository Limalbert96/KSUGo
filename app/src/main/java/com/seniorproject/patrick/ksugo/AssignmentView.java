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
       /* for(int i=0;i<Login.eventsJSONArray.length();i++){
            JSONObject object=Login.eventsJSONArray.getJSONObject(i);

            content2+=object.getString("event name")+" ";

        }*/
       content2=HomepageStudentTeacher.eventsObject.getString("Events");
        TextView assignmentInfo = (TextView) findViewById(R.id.assignmentInfo);
        assignmentInfo.setText(content2);
    }


}


