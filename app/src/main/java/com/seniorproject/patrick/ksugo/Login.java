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
import java.net.ServerSocket;
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
    //   public static ArrayList<MemberKSU>membersKSU=new ArrayList<>();
    public static MemberKSU member;
    private JSONObject jsonObject;
    private JSONArray jsonArray = new JSONArray();
    public static KSUSocket serverSocket;
    private boolean saveLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    connect();
                    // retriveData();
                    //  serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//      membersKSU.add(new MemberKSU("phileri1","1234",false,"Patrick Hilerio"));
        //    membersKSU.add(new MemberKSU("alim5","0000",true,"Albert Lim"));
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
                Executors.newSingleThreadExecutor().submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            retriveData(ksuID.getText().toString(), loginpassword.getText().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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


    private void validate(String user, String password) throws JSONException {
        boolean isMember = false;

        if (user.equals(jsonObject.getString("ksu id"))) {
            incorrectLogin.setVisibility(View.INVISIBLE);

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
            String userName = jsonObject.getString("ksu id");
            String ksuPassword = password;
            String isStudent = jsonObject.getString("is student");
            String name = jsonObject.getString("first name") + " " + jsonObject.getString("last name");
            MemberKSU memberKSU = new MemberKSU(userName, ksuPassword, Boolean.parseBoolean(isStudent), name);
           //
            // startService();
            Intent intent = new Intent(Login.this, HomepageStudentTeacher.class);
            isMember = true;
            startActivity(intent);

            Login.member = memberKSU;
        }

        if (!isMember) {
            incorrectLogin.setVisibility(View.VISIBLE);
        }
    }

    public void connect() throws IOException {
        serverSocket = new KSUSocket();
    }

    public void retriveData(String user, String pass) throws IOException, JSONException {
        String path = "/api/users/" + user + "&" + pass;
        serverSocket.readServer(path);
        JSONObject jsonObjectResponse = serverSocket.getJsonObject();
        jsonArray = new JSONArray(jsonObjectResponse.getString("Users"));
        jsonObject=jsonArray.getJSONObject(0);
        validate(user, pass);
        // jsonArray= new JSONArray(jsonObject.getString("Users"));
    }


}
