package com.seniorproject.patrick.ksugo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by patri on 3/18/2018.
 */

public class DiscussionBoardAdapter extends ArrayAdapter<String>{
    public static int selected;
    public DiscussionBoardAdapter(@NonNull Context context, ArrayList<String> list) {
        super(context, R.layout.module_button_layout,list);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.module_button_layout,parent,false);
        String discussion=getItem(position);
        TextView text;
        text=(TextView) view.findViewById(R.id.discussionTitle);
        text.setText(discussion);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView listView=(ListView) view.findViewById(R.id.discuListView);
                selected=view.getId();
                getContext().startActivity(new Intent(getContext(),DiscussionView.class));


            }
        });

        return view;
    }
}
