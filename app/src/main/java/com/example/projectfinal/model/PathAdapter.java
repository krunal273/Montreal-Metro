package com.example.projectfinal.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectfinal.R;

import java.nio.file.Path;
import java.util.ArrayList;

public class PathAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> path;

    public PathAdapter(Context context, ArrayList<String> path) {
        this.context = context;
        this.path = path;
    }

    @Override
    public int getCount() {
        return path.size();
    }

    @Override
    public Object getItem(int i) {
        return path.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textViewStation;
        View oneItem;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // the conversion of the layout to a view

        oneItem = inflater.inflate(R.layout.blue, viewGroup, false);

        textViewStation = oneItem.findViewById(R.id.textViewStation);

        String stationName = path.get(i);

        textViewStation.setText(stationName);

        return oneItem;
    }
}
