package com.javacourse.oc.maru;

import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;
import com.javacourse.oc.maru.service.DummyMeetingGenerator;
import com.javacourse.oc.maru.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess(){
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertTrue(meetings.containsAll(expectedMeetings));
    }
    @Test
    public void createMeetingWithSuccess(){
        Meeting meetingToCreate = new Meeting("7","14:00","Salle 2","Test","test6@gmail.com","22-05-2023");
        service.addMeeting(meetingToCreate);
        assertTrue(service.getMeetings().contains(meetingToCreate));
    }
    @Test
    public void deleteMeetingWithSuccess(){
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }
    @Test
    public void filterMeetingByDateWithSuccess() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Meeting meetingToCreate1 = new Meeting("10","09:00","Salle 1", "Reunion 1", "mail1@gmail.com","10-07-2023");
        Meeting meetingToCreate2 = new Meeting("11","09:00","Salle 2", "Reunion 2", "mail1@gmail.com","11-07-2023");
        Meeting meetingToCreate3 = new Meeting("12","09:00","Salle 3", "Reunion 3", "mail1@gmail.com","12-07-2023");
        service.addMeeting(meetingToCreate1);
        service.addMeeting(meetingToCreate2);
        service.addMeeting(meetingToCreate3);
        // Appel à la méthode pour filtrer les réunions par date
        List<Meeting> filteredMeetings = service.getMeetingsFilteredByDate(dateFormat.parse("10-07-2023"));

        // Vérification que seul meetingToCreate1 est présent dans la liste filtrée
        assertTrue(filteredMeetings.contains(meetingToCreate1));
        assertFalse(filteredMeetings.contains(meetingToCreate2));
        assertFalse(filteredMeetings.contains(meetingToCreate3));

    }
    @Test
    public void filterMeetingByRoomWithSuccess(){
        Meeting meetingToCreate1 = new Meeting("10","09:00","Salle 1", "Reunion 1", "mail1@gmail.com","10-07-2023");
        Meeting meetingToCreate2 = new Meeting("11","09:00","Salle 2", "Reunion 2", "mail1@gmail.com","11-07-2023");
        Meeting meetingToCreate3 = new Meeting("12","09:00","Salle 3", "Reunion 3", "mail1@gmail.com","12-07-2023");
        service.addMeeting(meetingToCreate1);
        service.addMeeting(meetingToCreate2);
        service.addMeeting(meetingToCreate3);
        List<Meeting> filteredMeetings = service.getMeetingsFilteredByRoom("Salle 1");
        assertTrue(filteredMeetings.contains(meetingToCreate1));
        assertFalse(filteredMeetings.contains(meetingToCreate2));
        assertFalse(filteredMeetings.contains(meetingToCreate3));


    }
}
