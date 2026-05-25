package com.example.campuseventmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campuseventmanager.R;

public class MainActivity extends AppCompatActivity {

    Button btnAddEvent, btnViewEvents, btnMyRegistrations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddEvent = findViewById(R.id.btnAddEvent);
        btnViewEvents = findViewById(R.id.btnViewEvents);
        btnMyRegistrations = findViewById(R.id.btnMyRegistrations);

        btnAddEvent.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddEventActivity.class)));

        btnViewEvents.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, EventListActivity.class)));

        btnMyRegistrations.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MyRegistrationsActivity.class)));
    }
}
