package com.javacourse.oc.maru.service;

import com.javacourse.oc.maru.model.Meeting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Cette interface fournit des méthodes pour gérer les réunions.
 */
public interface MeetingApiService {

    /**
     * Renvoie une liste de toutes les réunions.
     *
     * @return une liste de toutes les réunions
     */
    List<Meeting> getMeetings();

    /**
     * Ajoute une nouvelle réunion à la liste.
     *
     * @param meeting la réunion à ajouter
     */
    void addMeeting(Meeting meeting);

    /**
     * Supprime une réunion de la liste.
     *
     * @param meeting la réunion à supprimer
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Renvoie une liste de réunions filtrées par date.
     *
     * @param date la date pour filtrer les réunions
     * @return une liste de réunions filtrées par date
     * @throws ParseException s'il y a une erreur lors de l'analyse de la date
     */
    ArrayList<Meeting> getMeetingsFilteredByDate(Date date) throws ParseException;

    /**
     * Renvoie une liste de réunions filtrées par salle.
     *
     * @param room la salle pour filtrer les réunions
     * @return une liste de réunions filtrées par salle
     */
    ArrayList<Meeting> getMeetingsFilteredByRoom(String room);
}
