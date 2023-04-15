

package com.javacourse.oc.maru.di;

import com.javacourse.oc.maru.service.DummyMeetingApiService;
import com.javacourse.oc.maru.service.MeetingApiService;
/**
 * Classe de gestion des dépendances (Dependency Injection) pour l'API des réunions.
 * Cette classe permet d'obtenir des instances de la classe @{@link MeetingApiService}
 * pour accéder aux fonctionnalités de l'API. Elle offre également la possibilité
 * d'obtenir toujours une nouvelle instance de @{@link MeetingApiService} pour les tests.
 */

public class DI {
    private static MeetingApiService service = new DummyMeetingApiService();

    /**
     * Récupère une instance de @{@link MeetingApiService}.
     * @return Une instance de @{@link MeetingApiService}
     */
    public static MeetingApiService getMeetingApiService(){
        return service;
    }

    /**
     * Récupère toujours une nouvelle instance de @{@link MeetingApiService}. Utile pour les tests.
     * @return Une nouvelle instance de @{@link MeetingApiService}
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
