package com.example.recyclerview;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "musics.db";
    private static final int SCHEMA = 1;

    static final String TABLE = "albums";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ARTIST = "artist";
    public static final String COLUMN_YEAR = "year";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + TABLE + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_TITLE + " TEXT, "
                        + COLUMN_ARTIST + " TEXT, "
                        + COLUMN_YEAR + " INTEGER"
                        + ");"
        );


        db.execSQL("INSERT INTO " + TABLE + " ("
                + COLUMN_TITLE + ", " + COLUMN_ARTIST + ", " + COLUMN_YEAR + ") " +
                "VALUES ('Divisive', 'Distutbed',  2022);");

        db.execSQL("INSERT INTO " + TABLE + " ("
                + COLUMN_TITLE + ", " + COLUMN_ARTIST + ", " + COLUMN_YEAR + ") " +
                "VALUES ('Animalistic', 'Nordic Union',  2022);");

        db.execSQL("INSERT INTO " + TABLE + " ("
                + COLUMN_TITLE + ", " + COLUMN_ARTIST + ", " + COLUMN_YEAR + ") " +
                "VALUES ('Introvert', 'Saint Asonia',  2022);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
//        onCreate(db);
    }
}
