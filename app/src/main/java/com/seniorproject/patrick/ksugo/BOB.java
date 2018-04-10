package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by aschell3 on 3/12/2018.
 */

public class BOB extends AppCompatActivity{
    private WebView bobWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bob);
        bobWebView = (WebView)findViewById(R.id.bobView);
        WebSettings webSettings = bobWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        bobWebView.loadUrl("https://kennesaw.transloc.com/m/");
        bobWebView.setWebViewClient(new WebViewClient());
    }

    public void onBackPressed(){
        if(bobWebView.canGoBack()){
            bobWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void bobHome(View view){
        finish();
    }
}
