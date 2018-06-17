package com.example.acer.newsreader.Network;

import android.util.Log;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiClient {

    public static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";
    public static NewsApiInterface newsApiInterface = null;


    public static NewsApiInterface getClient() {
        if (newsApiInterface == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.interceptors().add(logging);

            OkHttpClient client = builder.build();

            newsApiInterface = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build().create(NewsApiInterface.class);
        }
        return newsApiInterface;
    }
}
