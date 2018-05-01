package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executors;

public class HomepageGuest extends AppCompatActivity {

    private ImageButton BOB;
    private ImageButton ContactDirectory;
    private ImageButton InteractiveMap;
    private ImageButton News;
    private ImageButton Events;
    private ImageButton Emergency;
    private ImageButton Disabilities;

    public static JSONObject eventsObjectGuest;
    public static JSONArray eventsJSONArrayGuest = new JSONArray();
    public static JSONObject facultyObjectGuest;
    public static JSONArray facultyJSONArrayGuest = new JSONArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_guest);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    allEventsGuest();
                    allFacultiesGuest();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        BOB = (ImageButton) findViewById(R.id.BOBButton);
        ContactDirectory = (ImageButton) findViewById(R.id.ContactDirectoryButton);
        InteractiveMap = (ImageButton) findViewById(R.id.CampusMap);
        News = (ImageButton) findViewById(R.id.NewsButton);
        Events = (ImageButton) findViewById(R.id.EventsButton);
        Emergency = (ImageButton) findViewById(R.id.emergencyButton);
        Disabilities = (ImageButton) findViewById(R.id.DisabilitiesButton);

        // Emergency
        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageGuest.this, EmergencyActivity.class));
            }
        });

        // Contact Directory
        ContactDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageGuest.this, Directory.class));
            }
        });

        // NewsFeed
        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageGuest.this, NewsFeed.class));
            }
        });

        // BOB
        BOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageGuest.this, BOB.class));
            }
        });

        // Map
        InteractiveMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(HomepageGuest.this, InteractiveMap.class));
            }
        });

        // Disabilities
        Disabilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageGuest.this, Disabilities.class));
            }
        });

        //Events
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageGuest.this, Events.class));
            }
        });
    }

    public void allFacultiesGuest() throws IOException, JSONException {
        String path = "users/faculty";
        KSUSocket facultySocket = new KSUSocket();
        facultySocket.createServer(path);
        facultyObjectGuest = facultySocket.getJsonObject();
        facultyJSONArrayGuest = new JSONArray(facultyObjectGuest.getString("Faculty"));
    }

    public void allEventsGuest() throws IOException, JSONException {
        String path = "events";
        KSUSocket eventsSocket = new KSUSocket();
        eventsSocket.createServer(path);
        eventsObjectGuest = eventsSocket.getJsonObject();
        eventsJSONArrayGuest = new JSONArray(eventsObjectGuest.getString("Events"));
    }
}
