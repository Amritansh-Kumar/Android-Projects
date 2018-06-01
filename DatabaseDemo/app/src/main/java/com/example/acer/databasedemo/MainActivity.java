package com.example.acer.databasedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name_text)
    EditText nameText;
    @BindView(R.id.roll_no_text)
    EditText rollNoText;
    @BindView(R.id.branch_text)
    EditText branchText;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.add_button)
    Button addButton;
    @BindView(R.id.search_button)
    Button searchButton;
    @BindView(R.id.delete_button)
    Button deleteButton;
    @BindView(R.id.displayText)
    TextView displayText;

    StudentEntrydbHelper mdbHelper;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mdbHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mdbHelper = new StudentEntrydbHelper(getApplicationContext());

    }

    @OnClick(R.id.add_button)
     void onAddClick(){
        SQLiteDatabase db =  mdbHelper.getWritableDatabase();

        // creating a new map for values, where column names are keys
        ContentValues values = new ContentValues();
        values.put(StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_NAME, nameText.getText().toString());
        values.put(StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO, rollNoText.getText().toString());
        values.put(StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_BRANCH, branchText.getText().toString());

        // insert the new row, returning the primary key value for the new row
        long newRowId = db.insert(StudentDatabaseContract.StudentEntry.TABLE_NAME, null, values);
        nameText.setText("");
        rollNoText.setText("");
        branchText.setText("");
        Toast.makeText(getApplicationContext(), "Entry Added to DB", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.delete_button)
     void onDeleteClick(){
        displayText.setText("");
        SQLiteDatabase db = mdbHelper.getWritableDatabase();

        // filter resuly WHERE rollno. = entered rollno.
        String selection = StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO + " =?";
        String selectionArgs[] = {String.valueOf(searchText.getText())};

        db.delete(StudentDatabaseContract.StudentEntry.TABLE_NAME, selection, selectionArgs);
        searchText.setText("");
        Toast.makeText(getApplicationContext(), "entry deleted",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.search_button)
     void onSearchClick(){
        SQLiteDatabase db = mdbHelper.getReadableDatabase();

        // defines which columns from the data base we will use
        String projection[] = {
                BaseColumns._ID,
                StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_NAME,
                StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO,
                StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_BRANCH
        };

        // filter resuly WHERE rollno. = entered rollno.
        String selection = StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO + " =?";
        String selectionArgs[] = {String.valueOf(searchText.getText())};

        String sortOrder = StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO + " DESC";

        Cursor cursor = db.query(StudentDatabaseContract.StudentEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(StudentDatabaseContract.StudentEntry._ID));
            Log.e("show column", String.valueOf(itemId));
            Log.e("show column", String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow
                    (StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO))));


            String studentDetails = "Name: " +  String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow
                    (StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_NAME))) + "\n" +
                    "Roll no. " + String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow
                    (StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_ROLL_NO))) + "\n" +
                    "Branch: " + String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow
                    (StudentDatabaseContract.StudentEntry.COLUMN_STUDENT_BRANCH)));
            displayText.setText(studentDetails);
        }else{
            Toast.makeText(getApplicationContext(), "entry not found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }



}
