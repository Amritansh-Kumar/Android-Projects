package com.example.acer.instagramclone.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        public UserHolder(View itemView) {
            super(itemView);
        }
    }

    public void updateUsersList(){

    }
}
