package com.seniorproject.patrick.ksugo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.XmlRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView studentName;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewsFrag() {
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
    public static NewsFrag newInstance(String param1, String param2) {
        NewsFrag fragment = new NewsFrag();
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

    }}
    public void addRow(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.news_tab, container, false);
        studentName=(TextView)view.findViewById(R.id.student_name);
        studentName.setText("Welcome "+D2L.member.getName());
        addAnnouncements(view);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void addAnnouncements(View view) {
        TableLayout announcementsTable = (TableLayout) view.findViewById(R.id.announcementTable);
        ArrayList<Course> courses = D2L.courses1;
        announcementsTable.setColumnShrinkable(0,true);
        int counter=0;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getAnnouncemnts().size() > 0) {
                TableRow courseNameRow = new TableRow(getActivity().getApplicationContext());
                TextView courseName = new TextView((getActivity().getApplicationContext()));
                courseName.setTextColor(getResources().getColor(R.color.black));
                courseName.setText(courses.get(i).getCourseName());
                courseNameRow.setBackgroundColor(getContext().getResources().getColor(R.color.rowBackground));
                courseNameRow.addView(courseName);
                announcementsTable.addView(courseNameRow);

                for (int j = 0; j < courses.get(i).getAnnouncemnts().size(); j++) {
                    TableRow row = new TableRow(getActivity().getApplicationContext());
                    TextView announcement = new TextView(getActivity().getApplicationContext());
                    TextView date = new TextView(getActivity().getApplicationContext());
                    date.setTextColor(getResources().getColor(R.color.black));
                    announcement.setTextColor(getResources().getColor(R.color.black));
                        TableRow row2 = new TableRow(getActivity().getApplicationContext());
                        announcement.setText(courses.get(i).getAnnouncemnts().get(j).getAnnoucementName());
                            row.setBackgroundColor(getContext().getResources().getColor(R.color.white));

                        row.addView(announcement);

                        announcementsTable.addView(row);
                        counter++;

                    }

                }

            }
        }
    }


