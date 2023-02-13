package com.javacourse.oc.maru.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Meeting {

    private long id;
    private String time;
    private String location;
    private String subject;
    private ArrayList<String> participants;
    private Date date;


    /**
     * Constructor
     * @param id
     * @param time
     * @param location
     * @param subject
     * @param participants
     */

    public Meeting(int id, String time, String location, String subject, ArrayList<String> participants) {
        this.id = id;
        this.time = time;
        this.location = location;
        this.subject = subject;
        this.participants = participants;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting metting = (Meeting) o;
        return Objects.equals(id, metting.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
