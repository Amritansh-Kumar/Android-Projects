package com.example.acer.memorableplaces;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<String> placesList;
    public static List<LatLng> locations;
    public  static ArrayAdapter adapter;
    private ListView listView;
    private Button originButton, destinationButton, checkpointButton;
    private Intent intent;

    public static void setOrigin(boolean origin) {
        MainActivity.origin = origin;
    }

    public static void setDest(boolean dest) {
        MainActivity.dest = dest;
    }

    public static boolean origin;
    public static boolean dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placesList = new ArrayList<String>();
        placesList.add("Add a new place..");

        locations = new ArrayList<LatLng>();
        locations.add(new LatLng(0, 0));

        originButton = findViewById(R.id.add_origin);
        destinationButton = findViewById(R.id.add_destination);
        checkpointButton = findViewById(R.id.add_checkpoints);
        origin = true;
        dest = true;

        intent = new Intent(getApplicationContext(), MapsActivity.class);

        originButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putStringArrayListExtra("placesList", (ArrayList<String>) placesList);
                startActivity(intent);
            }
        });


        destinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putStringArrayListExtra("placesList", (ArrayList<String>) placesList);
                startActivity(intent);
            }
        });


        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, placesList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                if (origin == false && dest == false) {
                    intent.putExtra("position", i);
                    intent.putStringArrayListExtra("placesList", (ArrayList<String>) placesList);
                    startActivity(intent);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "8542835667"));
                intent.putExtra("sms_body", "Reached" + placesList.get(position));
                startActivity(intent);
                return true;
            }
        });



    }

}
