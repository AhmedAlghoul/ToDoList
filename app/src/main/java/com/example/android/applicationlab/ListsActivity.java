package com.example.android.applicationlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity {
ImageButton Backbutton;
RecyclerView lists_rv;
ListsAdapter listAdapter;
static List<ListItem> tasksList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        Backbutton = findViewById(R.id.Backbutton);

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
//        tasksList.add(new ListItem("Ahmed","mohamed") );
//        tasksList.add(new ListItem("Ahmed","mohamed") );
//        tasksList.add(new ListItem("Ahmed","mohamed") );
//
//        lists_rv=findViewById(R.id.lists_rv);
//        lists_rv.setLayoutManager(new LinearLayoutManager(this));
//        listAdapter = new ListsAdapter(this,tasksList);
//        lists_rv.setAdapter(listAdapter);

    }

}