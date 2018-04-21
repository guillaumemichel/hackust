package com.example.xiufong.user;

import android.app.ExpandableListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusList extends AppCompatActivity {
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        expandableListView = (ExpandableListView) findViewById(R.id.exp_listview);
        List<String> Heading = new ArrayList<String>();
        List<String> L1 =new ArrayList<String>();
        List<String> L2 =new ArrayList<String>();
        List<String> L3 =new ArrayList<String>();

        HashMap<String,List<String>> TimesList= new HashMap<String, List<String>>();
        String heading_items[]= getResources().getStringArray(R.array.heading_titles);
        String l1[] = getResources().getStringArray(R.array.h1_item);
        String l2[] = getResources().getStringArray(R.array.h2_item);
        String l3[] = getResources().getStringArray(R.array.h3_item);

        for(String title: heading_items){
            Heading.add(title);
        }
        for(String title: l1){
            L1.add(title);
        }
        for(String title: l2){
            L2.add(title);
        }
        for(String title: l3){
            L3.add(title);
        }
        TimesList.put(Heading.get(0),L1);
        TimesList.put(Heading.get(1),L2);
        TimesList.put(Heading.get(2),L3);

        BusAdapter busAdapter = new BusAdapter(this, Heading,TimesList);
        expandableListView.setAdapter(busAdapter);
    }
}
