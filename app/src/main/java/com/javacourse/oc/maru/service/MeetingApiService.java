package com.javacourse.oc.maru.service;

import com.javacourse.oc.maru.model.Meeting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface MeetingApiService {
    /**
     * Get all Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);


    ArrayList<Meeting> getMeetingsFilteredByDate(Date date) throws ParseException;

    ArrayList<Meeting> getMeetingsFilteredByRoom(String room);
}
