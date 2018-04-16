package com.example.acer.memorableplaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GroupInfo extends AppCompatActivity {

//    private ListView mGroupList;
//    private Button mAddButton, mRenewButton;
//    private EditText mName, mContact;
//    private ArrayAdapter mGroupAdapter;
//    public static List<String> mMembersList;
//    public static List<String> mContactsList;

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//
//        mMembersList = savedInstanceState.getStringArrayList("MembersList");
//        mContactsList = savedInstanceState.getStringArrayList("ContactsList");
//        mGroupAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mMembersList);
//        mGroupList.setAdapter(mGroupAdapter);
//        mGroupAdapter.notifyDataSetChanged();
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

//        SharedPreferences preferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
//        mMembersList = (List<String>) preferences.getStringSet("MembersList", (Set<String>) mMembersList);
//        mContactsList = (List<String>) preferences.getStringSet("ContactsList", (Set<String>) mContactsList);
//
////        if (savedInstanceState != null){
////            mMembersList = savedInstanceState.getStringArrayList("MembersList");
////            mContactsList = savedInstanceState.getStringArrayList("ContactsList");
////            mGroupAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mMembersList);
////            mGroupList.setAdapter(mGroupAdapter);
////            mGroupAdapter.notifyDataSetChanged();
////        }
//
////        Intent mainIntent = getIntent();
////        List<String> membersList = mainIntent.getStringArrayListExtra("membersList");
////        List<String> contactsList = mainIntent.getStringArrayListExtra("contactsList");
////
////        if (membersList != null && contactsList != null){
////            mMembersList = membersList;
////            mContactsList = contactsList;
////        }
//
//
//        mGroupList = findViewById(R.id.group_list);
//        mAddButton = findViewById(R.id.add_member_button);
//        mRenewButton = findViewById(R.id.renew_button);
//        mName = findViewById(R.id.member_name);
//        mContact = findViewById(R.id.member_contact);
//
//        mMembersList = new ArrayList<>();
//        mContactsList = new ArrayList<>();
//
//        mGroupAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mMembersList);
//        mGroupList.setAdapter(mGroupAdapter);
//
//
//
//        mAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mMembersList.size() == 0){
//                    Toast.makeText(getApplicationContext(), " first enter name of the user", Toast.LENGTH_SHORT).show();
//                }
//
//                String name = mName.getText().toString();
//                String number = mContact.getText().toString();
//
//                if (name != null && getNumber(number) == 10){
//                    mMembersList.add(name);
//                    mContactsList.add(number);
//                    mGroupAdapter.notifyDataSetChanged();
//
//                }else{
//                    if (name == ""){
//                        Toast.makeText(getApplicationContext(), "Enter a name", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(getApplicationContext(), "Enter a Valid Contact No.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                mName.setText("");
//                mContact.setText("");
//            }
//        });
//
//
//        mRenewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMembersList.clear();
//                mContactsList.clear();
//                mGroupAdapter.notifyDataSetChanged();
//            }
//        });
//
//
//        mGroupList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                mMembersList.remove(position);
//                mContactsList.remove(position);
//                mGroupAdapter.notifyDataSetChanged();
//                return true;
//            }
//        });


    }

//    private int getNumber(String number){
//        char[] numb = number.toCharArray();
//        List<Integer> contact = new ArrayList<>();
//       for (char n : numb){
//           contact.add((int) n);
//       }
//       return contact.size();
//    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putStringArrayList("MembersList", (ArrayList<String>) mMembersList);
//        outState.putStringArrayList("ContactsList", (ArrayList<String>) mContactsList);
//        super.onSaveInstanceState(outState);
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
////        Intent intent =new Intent(getApplicationContext(), MapsActivity.class);
////        intent.putStringArrayListExtra("MembersList", (ArrayList<String>) mMembersList);
////        intent.putStringArrayListExtra("ContactsList", (ArrayList<String>) mContactsList);
////        startActivity(intent);
//    }


//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences preferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putStringSet("MembersList", (Set<String>) mMembersList);
//        editor.putStringSet("ContactsList", (Set<String>) mContactsList);
//        editor.commit();
//    }
}
