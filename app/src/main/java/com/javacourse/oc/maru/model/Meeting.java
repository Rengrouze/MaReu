package com.javacourse.oc.maru.model;
import java.util.Objects;

public class Meeting {

    private String id;
    private String time;
    private String location;
    private String subject;
    private String participants;
    private String date;


    /**
     * Constructor
     *
     * @param id
     * @param time
     * @param location
     * @param subject
     * @param participants
     */

    public Meeting(String id, String time, String location, String subject, String participants, String date) {
        this.id = id;
        this.time = time;
        this.location = location;
        this.subject = subject;
        this.participants = participants;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


}
