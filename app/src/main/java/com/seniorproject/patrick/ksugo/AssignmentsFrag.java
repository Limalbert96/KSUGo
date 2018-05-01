package com.seniorproject.patrick.ksugo;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssignmentsFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssignmentsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignmentsFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Assignments> assignments = new ArrayList<Assignments>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Assignments currentAssignment;
    public static Assignments selectedAssignment;


    private OnFragmentInteractionListener mListener;

    public AssignmentsFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursesFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AssignmentsFrag newInstance(String param1, String param2) {
        AssignmentsFrag fragment = new AssignmentsFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.assignment_tab, container, false);
        addTableAssignments(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void addTableAssignments(View view) {
        ArrayList<Date> allDates = new ArrayList<>();
        TableLayout assignmentsTable = (TableLayout) view.findViewById(R.id.assignmentsTable);
        assignmentsTable.setColumnShrinkable(0,true);



        final ArrayList<Course> courses = D2L.courses1;
        ArrayList<Assignments> allAssignments=new ArrayList<>();
        for (Course course:courses){
            for(Assignments assignment: course.getAssignments()){
                allAssignments.add(assignment);
            }
        }
        for(int i=0;i<allAssignments.size()-1;i++){
            for(int j=1;j<allAssignments.size()-1;j++){
                if(allAssignments.get(j).getDueDate().compareTo(allAssignments.get(i).getDueDate())>=1){
                    Assignments temp=allAssignments.get(j);
                    allAssignments.set(j,allAssignments.get(i));
                    allAssignments.set(i,temp);
                }
            }
        }
        for(final Assignments assignment: allAssignments){
            TableRow row = new TableRow(getActivity().getApplicationContext());
            row.setBackgroundColor(getContext().getResources().getColor(R.color.rowBackground));

            TextView courseName = new TextView(getActivity().getApplicationContext());
            TextView assignmentName = new TextView(getActivity().getApplicationContext());
            TextView timeDue = new TextView(getActivity().getApplicationContext());
            TextView dueDate = new TextView(getActivity().getApplicationContext());

            assignmentName.setTextColor(getResources().getColor(R.color.black));
            timeDue.setTextColor(getResources().getColor(R.color.black));
            dueDate.setTextColor(getResources().getColor(R.color.black));
            timeDue.setTextColor(getResources().getColor(R.color.black));
            courseName.setTextColor(getResources().getColor(R.color.black));

            TableRow row2 = new TableRow(getActivity().getApplicationContext());
            dueDate.setText(assignment.dateToString());
            row.addView(dueDate);
            currentAssignment=assignment;
            courseName.setText(String.format("%s ", assignment.getCourseName()));
            assignmentName.setText(Html.fromHtml("<u>"+assignment.getAssignmentName()+"</u>"));
            timeDue.setText(assignment.timeToString());
            row2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedAssignment=assignment;
                    startActivity(new Intent(getActivity(),AssignmentView.class));
                }

            });
            row2.addView(courseName);
            row2.addView(assignmentName);
            row2.addView(timeDue);
            assignmentsTable.addView(row);
            assignmentsTable.addView(row2);
        }
    }
}

