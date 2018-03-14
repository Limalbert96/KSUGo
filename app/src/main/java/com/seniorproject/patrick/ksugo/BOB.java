package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by aschell3 on 3/12/2018.
         */

public class BOB extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bob);
    }

    public void backPressed(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void bobHome(View view){
        finish();
    }
}
