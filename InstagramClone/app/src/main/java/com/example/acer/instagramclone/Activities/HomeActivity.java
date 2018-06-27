package com.example.acer.instagramclone.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.example.acer.instagramclone.Adapters.UsersAdapter;
import com.example.acer.instagramclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_users)
    RecyclerView usersRecycler;
    private UsersAdapter usersAdapter;
    private String userName;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        usersRecycler.setLayoutManager(new LinearLayoutManager(this));
        updateRecyclerView();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            updateUserDetails(user);
            updateDatabase(user);
        }else {
            Log.d("TAG", "user not authenticated");
        }

        getUsersList();
    }

    private void updateRecyclerView() {
        usersAdapter = new UsersAdapter();
        usersRecycler.setAdapter(usersAdapter);
        userName = getIntent().getStringExtra("username");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:
                mAuth.signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
        }
        return true;
    }

    private void updateUserDetails(FirebaseUser user) {

        if (userName!=null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(userName)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "User profile updated.");
                            }
                        }
                    });
        }

    }

    private void updateDatabase(FirebaseUser user) {
        ref.child("Users").child(user.getUid()).child("email").setValue(user.getEmail());
        ref.child("Users").child(user.getUid()).child("username").setValue(user.getDisplayName());
    }

    private void getUsersList() {

    }

}
