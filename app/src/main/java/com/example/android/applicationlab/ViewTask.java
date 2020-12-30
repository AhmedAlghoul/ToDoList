package com.example.android.applicationlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.applicationlab.DailyListActivity.currentList;

public class ViewTask extends AppCompatActivity {
    private EditText task_title;
    private EditText task_desc;
    private Task task;
    private TextView list_name;
    private ListItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        item = currentList;
        task = getIntent().getParcelableExtra("task");
        list_name = findViewById(R.id.list_name);
        task_title = findViewById(R.id.task_title);
        task_desc = findViewById(R.id.task_desc);
        task_title.setText(task.getTitle());
        task_desc.setText(task.getBody());
        list_name.setText(item.getTitle());

    }

    public void editTask(View view) {
        finish();
        Toast.makeText(this, "edited", Toast.LENGTH_SHORT).show();
    }

    public void deleteTask(View view) {
        finish();
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        finish();
    }
}