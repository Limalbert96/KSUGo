package com.seniorproject.patrick.ksugo;
//Lines 21-22, 42-46, 71-78 come from User 'dbDev' on Stack Exchange. (https://stackoverflow.com/questions/9370293/add-a-remember-me-checkbox)

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Executors;

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
    private JSONArray jsonArray=new JSONArray();

    private boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        connect(ksuID.getText().toString(), loginpassword.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    validate();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
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
        });}

    private void validate() throws JSONException {
        if(jsonArray.length() != 0){
            JSONObject jsonObject= jsonArray.getJSONObject(0);
            incorrectLogin.setVisibility(View.INVISIBLE);
            String userName = ksuID.getText().toString();
            String ksuPassword = loginpassword.getText().toString();
            if (remember.isChecked()) {
                loginEdit.putBoolean("saveLogin", true);
                loginEdit.putString("username", ksuID.getText().toString());
                loginEdit.putString("password", loginpassword.getText().toString());
                loginEdit.commit();
            } else {
                loginEdit.clear();
                loginEdit.commit();
                ksuID.setText("");
                loginpassword.setText("");
            }
            String isStudent=jsonObject.getString("is student");
            String name=jsonObject.getString("first name")+" "+jsonObject.getString("last name");
            MemberKSU memberKSU=new MemberKSU(userName,ksuPassword,Boolean.parseBoolean(isStudent),name);

            Intent intent = new Intent(Login.this, HomepageStudentTeacher.class);
            startActivity(intent);

            Login.member=memberKSU;
            finish();
        }
        else {
            incorrectLogin.setVisibility(View.VISIBLE);
        }
    }

    public void connect(String user, String pass){
        String host = "13.59.236.94:3000/api/users";
        String ip = "13.59.236.94";
        int port = 3000;
        String path = "/api/users/" + user + "&" + pass;
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
            jsonArray= new JSONArray(jsonObject.getString("Users"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
