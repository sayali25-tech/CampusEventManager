package com.example.campuseventmanager.models;

public class Event {
    private int id;
    private String title;
    private String date;
    private String time;
    private String venue;
    private String organizer;
    private String description;

    public Event() {
    }

    public Event(int id, String title, String date, String time, String venue, String organizer, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.organizer = organizer;
        this.description = description;
    }

    public Event(String title, String date, String time, String venue, String organizer, String description) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.organizer = organizer;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getDescription() {
        return description;
    }
}
