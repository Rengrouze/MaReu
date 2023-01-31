package com.javacourse.oc.maru.service;

import com.javacourse.oc.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1,"10:00","salle 1","les stupides réformes de retraites",
                    new ArrayList<>(Arrays.asList("test@gmail.com","fonctionne@free.fr","code@orange.fr"))),
            new Meeting(2,"12:00","salle 1","Les réunions qui peuvent tenir dans un mail.",
                    new ArrayList<>(Arrays.asList("test@gmail.com")))
    );
    static List<Meeting> generateMeetings(){
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
