package com.javacourse.oc.maru;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.javacourse.oc.maru.databinding.ItemListMeetingBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

/**
 * ViewHolder pour afficher un élément de réunion dans un RecyclerView.
 */
public class MeetingViewHolder extends RecyclerView.ViewHolder {

    ItemListMeetingBinding binding;

    /**
     * Constructeur du ViewHolder.
     *
     * @param binding Objet de liaison pour les vues de l'élément de réunion.
     */
    public MeetingViewHolder(@NonNull ItemListMeetingBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Méthode pour lier les données de la réunion à la vue du ViewHolder.
     *
     * @param meeting Réunion à afficher.
     */
    public void bind(Meeting meeting) {

        binding.itemListMeetingName.setText( meeting.getDate() + " - " +meeting.getLocation() + " - "+ meeting.getTime());
        binding.itemListMeetingTheme.setText(meeting.getSubject());
        binding.itemListMeetingPeople.setText(meeting.getParticipants().toString());
        setMeetingRoomColor(meeting.getLocation()); // Définir la couleur en fonction de la salle de réunion

        binding.itemListMeetingDeleteButton.setOnClickListener(view -> {
            // Supprimer la réunion en utilisant l'id de la réunion
            DI.getMeetingApiService().deleteMeeting(meeting);

        });

    }

    /**
     * Méthode pour définir la couleur de la salle de réunion dans la vue.
     *
     * @param roomName Nom de la salle de réunion.
     */
    public void setMeetingRoomColor(String roomName) {
        int color = getColorForRoom(roomName); // Définir la couleur en fonction de la salle de réunion
        binding.itemListMeetingColour.setColorFilter(color);
    }

    /**
     * Méthode pour obtenir la couleur associée à une salle de réunion.
     *
     * @param roomName Nom de la salle de réunion.
     * @return Couleur associée à la salle de réunion.
     */
    private int getColorForRoom(String roomName) {
        int color = Color.GRAY; // Couleur par défaut
        switch (roomName) {
            case "Salle 1":
                color = Color.parseColor("#2962FF"); // bleu foncé
                break;
            case "Salle 2":
                color = Color.parseColor("#00C853"); // vert
                break;
            case "Salle 3":
                color = Color.parseColor("#D50000"); // rouge foncé
                break;
            // Ajoutez des cas pour chaque salle avec sa propre couleur
        }
        return color;
    }
}