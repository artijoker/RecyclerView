package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  List<MusicAlbum> albums = new ArrayList<>();
    private RecyclerView recyclerView;
    private  StateAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new StateAdapter(this, albums));
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        albums.addAll(dbAdapter.getAlbums());
        recyclerView.setAdapter(new StateAdapter(this, albums));
        dbAdapter.close();
    }
}