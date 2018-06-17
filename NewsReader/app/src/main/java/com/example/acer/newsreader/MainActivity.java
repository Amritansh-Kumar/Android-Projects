package com.example.acer.newsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.acer.newsreader.Adapters.NewsAdapter;
import com.example.acer.newsreader.Network.NewsApiClient;
import com.example.acer.newsreader.Network.NewsApiInterface;
import com.example.acer.newsreader.models.NewsData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecycler;
    private NewsAdapter newsAdapter;
    private List<Long> newsIdsList = new ArrayList<>();
    private List<String> newsTitlesList = new ArrayList<>();
    private List<String> newsUrlsList = new ArrayList<>();
    private NewsApiInterface newsApiInterface;
    private int idListSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchData();
        initializeAdapter();
    }

    private void initializeAdapter() {
        newsRecycler = findViewById(R.id.rv_news_list);
        newsRecycler.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter();
        newsRecycler.setAdapter(newsAdapter);
    }

    private void fetchData() {

        newsApiInterface = NewsApiClient
                .getClient();
        getNewsIds();
    }

    private void getNewsIds() {
        newsApiInterface.getNewsList()
                .enqueue(new Callback<List<Long>>() {
                    @Override
                    public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                        if (response.isSuccessful() && response != null){
                            newsIdsList = response.body();
                            Log.e("list ids ", newsIdsList.toString());
                            getNews();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Long>> call, Throwable t) {

                    }
                });
    }

    private void getNews() {

        Log.e("get news", "called");
        if (newsIdsList.size() < 20){
            idListSize = newsIdsList.size();
        }else {
            idListSize = 20;
        }
        for (int i=0; i<idListSize; i++){
            newsApiInterface
                    .getNews(newsIdsList.get(i))
                    .enqueue(new Callback<NewsData>() {
                        @Override
                        public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                            if (response.isSuccessful() && response != null){
                                NewsData newsData = response.body();
                                if(newsData.getNewsTitle() != null && newsData.getNewsTitle() != null){
                                    Log.e("list news ", newsData.toString());
                                    newsTitlesList.add(newsData.getNewsTitle());
                                    newsUrlsList.add(newsData.getNewsUrl());
                                }
                            }
                            Log.e("adapter", "called");
                            newsAdapter.updateNewsList(newsTitlesList, newsUrlsList);
                        }

                        @Override
                        public void onFailure(Call<NewsData> call, Throwable t) {

                        }
                    });
        }
    }
}
