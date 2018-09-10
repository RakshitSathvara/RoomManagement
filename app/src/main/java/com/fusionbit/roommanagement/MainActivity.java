package com.fusionbit.roommanagement;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.fusionbit.roommanagement.model.Room;
import com.fusionbit.roommanagement.views.RoomView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ROOM";

    private Spinner spinSelectedRooms;

    private LinearLayout llRoomDetailsContainer;

    private int previousSelectedRoomCount = 0;

    private List<Room> roomList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llRoomDetailsContainer = findViewById(R.id.linear_container);

        findViewById(R.id.btn_submit).setOnClickListener(this);

        spinSelectedRooms = findViewById(R.id.sp_room);
        spinSelectedRooms.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Constants.NO_OF_ROOMS));

        spinSelectedRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int currentSelectedRoomCount, long l) {

                Log.e(TAG, "PREVIOUS SELECTION: " + previousSelectedRoomCount + " CURRENT SELECTION: " + currentSelectedRoomCount);

                if (currentSelectedRoomCount > previousSelectedRoomCount) {
                    //add room
                    final int roomDetailsToBeAdded = currentSelectedRoomCount - llRoomDetailsContainer.getChildCount();
                    Log.e(TAG, "CHILD COUNT: " + llRoomDetailsContainer.getChildCount());
                    Log.e(TAG, "ROOM DETAILS TO BE ADDED: " + roomDetailsToBeAdded);
                    for (int i = 0; i < roomDetailsToBeAdded; i++) {
                        Log.e(TAG, "ADDING VIEW");
                        final Room room = new Room();
                        roomList.add(room);
                        llRoomDetailsContainer.addView(new RoomView(MainActivity.this, room));
                    }
                } else if (currentSelectedRoomCount < previousSelectedRoomCount) {
                    for (int i = llRoomDetailsContainer.getChildCount() - 1; i >= currentSelectedRoomCount; i--) {
                        roomList.remove(i);
                        llRoomDetailsContainer.removeViewAt(i);
                    }
                }


                previousSelectedRoomCount = currentSelectedRoomCount;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinSelectedRooms.setSelection(0);

    }


    @Override
    public void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        String details = "";
        for (Room room : roomList) {
            sb.append("Room Details:");
            sb.append("\n");
            sb.append(room.getDetails());
            sb.append("\n");
        }
        details = details + sb;

        new AlertDialog.Builder(this)
                .setTitle("Room Details")
                .setMessage(details)
                .show();

    }
}
