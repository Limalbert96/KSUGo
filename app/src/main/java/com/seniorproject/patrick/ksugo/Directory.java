package com.seniorproject.patrick.ksugo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by Albert on 3/7/18.
 */




public class Directory extends AppCompatActivity {
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        myWebView = (WebView)findViewById(R.id.direcView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://directory.kennesaw.edu/dir/people/");
        myWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.directoryLoadingPanel).setVisibility(View.GONE);

            }
        });

    }

    public void onBackPressed(){
        if(myWebView.canGoBack()){
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void directoryHome(View view){
        finish();
    }


}


