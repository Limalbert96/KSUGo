package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class HomepageGuest extends AppCompatActivity {

    private ImageButton BOB;
    private ImageButton ContactDirectory;
    private ImageButton InteractiveMap;
    private ImageButton News;
    private ImageButton Events;
    private ImageButton Emergency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_guest);

        BOB = (ImageButton) findViewById(R.id.BOBButton);
        ContactDirectory = (ImageButton) findViewById(R.id.ContactDirectoryButton);
        InteractiveMap = (ImageButton) findViewById(R.id.CampusMap);
        News = (ImageButton) findViewById(R.id.NewsButton);
        Events = (ImageButton) findViewById(R.id.EventsButton);
        Emergency = (ImageButton) findViewById(R.id.emergencyButton);

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


        //Events
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageGuest.this, Events.class));
            }
        });


    }
}
