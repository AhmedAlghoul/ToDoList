package com.example.android.applicationlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.android.applicationlab.DailyListActivity.currentList;

public class ViewTask extends AppCompatActivity {
    private EditText task_title;
    private EditText task_desc;
    private Task task;
    private TextView list_name;
    private ListItem item;
    private boolean is_edit;
    private TextView edit, textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        textViewDate = findViewById(R.id.textViewDate);
        list_name = findViewById(R.id.list_name);
        task_title = findViewById(R.id.task_title);
        task_desc = findViewById(R.id.task_desc);
        item = currentList;
        edit = findViewById(R.id.edit);
        if (getIntent().hasExtra("task")) {
            is_edit = true;
            task = getIntent().getParcelableExtra("task");
            task_title.setText(task.getTitle());
            task_desc.setText(task.getBody());
            list_name.setText(item.getTitle());
            textViewDate.setText(task.getDate());
        } else {
            is_edit = false;
            edit.setText("Add");
        }

    }

    public void editTask(View view) {
        task = new Task(ListsActivity.currentListId);
        task.setTitle(task_title.getText().toString());
        task.setBody(task_desc.getText().toString());
        long currentTimeMillis=System.currentTimeMillis();
        Date date=new Date(currentTimeMillis);
        SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String dateString=FORMAT.format(date);
        task.setDate(dateString);
        if (is_edit) {
            edit(task);
        } else {
            addNewTask(task);
        }
        finish();

    }


    public void deleteTask(View view) {
        delete(task);
        finish();

    }

    public void back(View view) {
        finish();
    }

    private void addNewTask(Task task) {
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(10, intent);
    }

    private void edit(Task task) {
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(30, intent);
        Toast.makeText(this, "edited", Toast.LENGTH_SHORT).show();
    }

    private void delete(Task task) {
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(20, intent);

    }
}