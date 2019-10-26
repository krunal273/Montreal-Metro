package com.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.projectfinal.model.PathAdapter;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ArrayList<String> path;
    ListView listViewPath;
    //    ArrayAdapter<String> pathAdapter;
    PathAdapter pathAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        receivePath();
    }

    private void receivePath() {
        listViewPath = findViewById(R.id.listViewPath);
        path = (ArrayList<String>) getIntent().getExtras().getSerializable("path");
//        pathAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , path);
        pathAdapter = new PathAdapter(this, path);
        listViewPath.setAdapter(pathAdapter);

    }
}
