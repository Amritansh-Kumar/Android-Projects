package com.example.acer.newsreader.Network;

import com.example.acer.newsreader.models.NewsData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsApiInterface {

    @GET("topstories.json?print=pretty")
    Call<List<Long>> getNewsList();

    @GET("item/{id}.json?print=pretty")
    Call<NewsData> getNews(@Path("id") long id);

}
