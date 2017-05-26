package com.example.gerda.myhabittracker.data;

import android.provider.BaseColumns;


public class HabitContract {

    public HabitContract() {}

    public static final class HabitEntry implements BaseColumns {
        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_HABIT_DAY = "day";
        public final static String COLUMN_HABIT_WEEK = "week";
        public final static String COLUMN_HABIT_YEAR = "year";
        public final static String COLUMN_HABIT_TIME = "time_minutes";

        public static final int TIME_UNKNOWN = 0;
    }
}
