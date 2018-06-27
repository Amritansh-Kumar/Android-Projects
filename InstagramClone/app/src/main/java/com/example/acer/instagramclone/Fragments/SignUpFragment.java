package com.example.acer.instagramclone.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.instagramclone.Activities.HomeActivity;
import com.example.acer.instagramclone.Activities.LoginActivity;
import com.example.acer.instagramclone.Events.LoginEvent;
import com.example.acer.instagramclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment {

//    @BindView(R.id.su_email)
    EditText signUpEmail;
//    @BindView(R.id.su_pass)
    EditText signUpPassword;
//    @BindView(R.id.su_user_name)
    EditText signUpUser;
//    @BindView(R.id.txt_login)
    TextView loginTxt;
//    @BindView(R.id.btn_sign_up)
    private Button signUpBtn;
    private FirebaseAuth mAuth;
    private Intent intent;
    private static final String USERNAME = "userName";
    private static final String EMAIL = "email";
    private static final String UID = "userId";


    public SignUpFragment() {
    }


    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(getContext(), view);
        intent = new Intent(getContext(), HomeActivity.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        signUpEmail = view.findViewById(R.id.su_email);
        signUpPassword = view.findViewById(R.id.su_pass);
        signUpUser = view.findViewById(R.id.su_user_name);
        loginTxt = view.findViewById(R.id.txt_login);
        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new LoginEvent());
            }
        });
        signUpBtn = view.findViewById(R.id.btn_sign_up);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });
    }

//    @OnClick(R.id.btn_sign_up)
    void createNewUser(){
        final String email = signUpEmail.getText().toString();
        String password = signUpPassword.getText().toString();
        final String userName = signUpUser.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "createUserWithEmail:success");
                            Toast.makeText(getContext(), "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("user fragment", user.toString());
                            intent.putExtra("username", userName);
                            startActivity(intent);
                        } else {
                            Log.d("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
