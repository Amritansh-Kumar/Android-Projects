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
    import android.widget.EditText;
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


        private ListView mGroupList;
        private Button mAddButton, mRenewButton;
        private EditText mName, mContact;
        private ArrayAdapter mGroupAdapter;
        public static List<String> mMembersList;
        public static List<String> mContactsList;

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


            locations = new ArrayList<LatLng>();
            locations.add(new LatLng(0, 0));

            listView = findViewById(R.id.listView);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, placesList);

            mGroupList = findViewById(R.id.group_list);
            mAddButton = findViewById(R.id.add_member_button);
            mRenewButton = findViewById(R.id.renew_button);
            mName = findViewById(R.id.member_name);
            mContact = findViewById(R.id.member_contact);

            mMembersList = new ArrayList<>();
            mContactsList = new ArrayList<>();

            mGroupAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mMembersList);
            mGroupList.setAdapter(mGroupAdapter);


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
                    sendMessages(placesList.get(position));
                    return true;
                }
            });


            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMembersList.size() == 0){
                        Toast.makeText(getApplicationContext(), " first enter name of the user", Toast.LENGTH_SHORT).show();
                    }

                    String name = mName.getText().toString();
                    String number = mContact.getText().toString();

                    if (name != null && getNumber(number) == 10){
                        mMembersList.add(name);
                        mContactsList.add(number);
                        mGroupAdapter.notifyDataSetChanged();

                    }else{
                        if (name == ""){
                            Toast.makeText(getApplicationContext(), "Enter a name", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Enter a Valid Contact No.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    mName.setText("");
                    mContact.setText("");
                }
            });


            mRenewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMembersList.clear();
                    mContactsList.clear();
                    mGroupAdapter.notifyDataSetChanged();
                }
            });


            mGroupList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    mMembersList.remove(position);
                    mContactsList.remove(position);
                    mGroupAdapter.notifyDataSetChanged();
                    return true;
                }
            });


        }

        private int getNumber(String number){
            char[] numb = number.toCharArray();
            List<Integer> contact = new ArrayList<>();
            for (char n : numb){
                contact.add((int) n);
            }
            return contact.size();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean("first", firsttAdd);
        }

        private void sendMessages(String place){
            List<String> numberList =mContactsList;

            if (numberList != null) {
                String toNumbers = "";
                for (String s : numberList) {
                    toNumbers = toNumbers + s + ";";
                }
                toNumbers = toNumbers.substring(0, toNumbers.length() - 1);
                String message = mMembersList.get(0) + "Reached" + place;

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