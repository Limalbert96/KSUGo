package com.seniorproject.patrick.ksugo;

/**
 * Created by aschell3 on 3/12/2018.
 */

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;


public class OwlLife extends AppCompatActivity {

    private static final String TAG = "OwlLife";

    //private SectionPageAdapter mSectionPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owllife);
      /*  Log.d(TAG, "onCreate: Starting");

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        //TabLayout tablayout = (TabLayout) findViewById(R.id.tabs);
        //tablayout.setupWithViewPager(mViewPager);*/
    }

  /*  private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new OwlHome(), "HOME");
        adapter.addFragment(new OwlEvents(), "EVENTS");
        adapter.addFragment(new OwlGroups(), "GROUPS");
        adapter.addFragment(new OwlNews(), "NEWS");
        viewPager.setAdapter(adapter);

    }


    public void backPressed(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
*/
    public void home(View view){
        finish();
    }
}
