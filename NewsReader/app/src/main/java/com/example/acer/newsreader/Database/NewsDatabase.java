package com.example.acer.newsreader.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsDao getNewsDao();
}
