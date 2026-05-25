package com.example.campuseventmanager.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campuseventmanager.R;
import com.example.campuseventmanager.database.DBHelper;
import com.example.campuseventmanager.models.Event;

public class EventDetailActivity extends AppCompatActivity {

    TextView tvTitle, tvDate, tvTime, tvVenue, tvOrganizer, tvDescription;
    EditText etStudentName, etStudentEmail;
    Button btnRegister, btnDelete;
    DBHelper dbHelper;
    int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        dbHelper = new DBHelper(this);
        eventId = getIntent().getIntExtra("event_id", -1);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDate = findViewById(R.id.tvDetailDate);
        tvTime = findViewById(R.id.tvDetailTime);
        tvVenue = findViewById(R.id.tvDetailVenue);
        tvOrganizer = findViewById(R.id.tvDetailOrganizer);
        tvDescription = findViewById(R.id.tvDetailDescription);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        btnRegister = findViewById(R.id.btnRegister);
        btnDelete = findViewById(R.id.btnDeleteEvent);

        loadEventData();

        btnRegister.setOnClickListener(v -> registerStudent());
        btnDelete.setOnClickListener(v -> {
            dbHelper.deleteEvent(eventId);
            Toast.makeText(this, "Event deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void loadEventData() {
        Event event = dbHelper.getEventById(eventId);
        if (event != null) {
            tvTitle.setText(event.getTitle());
            tvDate.setText("Date: " + event.getDate());
            tvTime.setText("Time: " + event.getTime());
            tvVenue.setText("Venue: " + event.getVenue());
            tvOrganizer.setText("Organizer: " + event.getOrganizer());
            tvDescription.setText(event.getDescription());
        }
    }

    private void registerStudent() {
        String name = etStudentName.getText().toString().trim();
        String email = etStudentEmail.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter name and email", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = dbHelper.registerForEvent(eventId, name, email);
        if (success) {
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
            etStudentName.setText("");
            etStudentEmail.setText("");
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
