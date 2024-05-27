package com.example.bigproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Game> {
    private int resourceId;

    public GameAdapter(Context context, int textViewResourceId, List<Game> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Game game = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView gameImage = (ImageView) view.findViewById(R.id.game_image);
        TextView gameName = (TextView) view.findViewById(R.id.game_name);
        gameName.setText(game.getName());
        gameImage.setImageResource((game.getImageId()));
        return view;
    }
}
