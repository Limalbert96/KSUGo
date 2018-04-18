package com.seniorproject.patrick.ksugo;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/**
 * Created by patri on 4/16/2018.
 */

public class KSUSocket {
    private Socket socket;
    private String ip;
    private int port;
    private PrintStream out;
    private BufferedReader in;
    private JSONObject jsonObject;




    public KSUSocket() throws IOException {
        ip="13.59.236.94";
        port=3000;
    }
    public void readServer(String path) throws IOException, JSONException {
        socket=new Socket(ip,port);

        out=new PrintStream(socket.getOutputStream());
        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("GET " + path + " HTTP/2.0");
        out.println();
        out.flush();
        String line;
        String response="";
        String json="";
        in.ready();
        while ((line = in.readLine()) != null){
            response+=line;
        }
        for(int i=0;i<response.length();i++){
            if(response.charAt(i)=='{'){
                json=response.substring(i);
                i=response.length()+1;
            }
        }
         jsonObject=new JSONObject(json);
    }
    public void close() throws IOException {
        socket.close();
    }
    public JSONObject getJsonObject(){
        return jsonObject;
    }

    public void createServer(String path) throws IOException, JSONException {
        String username=Login.member.getUsername();
        String pass=Login.member.getPassword();

        URL url = new URL("http://13.59.236.94:3000/api/"+path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        jsonObject=new JSONObject(content.toString());



    }

}
