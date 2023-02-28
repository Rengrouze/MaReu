package com.javacourse.oc.maru.service;

import com.javacourse.oc.maru.model.Meeting;

import java.util.List;


public interface MeetingApiService {
    /**
     * Get all Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);


}
