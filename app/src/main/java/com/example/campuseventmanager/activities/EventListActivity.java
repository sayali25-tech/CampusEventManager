package com.example.campuseventmanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campuseventmanager.R;
import com.example.campuseventmanager.adapters.EventAdapter;
import com.example.campuseventmanager.database.DBHelper;
import com.example.campuseventmanager.models.Event;

import java.util.List;

public class EventListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        recyclerView = findViewById(R.id.recyclerViewEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Event> eventList = dbHelper.getAllEvents();
        recyclerView.setAdapter(new EventAdapter(this, eventList));
    }
}

