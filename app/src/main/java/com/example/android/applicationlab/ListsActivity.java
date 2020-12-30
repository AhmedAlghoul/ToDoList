package com.example.android.applicationlab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity {
    RecyclerView lists_rv;
    EditText createTask;
    ListsAdapter listAdapter;
    static List<ListItem> tasksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        createTask=findViewById(R.id.createtask);

        lists_rv=findViewById(R.id.lists_rv);
        lists_rv.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListsAdapter(this,tasksList);
        lists_rv.setAdapter(listAdapter);

    }

    public void logout(View view) {
        finish();
        Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        finish();
    }

    public void createTask(final View view) {
        String title=createTask.getText().toString();
        if (!title.isEmpty()){
            tasksList.add(new ListItem(title));
            listAdapter.notifyDataSetChanged();
            createTask.setText("");
        }
        else{
            Toast.makeText(this, "Enter the list name please ..", Toast.LENGTH_SHORT).show();
        }

    }

    private void addList() {
        Toast.makeText(this, "added successfully..", Toast.LENGTH_SHORT).show();
    }
}