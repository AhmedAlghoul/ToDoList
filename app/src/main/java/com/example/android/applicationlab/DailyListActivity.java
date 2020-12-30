package com.example.android.applicationlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class DailyListActivity extends AppCompatActivity {
    private ListItem item;
    private TextView result;
    private RecyclerView recyclerView;
    private TasksAdapter tasksAdapter;
    private ArrayList<Task> tasks;
    public static ListItem currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_list);
        tasks=new ArrayList<>();
        result = findViewById(R.id.result);
        item = getIntent().getParcelableExtra("list");
        result.setText(item.getTitle()+" List");

        tasks.add(new Task("meeting","no meeting here", "22/12/2020"));
        currentList=item;
        recyclerView = findViewById(R.id.recyclerView);
        tasksAdapter=new TasksAdapter(this,tasks);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(tasksAdapter);
        recyclerView.setLayoutManager(layoutManager);




    }

    public void onClickBack(View view) {
        finish();
    }
}