package com.seniorproject.patrick.ksugo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsFeed extends AppCompatActivity {

    ImageButton a, b;
    private WebView myWebView;

    public void home(View view) {
        finish();
    }

    public void backPressed(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public static Twitter getTwitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("2GrVfGWXlVNYo4ZkrKZIlQcVj")
                .setOAuthConsumerSecret("6tUuxhseY7Lohy4qhatHJWPOzoBAh1sfBqP8atjbvsBwVUvBSY")
                .setOAuthAccessToken("971925164870045696-u2dxN9HkdRnTSOwM1Lsyofex0iDhVyZ")
                .setOAuthAccessTokenSecret("erMW9I02aziXVeVCPdXoWVpKESo93VU9mDy67JqicgKsv");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
    }

    public static List<String> getTimeLine() throws TwitterException {
        List<Status> statuses = getTwitterInstance().getHomeTimeline();
        List<String> tweets = new ArrayList<String>();
        for (Status status : statuses) {
            tweets.add(status.getUser().getName() + " - " + status.getCreatedAt() + "\n" + status.getText());
        }
        return tweets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        a = findViewById(R.id.facebookNews);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.facebook_tab);
                myWebView = (WebView)findViewById(R.id.fbView);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.loadUrl("https://www.facebook.com/pg/KennesawStateUniversity/posts/");
                myWebView.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        findViewById(R.id.facebookLoadingPanel).setVisibility(View.GONE);

                    }
                });

            }
        });

        b = findViewById(R.id.twitterNews);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.twitter_tab);
                new Thread(new Runnable() {
                    public void run(){
                        final ArrayList<String> tweets = new ArrayList<String>();

                        try {
                            tweets.addAll(getTimeLine());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ListView listView = (ListView) findViewById(R.id.listView3);
                                try {
                                    listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.assignment_layoutfile, R.id.twitterText, tweets));
                                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}






