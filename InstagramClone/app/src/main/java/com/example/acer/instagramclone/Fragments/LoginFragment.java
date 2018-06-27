package com.example.acer.instagramclone.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.acer.instagramclone.Events.SignUpEvent;
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

public class LoginFragment extends Fragment {

//    @BindView(R.id.login_email)
    EditText loginEmail;
//    @BindView(R.id.login_pass)
    EditText loginPassword;
//    @BindView(R.id.btn_login)
    Button loginButton;
//    @BindView(R.id.txt_signup)
//    TextView signUpTxt;
    private TextView signUpTxt;
    FirebaseAuth mAuth;
    private Intent intent;

    public LoginFragment(){

    }

    public static LoginFragment getInstance(){return new LoginFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(getContext(), view);
        intent = new Intent(getContext(), HomeActivity.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        loginEmail = view.findViewById(R.id.login_email);
        loginPassword = view.findViewById(R.id.login_pass);
        loginButton = view.findViewById(R.id.btn_login);
        signUpTxt = view.findViewById(R.id.txt_signup);
        signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SignUpEvent());
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin(){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("user fragment", user.toString());
//                            Toast.makeText(getContext(), user.getDisplayName()+"fhfh",
//                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else {
                            Log.d("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


//    @OnClick(R.id.txt_signup)
//    void signUpForm(){
//        EventBus.getDefault().post(new SignUpEvent());
//    }
}
