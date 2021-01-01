package com.example.android.applicationlab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class DailyListActivity extends AppCompatActivity {
    private ListItem item;
    private TextView result;
    private RecyclerView recyclerView;
    private TasksAdapter tasksAdapter;
    private ArrayList<Task> tasks;
    private ArrayList<Task> filteredTasks;
    public static ListItem currentList;
    public static int currentTaskPosition = 0;
    public static ArrayList<String> tasksToDelete;
    public static String idToDelete="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_list);
        tasks = new ArrayList<>();
        filteredTasks = new ArrayList<>();
        tasksToDelete=new ArrayList<>();
        result = findViewById(R.id.result);
        item = getIntent().getParcelableExtra("list");
        result.setText(item.getTitle() + " List");
        currentList = item;
        tasks.addAll(ListsActivity.tasks);
        for (int i=0;i<tasks.size();i++){
            if (tasks.get(i).getListId().equals(ListsActivity.currentListId))
            filteredTasks.add(tasks.get(i));
        }
        recyclerView = findViewById(R.id.recyclerView);
        tasksAdapter = new TasksAdapter(this, filteredTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(tasksAdapter);
        recyclerView.setLayoutManager(layoutManager);
        tasksAdapter.setOnClickTask(new TasksAdapter.OnClickTask() {
                                        @Override
                                        public void onClickTask(Task task, int position) {
                                            Intent intent = new Intent(DailyListActivity.this, ViewTask.class);
                                            intent.putExtra("task", task);
                                            startActivityForResult(intent, 100);
                                            currentTaskPosition = position;
                                            idToDelete=task.getId();
                                        }
                                    }
        );


    }

    public void onClickBack(View view) {
        finish();
    }

    public void createTask(View view) {
        Intent intent = new Intent(this, ViewTask.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10 && requestCode == 100) {
            Toast.makeText(this, "res", Toast.LENGTH_SHORT).show();
            Task task = data.getParcelableExtra("task");
            addTask(task);
        } if (resultCode == 30 && requestCode == 100) {
            Toast.makeText(this, "res", Toast.LENGTH_SHORT).show();
            Task task = data.getParcelableExtra("task");
            editTask(task);
        }
        if (resultCode == 20 && requestCode == 100) {
            Toast.makeText(this, "res", Toast.LENGTH_SHORT).show();
            Task task = data.getParcelableExtra("task");
            deleteTask(task);
        }
    }

    private void editTask(Task task) {


        ListsActivity.tasks.set(currentTaskPosition,task);
        filteredTasks.set(currentTaskPosition,task);
        tasksAdapter.notifyDataSetChanged();
        DatabaseReference taskRef = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("Task").child(idToDelete);
        taskRef.removeValue();
        ListsActivity.addTaskToFirebase(task);
    }

    private void deleteTask(Task task) {
        tasks.remove(currentTaskPosition);
        ListsActivity.deleteTaskFromFirebase(task);
        tasksAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Task deleted successfully." + tasks.size(), Toast.LENGTH_SHORT).show();

    }


    private void addTask(Task task) {
        ListsActivity.tasks.add(task);
        filteredTasks.add(task);
        tasksAdapter.notifyDataSetChanged();
        ListItem item=getIntent().getParcelableExtra("list");
        item.setNumber(item.getNumber()+1);
        ListsActivity.addTaskToFirebase(task);
        Toast.makeText(this, "Task added successfully.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasksAdapter.notifyDataSetChanged();

    }

    public void deleteSetOfTasks(View view) {
        for (int i=0;i<tasks.size();i++){
        for (int j=0;j<tasksToDelete.size();j++) {
            if (tasks.get(i).getId().equals(tasksToDelete.get(j))) {
                tasksToDelete.remove(j);
                tasks.remove(i);
            }


        }
            ListsActivity.deleteTaskFromFirebase(tasks.get(i));
        }
        tasksAdapter=new TasksAdapter(this,tasks);
        recyclerView.setAdapter(tasksAdapter);
    }
}