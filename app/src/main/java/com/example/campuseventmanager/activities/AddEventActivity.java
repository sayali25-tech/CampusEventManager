package com.example.campuseventmanager.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campuseventmanager.R;
import com.example.campuseventmanager.database.DBHelper;
import com.example.campuseventmanager.models.Event;

public class AddEventActivity extends AppCompatActivity {

    EditText etTitle, etDate, etTime, etVenue, etOrganizer, etDescription;
    Button btnSaveEvent;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        dbHelper = new DBHelper(this);

        etTitle = findViewById(R.id.etTitle);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etVenue = findViewById(R.id.etVenue);
        etOrganizer = findViewById(R.id.etOrganizer);
        etDescription = findViewById(R.id.etDescription);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);

        btnSaveEvent.setOnClickListener(v -> saveEvent());
    }

    private void saveEvent() {
        String title = etTitle.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String venue = etVenue.getText().toString().trim();
        String organizer = etOrganizer.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(date) || TextUtils.isEmpty(time)
                || TextUtils.isEmpty(venue) || TextUtils.isEmpty(organizer) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Event event = new Event(title, date, time, venue, organizer, description);
        boolean inserted = dbHelper.addEvent(event);

        if (inserted) {
            Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to add event", Toast.LENGTH_SHORT).show();
        }
    }
}
