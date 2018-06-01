package com.example.acer.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class StudentEntrydbHelper extends SQLiteOpenHelper {

    // database entries
    private static final String SQl_CREATE_ENTRIES = "CREATE TABLE " + StudentDatabaseContract.StudentEntry.TABLE_NAME + "(" +
            StudentDatabaseContract.StudentEntry.COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
            StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_NAME + " TEXT, " +
            StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO + " TEXT, " +
            StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_BRANCH + " TEXT);";

    private static final String SQL_DELETE_ENTRIES = "DELTE TABLE IF EXISTS" + StudentDatabaseContract.StudentEntry.TABLE_NAME;

    // database version and name
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "Student.db";


    public StudentEntrydbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQl_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
