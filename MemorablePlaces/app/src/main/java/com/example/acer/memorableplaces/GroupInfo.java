package com.example.acer.memorableplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupInfo extends AppCompatActivity {

    private ListView mGroupList;
    private Button mAddButton, mRenewButton;
    private EditText mName, mContact;
    private ArrayAdapter mGroupAdapter;
    public static List<String> mMembersList;
    public static List<String> mContactsList;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMembersList = savedInstanceState.getStringArrayList("MembersList");
        mContactsList = savedInstanceState.getStringArrayList("ContactsList");
        mGroupAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mMembersList);
        mGroupList.setAdapter(mGroupAdapter);
        mGroupAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        mMembersList = new ArrayList<>();
        mContactsList = new ArrayList<>();

        if (savedInstanceState != null){
            mMembersList = savedInstanceState.getStringArrayList("MembersList");
            mContactsList = savedInstanceState.getStringArrayList("ContactsList");
            mGroupAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mMembersList);
            mGroupList.setAdapter(mGroupAdapter);
            mGroupAdapter.notifyDataSetChanged();
        }

        mGroupList = findViewById(R.id.group_list);
        mAddButton = findViewById(R.id.add_member_button);
        mRenewButton = findViewById(R.id.renew_button);
        mName = findViewById(R.id.member_name);
        mContact = findViewById(R.id.member_contact);


        mGroupAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mMembersList);
        mGroupList.setAdapter(mGroupAdapter);



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
        outState.putStringArrayList("MembersList", (ArrayList<String>) mMembersList);
        outState.putStringArrayList("ContactsList", (ArrayList<String>) mContactsList);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(getApplicationContext(), MapsActivity.class);
        intent.putStringArrayListExtra("MembersList", (ArrayList<String>) mMembersList);
        intent.putStringArrayListExtra("ContactsList", (ArrayList<String>) mContactsList);
        startActivity(intent);
    }
}
