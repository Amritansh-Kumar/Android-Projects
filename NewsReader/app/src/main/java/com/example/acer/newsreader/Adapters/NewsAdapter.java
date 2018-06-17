package com.example.acer.newsreader.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.newsreader.NewsActivity;
import com.example.acer.newsreader.R;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<String> newsTitlesList = new ArrayList<>();
    private List<String> newsUrlsList = new ArrayList<>();

    @NonNull
    @Override
    public NewsAdapter.NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_row,  parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsHolder holder, int position) {
        holder.updateNewsRecycler(position);
    }

    @Override
    public int getItemCount() {
        return newsTitlesList.size();
    }

    public void updateNewsList(List<String> newsTitlesList, List<String> newsurlsList){
        this.newsTitlesList = newsTitlesList;
        this.newsUrlsList = newsurlsList;
        notifyDataSetChanged();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView newsRow;
        private Context context;

        public NewsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context = itemView.getContext();
            newsRow = itemView.findViewById(R.id.row_text);
        }

        public void updateNewsRecycler(int position){
            newsRow.setText(newsTitlesList.get(position));
        }

        @Override
        public void onClick(View v) {
            Log.e("card clicked", "get response for the click");
            int pos = getAdapterPosition();
            Intent intent = new Intent(context, NewsActivity.class);
            intent.putExtra("url", newsUrlsList.get(pos));
            context.startActivity(intent);
        }
    }
}
