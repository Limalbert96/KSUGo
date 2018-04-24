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
 * Created by Albert on 3/13/18.
 */

public class Handshake extends AppCompatActivity{
    private WebView handshakeWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handshake);
        handshakeWebView = (WebView)findViewById(R.id.handshakeView);
        WebSettings webSettings = handshakeWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        handshakeWebView.loadUrl("https://kennesaw.joinhandshake.com");
        handshakeWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.handshakeLoadingPanel).setVisibility(View.GONE);

            }
        });
    }


    public void onBackPressed(){
        if(handshakeWebView.canGoBack()){
            handshakeWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void handshakeHome(View view){
        finish();
    }

}
