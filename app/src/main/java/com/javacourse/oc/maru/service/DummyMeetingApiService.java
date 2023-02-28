package com.javacourse.oc.maru.service;


import com.javacourse.oc.maru.model.Meeting;

import java.util.List;
/**
 * Dummy mock for the Meetings API
 */
public class DummyMeetingApiService implements MeetingApiService {



        private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

        /**
         * {@inheritDoc}
         */
        @Override
        public List<Meeting> getMeetings() {
            return meetings;
        }

        @Override
        public void addMeeting(Meeting meeting){
                meetings.add(meeting);
        }

        @Override
        public void deleteMeeting(Meeting meeting){
                meetings.remove(meeting);
        }
}
