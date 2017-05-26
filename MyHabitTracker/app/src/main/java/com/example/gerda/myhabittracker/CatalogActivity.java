package com.example.gerda.myhabittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gerda.myhabittracker.data.HabitContract;
import com.example.gerda.myhabittracker.data.HabitDbHelper;

public class CatalogActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private HabitDbHelper  mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        read();
    }

    private Cursor read() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_DAY,
                HabitContract.HabitEntry.COLUMN_HABIT_WEEK,
                HabitContract.HabitEntry.COLUMN_HABIT_YEAR,
                HabitContract.HabitEntry.COLUMN_HABIT_TIME
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);
        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int dayColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_DAY);
            int weekColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_WEEK);
            int yearColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_YEAR);
            int timeColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_TIME);

            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDay = cursor.getString(dayColumnIndex);
                int currentWeek = cursor.getInt(weekColumnIndex);
                int currentYear = cursor.getInt(yearColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);


            }
        } finally {
            // This releases all its resources and makes it invalid.
            cursor.close();
        }
        return cursor;
    }

    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Running habit attributes are the values.

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "Running");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DAY, "Wednesday");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_WEEK, 50);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_YEAR, 2016);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TIME, 28);

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
    }
}
