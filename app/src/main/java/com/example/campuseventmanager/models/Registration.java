package com.example.campuseventmanager.models;

public class Registration {
    private int id;
    private int eventId;
    private String studentName;
    private String studentEmail;
    private String eventTitle;

    public Registration(int id, int eventId, String studentName, String studentEmail, String eventTitle) {
        this.id = id;
        this.eventId = eventId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.eventTitle = eventTitle;
    }

    public int getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getEventTitle() {
        return eventTitle;
    }
}

