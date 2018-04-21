package com.example.minibusplanner.minibusdriver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int seat_number=16;
    private int occupied_seats=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView remaining_seats_field = (TextView) findViewById(R.id.seat_number);
        final Button plus_button=findViewById(R.id.plus_button);
        final Button minus_button=findViewById(R.id.minus_button);

        plus_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (occupied_seats<seat_number){
                    ++occupied_seats;
                    remaining_seats_field.setText(Integer.toString(occupied_seats));
                }
            }
        });

        minus_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (occupied_seats>0){
                    --occupied_seats;
                    remaining_seats_field.setText(Integer.toString(occupied_seats));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final TextView remaining_seats_field = (TextView) findViewById(R.id.seat_number);

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset_button) {
            occupied_seats=0;
            remaining_seats_field.setText(Integer.toString(occupied_seats));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("GO");
        alertDialog.setMessage("You can now leave to grab passengers on the way");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    alertDialog.show();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
