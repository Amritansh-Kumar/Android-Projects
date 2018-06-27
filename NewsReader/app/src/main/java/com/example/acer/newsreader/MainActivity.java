package com.example.acer.newsreader;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.acer.newsreader.Adapters.NewsAdapter;
import com.example.acer.newsreader.Database.News;
import com.example.acer.newsreader.Database.NewsDao;
import com.example.acer.newsreader.Database.NewsDatabase;
import com.example.acer.newsreader.Network.NewsApiClient;
import com.example.acer.newsreader.Network.NewsApiInterface;
import com.example.acer.newsreader.models.NewsData;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    private List<NewsData> newsDataList = new ArrayList<>();
    private NewsApiInterface newsApiInterface;
    private int idListSize;
    NewsDatabase db;
    NewsDao newsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchData();
        initializeAdapter();
        db = Room.databaseBuilder(getApplicationContext(),
                NewsDatabase.class, "news").build();
        newsDao = db.getNewsDao();
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
                        if (response.isSuccessful() && response != null) {
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
        if (newsIdsList.size() < 20) {
            idListSize = newsIdsList.size();
        } else {
            idListSize = 20;
        }
        for (int i = 0; i < idListSize; i++) {
            newsApiInterface
                    .getNews(newsIdsList.get(i))
                    .enqueue(new Callback<NewsData>() {
                        @Override
                        public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                            if (response.isSuccessful() && response != null) {
                                NewsData newsData = response.body();
                                if (newsData.getNewsTitle() != null && newsData.getNewsUrl() != null) {
                                    Log.e("list news ", newsData.toString());
                                    newsDataList.add(newsData);
                                    newsTitlesList.add(newsData.getNewsTitle());
                                    newsUrlsList.add(newsData.getNewsUrl());
//                                    readNews(newsData.getNewsUrl(), newsData.getNewsTitle());
                                    getAllNews(newsDataList);

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

    private void getAllNews(List<NewsData> newsDataList) {
        DownloadNewsData newsData = new DownloadNewsData();
        newsData.execute(newsDataList);
    }

//    private void readNews(final String newsUrl, final String newsTitle) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                String result = "";
//                URL url;
//                HttpURLConnection urlConnection = null;
//                try {
//                    url = new URL(newsUrl);
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                    InputStream stream = urlConnection.getInputStream();
//                    InputStreamReader reader = new InputStreamReader(stream);
//
//                    int data = reader.read();
//
//                    while (data != -1) {
//                        result += (char) data;
//                        data = reader.read();
//                    }
//
//                    News news = new News();
//                    news.setNewsTitle(newsTitle);
//                    news.setNewsData(result);
//
//                    newsDao.insertAllNews(news);
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }

    public class DownloadNewsData extends AsyncTask<List<NewsData>, Void, Void> {
        @Override
        protected Void doInBackground(List<NewsData>... newsData) {

            for (List<NewsData> newsdata1 : newsData) {
                for (NewsData news1 : newsdata1) {
                    String result = "";
                    URL url;
                    HttpURLConnection urlConnection = null;
                    try {
                        url = new URL(news1.getNewsUrl());
                        urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream stream = urlConnection.getInputStream();
                        InputStreamReader reader = new InputStreamReader(stream);

                        int data = reader.read();

                        while (data != -1) {
                            result += (char) data;
                            data = reader.read();
                        }

                        News news = new News();
                        news.setNewsTitle(news.getNewsTitle());
                        news.setNewsData(result);

                        newsDao.insertAllNews(news);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
            return null;
        }

    }
}
