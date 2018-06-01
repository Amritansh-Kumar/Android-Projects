package com.example.acer.databasedemo;

import android.provider.BaseColumns;

/**
 * Created by acer on 28-05-2018.
 */

public final class StudentDatabaseContract {

    // to prevent this class from accidently being instianted
    private StudentDatabaseContract(){}

    // class to define the table content
    public static final class StudentEntry implements BaseColumns{

        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_STUDENT_NAME = "student_name";
        public static final String COLUMN_STUDENT_ROLL_NO = "roll_no";
        public static final String COLUMN_STUDENT_BRANCH = "branch";

    }

}
