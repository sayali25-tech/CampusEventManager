package com.example.campuseventmanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campuseventmanager.R;
import com.example.campuseventmanager.adapters.RegistrationAdapter;
import com.example.campuseventmanager.database.DBHelper;

public class MyRegistrationsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_registrations);

        recyclerView = findViewById(R.id.recyclerViewRegistrations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        recyclerView.setAdapter(new RegistrationAdapter(this, dbHelper.getAllRegistrations()));
    }
}
