package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private ImageButton Logout;
    private ImageButton Emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepagestudentteacher);

        D2L = (ImageButton) findViewById(R.id.D2LButton);
      /*  OwlLife = (ImageButton) findViewById(R.id.OwlLifeButton);
        Handshake = (ImageButton) findViewById(R.id.HandshakeButton);
        BOB = (ImageButton) findViewById(R.id.BOBButton);
        ContactDirectory = (ImageButton) findViewById(R.id.ContactDirectoryButton);
        InteractiveMap = (ImageButton) findViewById(R.id.CampusMap);
        News = (ImageButton) findViewById(R.id.NewsButton);
        Events = (ImageButton) findViewById(R.id.EventsButton);
        Logout = (ImageButton) findViewById(R.id.LogoutButton);*/
        Emergency = (ImageButton) findViewById(R.id.emergency);


        //Still Need to implement these.
        D2L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, D2L.class));
            }
        }); //Need a method to get User Type.

        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageStudentTeacher.this, EmergencyActivity.class));
            }
        }); //Need a method to get User Type.


      /*  Handshake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        BOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ContactDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        InteractiveMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }); //Need User Type

        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageStudentTeacher.this, Login.class);
                startActivity(intent);
            }
        });*/
    }
}



