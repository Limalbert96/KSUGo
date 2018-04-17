package com.seniorproject.patrick.ksugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

public class Disabilities extends AppCompatActivity {

    ImageButton a, b, c, d;
    private WebView myWebView;

    public void home(View view) {
        finish();
    }

    public void backPressed(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disabilities);

        a = findViewById(R.id.accommodate);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.accommodations_tab);
                myWebView = (WebView)findViewById(R.id.accommodationView);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.getSettings().setLoadWithOverviewMode(true);
                myWebView.getSettings().setUseWideViewPort(true);
                myWebView.getSettings().setBuiltInZoomControls(true);
                myWebView.getSettings().setDisplayZoomControls(false);
                myWebView.loadUrl("http://owlaccommodate.kennesaw.edu/students");
                myWebView.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        findViewById(R.id.accommodationLoadingPanel).setVisibility(View.GONE);

                    }
                });

            }
        });

        b = findViewById(R.id.disabilitiesMain);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.accommodations_tab);
                myWebView = (WebView)findViewById(R.id.accommodationView);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.loadUrl("http://sds.kennesaw.edu/index.php");
                myWebView.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        findViewById(R.id.accommodationLoadingPanel).setVisibility(View.GONE);

                    }
                });

            }
        });

        c = findViewById(R.id.able);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.accommodations_tab);
                myWebView = (WebView)findViewById(R.id.accommodationView);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.loadUrl("https://owllife.kennesaw.edu/organization/able/events?showPastEvents=true");
                myWebView.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        findViewById(R.id.accommodationLoadingPanel).setVisibility(View.GONE);

                    }
                });

            }
        });

        d = findViewById(R.id.amac);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.accommodations_tab);
                myWebView = (WebView)findViewById(R.id.accommodationView);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.setInitialScale(175);
                myWebView.loadUrl("https://www.amacusg.gatech.edu/dbsearch.php");
                myWebView.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        findViewById(R.id.accommodationLoadingPanel).setVisibility(View.GONE);

                    }
                });

            }
        });
    }
}
