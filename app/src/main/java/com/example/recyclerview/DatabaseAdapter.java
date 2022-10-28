package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_TITLE,
                DatabaseHelper.COLUMN_ARTIST,
                DatabaseHelper.COLUMN_YEAR
        };
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<MusicAlbum> getAlbums(){
        ArrayList<MusicAlbum> albums = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int indexColumnId = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int indexColumnTitle = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
            int indexColumnArtist = cursor.getColumnIndex(DatabaseHelper.COLUMN_ARTIST);
            int indexColumnYear = cursor.getColumnIndex(DatabaseHelper.COLUMN_YEAR);

            int id = cursor.getInt(indexColumnId);
            String title = cursor.getString(indexColumnTitle);
            String artist = cursor.getString(indexColumnArtist);
            int year = cursor.getInt(indexColumnYear);
            albums.add(new MusicAlbum(title, artist, year));
        }
        cursor.close();
        return albums;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public MusicAlbum getAlbum(long id){
        MusicAlbum album = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            int indexColumnTitle = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
            int indexColumnArtist = cursor.getColumnIndex(DatabaseHelper.COLUMN_ARTIST);
            int indexColumnYear = cursor.getColumnIndex(DatabaseHelper.COLUMN_YEAR);

            String title = cursor.getString(indexColumnTitle);
            String artist = cursor.getString(indexColumnArtist);
            int year = cursor.getInt(indexColumnYear);
            album = new MusicAlbum(title, artist, year);
        }
        cursor.close();
        return  album;
    }
}
