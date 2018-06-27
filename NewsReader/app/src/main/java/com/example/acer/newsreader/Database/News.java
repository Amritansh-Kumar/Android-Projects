package com.example.acer.newsreader.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class News {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "news_title")
    private String newsTitle;

    @ColumnInfo(name = "news_data")
    private String newsData;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsData() {
        return newsData;
    }

    public void setNewsData(String newsData) {
        this.newsData = newsData;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
