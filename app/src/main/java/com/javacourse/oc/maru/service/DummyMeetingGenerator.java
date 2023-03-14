package com.javacourse.oc.maru.service;

import com.javacourse.oc.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("1","10:00","Salle 1","les stupides réformes de retraites","test@gmail.com","10-05-2023")
    );
    static List<Meeting> generateMeetings(){
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
