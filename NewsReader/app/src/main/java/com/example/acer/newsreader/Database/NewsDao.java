package com.example.acer.newsreader.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM News")
    List<News> getAllNews();

    @Insert
    void insertAllNews(News... news);

    @Query("DELETE FROM News")
    void deleteAllNews();

}
