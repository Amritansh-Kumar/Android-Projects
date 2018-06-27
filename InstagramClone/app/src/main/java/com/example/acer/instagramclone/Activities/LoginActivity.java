package com.example.acer.instagramclone.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.acer.instagramclone.Events.LoginEvent;
import com.example.acer.instagramclone.Events.SignUpEvent;
import com.example.acer.instagramclone.Fragments.LoginFragment;
import com.example.acer.instagramclone.Fragments.SignUpFragment;
import com.example.acer.instagramclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        replaceFragment(LoginFragment.getInstance());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displaySignUp(SignUpEvent event){
        replaceFragment(SignUpFragment.newInstance());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayLogin(LoginEvent event){
        replaceFragment(LoginFragment.getInstance());
    }

    protected void replaceFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null) {
            for (Fragment f : fragments) {
                if (f != null) {
                    transaction.hide(f);
                }
            }
        }
        String tag = fragment.getClass().getName();
        Fragment f = manager.findFragmentByTag(tag);
        if (f == null) {
            f = fragment;
            transaction.add(R.id.login_frame, f, tag);
        } else {
            transaction.show(f);
        }
        try {
            transaction.commit();
        } catch (IllegalStateException e) {

        } catch (Exception e) {

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
