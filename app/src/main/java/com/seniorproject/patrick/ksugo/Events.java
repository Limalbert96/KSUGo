package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by aschell3 on 3/12/2018.
 */

public class Events extends AppCompatActivity{
    /* Event webview implementation
    private WebView eventsWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventsWebView = (WebView)findViewById(R.id.eventsWV);
        WebSettings webSettings = eventsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        eventsWebView.loadUrl("https://calendar.kennesaw.edu");
        eventsWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.eventsLoadingPanel).setVisibility(View.GONE);

            }
        });
    }

    public void eventsHome(View view){
        finish();
    }
    */

    private JSONArray jsonArray=new JSONArray();
    private ArrayList<Event> allEvents = new ArrayList<>();

    // stuffs from courseAssignments

    // Method to connect to the server
    public void connect(){
        String host = "13.59.236.94:3000/api/events";
        String ip = "13.59.236.94";
        int port = 3000;
        String path = "/api/events";
        Socket socket= new Socket();
        Thread thread=new Thread();
        try {

            socket= new Socket(ip,port);
            PrintStream out=new PrintStream(socket.getOutputStream());
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("GET " + path + " HTTP/1.0");
            out.println();
            out.flush();
            String line;
            String response="";
            String json="";
            while ((line = in.readLine()) != null){
                response+=line;
            }
            for(int i=0;i<response.length();i++){
                if(response.charAt(i)=='{'){
                    json=response.substring(i);
                    i=response.length()+1;
                }
            }
            JSONObject jsonObject=new JSONObject(json);
            jsonArray= new JSONArray(jsonObject.getString("Events"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // set data
    public void insertEvents() {
        Event event1 = new Event();
        Event event2 = new Event();

        event1.setEventName("All Majors Career Fair");
        event1.setEventDescription("Companies are looking for interns and full time employment across all majors.");
        event1.setEventBuilding("Gymnasium");
        event1.setEventRoom("Gym");
        event1.setEventDate(new Date(2018, 5, 15));
        event1.setEventTime(new Time(12,0,0));

        event2.setEventName("STEM Career Fair");
        event2.setEventDescription("Companies are looking for interns and full time employment across all majors.");
        event2.setEventBuilding("Gymnasium");
        event2.setEventRoom("Gym");
        event2.setEventDate(new Date(2018, 5, 22));
        event2.setEventTime(new Time(12,0,0));


        // push all data to the arraylist
        for (Event event : allEvents) {
            allEvents.add(event);
        }

        allEvents.add(event1);
        allEvents.add(event2);

        //to sort

        Collections.sort(allEvents, new Comparator<Event>() {
            @Override
            public int compare(Event event1, Event event2) {
                return event1.getEventDate().compareTo(event2.getEventDate());
            }
        });
    }

    //assign data to table view
    public void addEventTable(){
        TableLayout assignmentsTable=(TableLayout) findViewById(R.id.eventTable);

        for (int j=0;j<allEvents.size();j++){
            TableRow row= new TableRow(getApplicationContext());
            row.setBackgroundColor(getResources().getColor(R.color.rowBackground));

            TextView eventName=new TextView(getApplicationContext());
            TextView eventDescription=new TextView(getApplicationContext());
            TextView eventBuilding=new TextView(getApplicationContext());
            TextView eventRoom=new TextView(getApplicationContext());
            TextView eventDate=new TextView(getApplicationContext());
            TextView eventTime=new TextView(getApplicationContext());

            eventName.setTextColor(getResources().getColor(R.color.black));
            eventDescription.setTextColor(getResources().getColor(R.color.black));
            eventBuilding.setTextColor(getResources().getColor(R.color.black));
            eventRoom.setTextColor(getResources().getColor(R.color.black));
            eventDate.setTextColor(getResources().getColor(R.color.black));
            eventTime.setTextColor(getResources().getColor(R.color.black));

            // need to add building, desc, room,
            TableRow row2= new TableRow(getApplicationContext());

            eventDate.setText(allEvents.get(j).dateToString());
            row.addView(eventDate);

            eventName.setText(allEvents.get(j).getEventName()+"    ");
            eventTime.setText(allEvents.get(j).getEventTime().toString());

            row2.addView(eventName);
            row2.addView(eventTime);
            assignmentsTable.addView(row);
            assignmentsTable.addView(row2);

        }
    }


    // main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events2);

        TextView title=(TextView) findViewById(R.id.eventText);
        title.setText(String.format("Events"));
        insertEvents();
        addEventTable();

    }

    // for home button
    public void onClick(View view) {
        finish();

    }
}
