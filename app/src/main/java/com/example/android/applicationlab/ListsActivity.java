package com.example.android.applicationlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity {
    RecyclerView lists_rv;
    EditText createTask;
    public static ListsAdapter listAdapter;
    static List<ListItem> tasksList = new ArrayList<>();
    public static ValueEventListener valueEventListener;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static String currentListId = "non";
    public static String currentListTitle = "";
    private static DatabaseReference mDatabase;
    RecyclerView searchRecyclerView;
    SearchAdapter searchAdapter;
    SearchView searchView;
    ArrayList<Task> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        createTask = findViewById(R.id.createtask);
        searchRecyclerView = findViewById(R.id.recyclerView_search);
        searchView = findViewById(R.id.searchview2);
        filteredList = new ArrayList<>();
        searchAdapter = new SearchAdapter(this, filteredList);
        searchRecyclerView.setAdapter(searchAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        lists_rv = findViewById(R.id.lists_rv);
        lists_rv.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListsAdapter(this, tasksList);
        lists_rv.setAdapter(listAdapter);
        initTaskData();
        initListData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() == 0) {
                    lists_rv.setVisibility(View.VISIBLE);
                    searchRecyclerView.setVisibility(View.INVISIBLE);
                } else {
                    searchRecyclerView.setVisibility(View.VISIBLE);
                    lists_rv.setVisibility(View.INVISIBLE);
                    filter(s);
                }
                return false;
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        finish();
    }

    public void createTask(final View view) {
        String title = createTask.getText().toString();
        if (!title.isEmpty()) {
            ListItem listItem = new ListItem(title);
            tasksList.add(listItem);
            listAdapter.notifyDataSetChanged();
            addToListFirebase(listItem);
            createTask.setText("");
        } else {
            Toast.makeText(this, "Enter the list name please ..", Toast.LENGTH_SHORT).show();
        }

    }

    private void addToListFirebase(ListItem item) {
        String userId = FirebaseAuth.getInstance().getUid();
        assert userId != null;
        mDatabase.child("User").child(userId).child("ListItem").child(item.id).setValue(item);
    }

    public static void initListData() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("ListItem")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tasksList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ListItem listItem = snapshot.getValue(ListItem.class);
                            tasksList.add(listItem);
                        }
                        listAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    public static void initTaskData() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("Task")
                .addValueEventListener(valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tasks.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Task task = snapshot.getValue(Task.class);
                            assert task != null;
                            tasks.add(task);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    public static void addTaskToFirebase(Task task) {
        String userId = FirebaseAuth.getInstance().getUid();
        assert userId != null;
        mDatabase.child("User").child(userId).child("Task").child(task.id).setValue(task);
    }

    public static void deleteTaskFromFirebase(Task task) {
        DatabaseReference taskRef = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("Task").child(task.getId());
        taskRef.removeValue();

    }

    private void filter(String text) {
        filteredList.clear();
        for (Task item : tasks) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                searchAdapter.notifyDataSetChanged();
            }
        }

    }


}