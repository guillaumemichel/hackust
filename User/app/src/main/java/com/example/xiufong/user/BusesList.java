package com.example.xiufong.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class BusesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buses_list);

        final boolean b = false;
        final CheckBox cb0 = findViewById(R.id.checkbox0);
        final CheckBox cb1 = findViewById(R.id.checkbox1);
        final CheckBox cb2 = findViewById(R.id.checkbox2);


        final RequestQueue queue = Volley.newRequestQueue(this);
        final TextView mTextView = (TextView) findViewById(R.id.helloworld);

        final String url00 = "http://18.236.187.194:5000/user/632/bus/11";
        final String url01 = "http://18.236.187.194:5000/user/632/bus/11/delete";
        final String url10 = "http://18.236.187.194:5000/user/632/bus/11m";
        final String url11 = "http://18.236.187.194:5000/user/632/bus/11m/delete";
        final String url20 = "http://18.236.187.194:5000/user/632/bus/12";
        final String url21 = "http://18.236.187.194:5000/user/632/bus/12/delete";

        cb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Request a string response from the provided URL.
                final StringRequest stringRequest = new StringRequest(Request.Method.GET, isChecked? url00 : url01,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                mTextView.setText("Response is: "+ response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
                queue.add(stringRequest);

            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Request a string response from the provided URL.
                final StringRequest stringRequest = new StringRequest(Request.Method.GET, isChecked? url10 : url11,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                mTextView.setText("Response is: "+ response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
                queue.add(stringRequest);

            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Request a string response from the provided URL.
                final StringRequest stringRequest = new StringRequest(Request.Method.GET, isChecked? url20 : url21,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                mTextView.setText("Response is: "+ response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
                queue.add(stringRequest);

            }
        });


    }

}
