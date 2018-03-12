package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class HomepageStudentTeacher extends AppCompatActivity {

    private ImageButton D2L;
    private ImageButton OwlLife;
    private ImageButton Handshake;
    private ImageButton BOB;
    private ImageButton ContactDirectory;
    private ImageButton InteractiveMap;
    private ImageButton News;
    private ImageButton Events;
    private ImageButton Emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepagestudentteacher);

        D2L = (ImageButton) findViewById(R.id.D2LButton);
        OwlLife = (ImageButton) findViewById(R.id.OwllifeButton);
        Handshake = (ImageButton) findViewById(R.id.HandshakeButton);
        BOB = (ImageButton) findViewById(R.id.BOBButton);
        ContactDirectory = (ImageButton) findViewById(R.id.ContactDirectoryButton);
        InteractiveMap = (ImageButton) findViewById(R.id.CampusMap);
        News = (ImageButton) findViewById(R.id.NewsButton);
        Events = (ImageButton) findViewById(R.id.EventsButton);
        Emergency = (ImageButton) findViewById(R.id.emergencyButton);


        // D2L - Still Need to implement these.
        D2L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, D2L.class));
            }
        }); //Need a method to get User Type.

        // Emergency
        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, EmergencyActivity.class));
            }
        });

        // Directory
        ContactDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, Directory.class));
            }
        });

        // NewsFeed
        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, NewsFeed.class));
            }
        });

        // Owl Life
        OwlLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Handshake
        Handshake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // BOB
        BOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Maps
        InteractiveMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }); //Need User Type


        // Events
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}



