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
 * Classe qui implémente l'interface MeetingApiService pour fournir un service factice de réunions.
 */
public class DummyMeetingApiService implements MeetingApiService {

        // Liste des réunions
        private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

        /**
         * Récupère la liste des réunions.
         *
         * @return la liste des réunions
         */
        @Override
        public List<Meeting> getMeetings() {
            return meetings;
        }

        /**
         * Ajoute une réunion à la liste.
         *
         * @param meeting la réunion à ajouter
         */
        @Override
        public void addMeeting(Meeting meeting){
                meetings.add(meeting);
        }

        /**
         * Supprime une réunion de la liste.
         *
         * @param meeting la réunion à supprimer
         */
        @Override
        public void deleteMeeting(Meeting meeting){
                meetings.remove(meeting);
        }

        /**
         * Récupère la liste des réunions filtrées par date.
         *
         * @param date la date à utiliser pour le filtre
         * @return la liste des réunions filtrées par date
         * @throws ParseException si la date fournie n'est pas dans le format "dd-MM-yyyy"
         */
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

        /**
         * Récupère la liste des réunions filtrées par salle de réunion.
         *
         * @param room la salle de réunion à utiliser pour le filtre
         * @return la liste des réunions filtrées par salle de réunion
         */
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
