package com.seniorproject.patrick.ksugo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Patrick on 2/11/2018.
 */

public class OLFragmentAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;

    public OLFragmentAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }
    


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: OwlLifeHome olHome= new OwlLifeHome();
            return olHome;
            case 1: OwlLifeEvents olEvents=new OwlLifeEvents();
            return olEvents;
            case 2: OwlLifeGroups olGroups=new OwlLifeGroups();
            return olGroups;
            case 3: OwlLifeNews olNews=new OwlLifeNews();
            return olNews;
            default: return null;
        }
    }



    @Override
    public int getCount() {
        return numOfTabs;
    }
}

