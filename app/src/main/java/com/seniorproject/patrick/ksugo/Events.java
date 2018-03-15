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

public class Events extends AppCompatActivity{

    private WebView eventsWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventsWebView = (WebView)findViewById(R.id.eventsWV);
        WebSettings webSettings = eventsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        eventsWebView.loadUrl("https://calendar.kennesaw.edu");
        eventsWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.eventsLoadingPanel).setVisibility(View.GONE);

            }
        });
    }

    public void eventsHome(View view){
        finish();
    }
}
