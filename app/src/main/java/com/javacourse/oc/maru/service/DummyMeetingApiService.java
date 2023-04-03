package com.javacourse.oc.maru.service;


import com.javacourse.oc.maru.model.Meeting;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        @Override
        public ArrayList<Meeting> getMeetingsFilteredByDate(Date date) throws ParseException {
                ArrayList<Meeting> result = new ArrayList<>();

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date);
                for (int i =0; i < meetings.size() ; i++) {
                        String stringDate = meetings.get(i).getDate();
                        Date cal2Date = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(cal2Date);
                      //  cal2.setTime(meetings.get(i).getDate());
                        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
                if (sameDay) result.add(meetings.get(i));
                }
                return result;
        }

        @Override
        public ArrayList<Meeting> getMeetingsFilteredByRoom(String room) {
                ArrayList<Meeting> result = new ArrayList<>();
                for (int i =0; i < meetings.size() ; i++) {
                        String room2 = meetings.get(i).getLocation();

                        //  cal2.setTime(meetings.get(i).getDate());
                        boolean sameRoom = room.equals(room2);
                        if (sameRoom) result.add(meetings.get(i));
                }
                return result;
        }

}

