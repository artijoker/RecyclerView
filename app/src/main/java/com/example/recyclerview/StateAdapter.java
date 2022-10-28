package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<MusicAlbum> albums;

    StateAdapter(Context context, List<MusicAlbum> albums) {
        this.albums = albums;
        this.inflater = LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (albums.size() != 0){
            MusicAlbum album = albums.get(position);
            holder.coverView.setImageResource(R.drawable.music);
            holder.titleView.setText(album.getTitle());
            holder.artistView.setText(album.getArtist());
            String year = Integer.toString(album.getReleaseYear());
            holder.yearView.setText(year);
        }
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView coverView;
        final TextView titleView, artistView, yearView;
        ViewHolder(View view){
            super(view);
            coverView = view.findViewById(R.id.cover);
            titleView = view.findViewById(R.id.title);
            artistView = view.findViewById(R.id.artist);
            yearView = view.findViewById(R.id.year);
        }
    }
}
