package com.seniorproject.patrick.ksugo;
//Lines 21-22, 42-46, 71-78 come from User 'dbDev' on Stack Exchange. (https://stackoverflow.com/questions/9370293/add-a-remember-me-checkbox)

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private EditText ksuID;
    private EditText loginpassword;
    private Switch remember;
    private Button login;
    private Button guest;
    private TextView incorrectLogin;
    private SharedPreferences loginPref;
    private SharedPreferences.Editor loginEdit;
    public static ArrayList<MemberKSU>membersKSU=new ArrayList<>();
    public static MemberKSU member;

    private boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        membersKSU.add(new MemberKSU("phileri1","1234",false,"Patrick Hilerio"));
        membersKSU.add(new MemberKSU("alim5","0000",true,"Albert Lim"));
        ksuID = (EditText) findViewById(R.id.userID);
        loginpassword = (EditText) findViewById(R.id.passwordField);
        remember = (Switch) findViewById(R.id.swLoginRem);
        login = (Button) findViewById(R.id.bLogin);
        guest = (Button) findViewById(R.id.bGuest);
        incorrectLogin = (TextView) findViewById(R.id.icLogin);
        loginPref = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginEdit = loginPref.edit();

        incorrectLogin.setVisibility(View.INVISIBLE);

        saveLogin = loginPref.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            ksuID.setText(loginPref.getString("username", ""));
            loginpassword.setText(loginPref.getString("password", ""));
            remember.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(ksuID.getText().toString(), loginpassword.getText().toString());
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginEdit.clear();
                loginEdit.apply();
                ksuID.setText("");
                loginpassword.setText("");
                Intent intent = new Intent(Login.this, HomepageGuest.class);
                startActivity(intent);
            }
        });
    }


    private void validate(String user, String password) {

        boolean isMember=false;
        for(MemberKSU member:membersKSU){
        if (user.equals(member.getUsername()) && password.equals(member.getPassword())) {
            if (remember.isChecked()) {
                loginEdit.putBoolean("saveLogin", true);
                loginEdit.putString("username", user);
                loginEdit.putString("password", password);
                loginEdit.commit();
            } else {
                loginEdit.clear();
                loginEdit.commit();
                ksuID.setText("");
                loginpassword.setText("");
            }
            Intent intent = new Intent(Login.this, HomepageStudentTeacher.class);
            isMember=true;
            startActivity(intent);
            Login.member=member;
        }
        } if(isMember==false) {
            incorrectLogin.setVisibility(View.VISIBLE);
        }
    }

}


