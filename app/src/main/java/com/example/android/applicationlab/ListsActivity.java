package com.example.android.applicationlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ListsActivity extends AppCompatActivity {
ImageButton Backbutton;
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
    }
}