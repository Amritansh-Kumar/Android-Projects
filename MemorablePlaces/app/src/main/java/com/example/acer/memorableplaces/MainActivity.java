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
    import android.widget.Toast;

    import com.google.android.gms.maps.model.LatLng;

    import java.util.ArrayList;
    import java.util.List;

    public class MainActivity extends AppCompatActivity {

        public static List<String> placesList;
        public static List<LatLng> locations;
        public static ArrayAdapter adapter;
        private ListView listView;
        private Button mTripButton;
        private Button mgroupInfoButton;

        public static void setFirsttAdd(boolean first) {
            firsttAdd = first;
        }

        public static boolean firsttAdd = true;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if (savedInstanceState != null) {
                firsttAdd = savedInstanceState.getBoolean("firsttAdd");
            }

            placesList = new ArrayList<String>();
            placesList.add("Add checkpoints");
            mTripButton = findViewById(R.id.new_tour);
            mgroupInfoButton = findViewById(R.id.group_info);

            locations = new ArrayList<LatLng>();
            locations.add(new LatLng(0, 0));

            listView = findViewById(R.id.listView);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, placesList);

            mTripButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    placesList.clear();
                    locations.clear();
                    adapter.notifyDataSetChanged();
                    placesList.add("Add checkpoints");
                }
            });


            mgroupInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), GroupInfo.class);
                    startActivity(intent);
                }
            });

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        if (placesList.size() == 1) {
                            intent.putExtra("first", 1);
                        }
                        intent.putExtra("position", i);
                        intent.putStringArrayListExtra("placesList", (ArrayList<String>) placesList);
                        intent.putExtra("firstAdd", firsttAdd);
                        startActivity(intent);
                    }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    sendMessages();
                    return true;
                }
            });

        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean("first", firsttAdd);
        }

        private void sendMessages(){
            List<String> numberList = GroupInfo.mContactsList;

            if (numberList != null) {
                String toNumbers = "";
                for (String s : numberList) {
                    toNumbers = toNumbers + s + ";";
                }
                toNumbers = toNumbers.substring(0, toNumbers.length() - 1);
                String message = GroupInfo.mMembersList.get(0) + "Reached";

                Uri sendSmsTo = Uri.parse("smsto:" + toNumbers);
                Intent intent = new Intent(
                        android.content.Intent.ACTION_SENDTO, sendSmsTo);
                intent.putExtra("sms_body", message);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "No contacts to send messages", Toast.LENGTH_SHORT).show();
            }
        }
    }