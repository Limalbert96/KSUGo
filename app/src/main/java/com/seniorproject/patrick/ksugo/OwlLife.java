package com.seniorproject.patrick.ksugo;

/**
 * Created by aschell3 on 3/12/2018.
 */

import android.app.LocalActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;


public class OwlLife extends AppCompatActivity  implements OwlLifeNews.OnFragmentInteractionListener,OwlLifeGroups.OnFragmentInteractionListener,
        OwlLifeEvents.OnFragmentInteractionListener,OwlLifeHome.OnFragmentInteractionListener {

    private static final String TAG = "OwlLife";

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owllife);
        tabLayout = (TabLayout) findViewById(R.id.olTabs);
        final OLFragmentAdapter adapter = new OLFragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = (ViewPager) findViewById(R.id.olContainer);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });


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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
