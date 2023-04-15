package com.javacourse.oc.maru.service;

import com.javacourse.oc.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * cette classe génére une liste de réunion utilisable à des fins de test
 */
public abstract class DummyMeetingGenerator {
    /**
     * une liste de réunion.
     */
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("1","10:00","Salle 1","l'interet du vote.","test@gmail.com","10-05-2023"),
            new Meeting("2","11:30","Salle 2","Discussion sur les ventes","test2@gmail.com","12-05-2023"),
            new Meeting("3","14:00","Salle 3","Présentation de la stratégie marketing","test3@gmail.com","15-05-2023"),
            new Meeting("4","16:00","Salle 2","Réunion de suivi du projet X","test4@gmail.com","18-05-2023"),
            new Meeting("5","09:30","Salle 2","Les bonnes pratiques de sécurité informatique","test5@gmail.com","20-05-2023"),
            new Meeting("6","13:00","Salle 1","Réunion avec les partenaires commerciaux","test6@gmail.com","22-05-2023")

    );

    /**
     * Génére une liste de réunion
     * @return une liste de {@link Meeting}.
     */
    static List<Meeting> generateMeetings(){
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
