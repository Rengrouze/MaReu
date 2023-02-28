package com.javacourse.oc.maru.service;

import com.javacourse.oc.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1,"10:00","Salle 1","les stupides réformes de retraites",
                    new ArrayList<>(Arrays.asList("test@gmail.com","fonctionne@free.fr","code@orange.fr")), "12/01/2023"),
            new Meeting(2,"12:00","Salle 3","Les réunions qui peuvent tenir dans un mail.",
                    new ArrayList<>(Arrays.asList("test@gmail.com")),"12/01/2023"),
            new Meeting(3,"15:00","Salle 2","La fatigue globale", new ArrayList<>(Arrays.asList("code@orange.fr")),"30/01/2023")
    );
    static List<Meeting> generateMeetings(){
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
