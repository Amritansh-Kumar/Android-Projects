package com.example.acer.newsreader.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsData {

    @Expose
    @SerializedName("title")
    private String newsTitle;

    @Expose
    @SerializedName("url")
    private String newsUrl;

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

}
