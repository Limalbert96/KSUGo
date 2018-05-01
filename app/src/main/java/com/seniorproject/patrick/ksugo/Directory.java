package com.seniorproject.patrick.ksugo;

import android.app.SearchManager;
import android.content.Context;

import android.os.Bundle;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ExpandableListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Albert on 3/7/18.
 */

public class Directory extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchManager searchManager;
    private android.widget.SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchItem;

    private JSONArray jsonArray=new JSONArray();
    private ArrayList<Faculty> allFaculties = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        if(HomepageGuest.facultyJSONArrayGuest.length() != 0){
            jsonArray = new JSONArray(new ArrayList<String>());
            jsonArray = HomepageGuest.facultyJSONArrayGuest;
        }else{
            jsonArray = new JSONArray(new ArrayList<String>());
            jsonArray = HomepageStudentTeacher.facultyJSONArray;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();



        // The app will crash if display list is not called here.
        displayList();

        // This expands the list.
        expandAll();



    }

    public void getData()throws JSONException {
        for(int i=0;i<jsonArray.length();i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String ksuID = jsonObject.getString("ksu id");
            String firstName = jsonObject.getString("first name");
            String lastName = jsonObject.getString("last name");
            String department = jsonObject.getString("department");
            String email = jsonObject.getString("ksu email");
            String phone = jsonObject.getString("phone");
            String office = jsonObject.getString("office");
            String hours = jsonObject.getString("hours");

            Faculty facultyMember = new Faculty(firstName, lastName, email, department, phone, office, hours);
            allFaculties.add(facultyMember);

            //allFaculties = new TreeSet<>(Arrays.asList(facultyMember));
        }

        //to sort faculty by last name
        Collections.sort(allFaculties, new Comparator<Faculty>() {
            @Override
            public int compare(Faculty f1, Faculty f2) {
                return f1.getLastName().compareTo(f2.getLastName());
            }
        });


    }


    private void loadData() {
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;

        Map<String, List<Faculty>> facultiesByDepartment = new HashMap<>();

        for (Faculty faculty : allFaculties) {
            if (facultiesByDepartment.containsKey(faculty.getDept())) {
                facultiesByDepartment.get(faculty.getDept()).add(faculty);
            } else {
                facultiesByDepartment.put(faculty.getDept(), new ArrayList<>(Collections.singletonList(faculty)));
            }
        }

        for (Map.Entry<String, List<Faculty>> entry : facultiesByDepartment.entrySet()) {

            //generate rows
            childRows = new ArrayList<ChildRow>();
            for (Faculty faculty : entry.getValue()) {
                childRows.add(new ChildRow(R.mipmap.generic_icon,
                        faculty.getFirstName() + " " + faculty.getLastName() + "\nEmail: " +
                                faculty.getEmail() + "\nTelp: " + faculty.getPhone() +"\nDept: "+faculty.getDept()+
                                "\nOffice: " +faculty.getOffice() + "\nOffice Hours: " + faculty.getHours()));
            }

            //generate category
            parentRow = new ParentRow(entry.getKey(), childRows);
            parentList.add(parentRow);

        }

    }


    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        } //end for (int i = 0; i < count; i++)
    }

    private void displayList() {
        try {
            getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(Directory.this, parentList);

        myList.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();

        return true;
    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    public void directoryHome(View view){
        finish();
    }


}


