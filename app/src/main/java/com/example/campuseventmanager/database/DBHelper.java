package com.example.campuseventmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.campuseventmanager.models.Event;
import com.example.campuseventmanager.models.Registration;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "campus_event_manager.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EVENTS = "events";
    public static final String TABLE_REGISTRATIONS = "registrations";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createEventsTable = "CREATE TABLE " + TABLE_EVENTS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "date TEXT, " +
                "time TEXT, " +
                "venue TEXT, " +
                "organizer TEXT, " +
                "description TEXT)";

        String createRegistrationsTable = "CREATE TABLE " + TABLE_REGISTRATIONS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "event_id INTEGER, " +
                "student_name TEXT, " +
                "student_email TEXT)";

        db.execSQL(createEventsTable);
        db.execSQL(createRegistrationsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATIONS);
        onCreate(db);
    }

    public boolean addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", event.getTitle());
        values.put("date", event.getDate());
        values.put("time", event.getTime());
        values.put("venue", event.getVenue());
        values.put("organizer", event.getOrganizer());
        values.put("description", event.getDescription());

        long result = db.insert(TABLE_EVENTS, null, values);
        return result != -1;
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS + " ORDER BY id DESC", null);

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return eventList;
    }

    public Event getEventById(int eventId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE id = ?", new String[]{String.valueOf(eventId)});

        Event event = null;
        if (cursor.moveToFirst()) {
            event = new Event(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }
        cursor.close();
        return event;
    }

    public boolean registerForEvent(int eventId, String studentName, String studentEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("event_id", eventId);
        values.put("student_name", studentName);
        values.put("student_email", studentEmail);

        long result = db.insert(TABLE_REGISTRATIONS, null, values);
        return result != -1;
    }

    public List<Registration> getAllRegistrations() {
        List<Registration> registrationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT r.id, r.event_id, r.student_name, r.student_email, e.title " +
                "FROM " + TABLE_REGISTRATIONS + " r " +
                "INNER JOIN " + TABLE_EVENTS + " e ON r.event_id = e.id " +
                "ORDER BY r.id DESC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Registration registration = new Registration(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                registrationList.add(registration);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return registrationList;
    }

    public void deleteEvent(int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REGISTRATIONS, "event_id = ?", new String[]{String.valueOf(eventId)});
        db.delete(TABLE_EVENTS, "id = ?", new String[]{String.valueOf(eventId)});
    }
}

