package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.concurrent.Executors;

/**
 * Created by aschell3 on 3/12/2018.
 */

public class Events extends AppCompatActivity{
    private ArrayList<Event> allEvents = new ArrayList<>();
    private JSONObject jsonObject;
    private KSUSocket socket;
    private JSONArray jsonArray=new JSONArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events2);
        jsonArray=HomepageStudentTeacher.eventsJSONArray;
        TextView title=(TextView) findViewById(R.id.eventText);
        title.setText(String.format("Events"));
        try {
            getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        addEventTable();

     /*   Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    connect();
                    retrieveData();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/

    }

  /*  public void retrieveData() throws IOException, JSONException {
        String path="/api/events";
        socket.readServer(path);
        jsonObject =socket.getJsonObject();
        jsonArray= new JSONArray(jsonObject.getString("Events"));
        TextView textView=(TextView)findViewById(R.id.eventText);
        JSONObject jsonOBject2=jsonArray.getJSONObject(0);
        textView.setText(jsonOBject2.getString("event name"));
       // getData();
        //addEventTable();
    }
    public void connect() throws IOException {
        socket=new KSUSocket();
    }*/

    public void getData()throws JSONException {
        for(int i=0;i<jsonArray.length();i++) {
            Event event = new Event();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("event name");
            String description = jsonObject.getString("description");
            String building = jsonObject.getString("building name");
            String room = jsonObject.getString("room number");
            //String date = jsonObject.getString("date");
            //String time = jsonObject.getString("time");

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(jsonObject.getString("date"));
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date time = sdf.parse(jsonObject.getString("time"));
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            event.setEventName(name);
            event.setEventDescription(description);
            event.setEventBuilding(building);
            event.setEventRoom(room);
            //event.setEventDate(date);
            //event.setEventTime(time);
            allEvents.add(event);
        }


    }

    //assign data to table view
    public void addEventTable(){
        TableLayout eventsTable=(TableLayout) findViewById(R.id.eventTable);

        for (int j=0;j<allEvents.size();j++) {
            TableRow row = new TableRow(getApplicationContext());
            row.setBackgroundColor(getResources().getColor(R.color.rowBackground));

            TextView eventName = new TextView(getApplicationContext());
            TextView eventDescription = new TextView(getApplicationContext());
            TextView eventBuilding = new TextView(getApplicationContext());
            //TextView eventRoom=new TextView(getApplicationContext());
            //TextView eventDate=new TextView(getApplicationContext());
            //TextView eventTime=new TextView(getApplicationContext());

            eventName.setTypeface(Typeface.DEFAULT_BOLD);
            eventName.setTextColor(getResources().getColor(R.color.black));
            eventDescription.setTextColor(getResources().getColor(R.color.black));
            eventBuilding.setTextColor(getResources().getColor(R.color.black));
            //eventRoom.setTextColor(getResources().getColor(R.color.black));
            //eventDate.setTextColor(getResources().getColor(R.color.black));
            //eventTime.setTextColor(getResources().getColor(R.color.black));

            // need to add building, desc, room,
            TableRow row2 = new TableRow(getApplicationContext());
            TableRow row3 = new TableRow(getApplicationContext());
            TableRow row4 = new TableRow(getApplicationContext());

            //eventDate.setText(allEvents.get(j).dateToString());
            //eventTime.setText("Time: " + allEvents.get(j).timeToString());
            //row.addView(eventDate);
            //row.addView(eventTime);

            eventName.setText(allEvents.get(j).getEventName() + "    ");
            row2.addView(eventName);

            eventDescription.setText(allEvents.get(j).getEventDescription());
            row3.addView(eventDescription);

            // building and Room data are included in eventBuilding
            eventBuilding.setText(allEvents.get(j).getEventBuilding());
            //+ ", Room: " + allEvents.get(j).getEventRoom()+"\n");
            row4.addView(eventBuilding);

            //eventsTable.addView(row);
            eventsTable.addView(row2);
            eventsTable.addView(row3);
            eventsTable.addView(row4);

        }
    }

    // main

    // for home button
    public void onClick(View view) {
        finish();

    }

}
