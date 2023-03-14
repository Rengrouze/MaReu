package com.javacourse.oc.maru;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.javacourse.oc.maru.databinding.ItemListMeetingBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

public class MeetingViewHolder extends RecyclerView.ViewHolder {

    ItemListMeetingBinding binding;

    public MeetingViewHolder(@NonNull ItemListMeetingBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

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
    public void setMeetingRoomColor(String roomName) {
        int color = getColorForRoom(roomName); // Définir la couleur en fonction de la salle de réunion
        binding.itemListMeetingColour.setColorFilter(color);
    }

    private int getColorForRoom(String roomName) {
        int color = Color.GRAY; // Couleur par défaut
        switch (roomName) {
            case "Salle 1":
                color = Color.BLUE;
                break;
            case "Salle 2":
                color = Color.GREEN;
                break;
            case "Salle 3":
                color = Color.RED;
                break;
            // Ajoutez des cas pour chaque salle avec sa propre couleur
        }
        return color;
    }


}
