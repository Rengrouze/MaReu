package com.javacourse.oc.maru;

import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.javacourse.oc.maru.databinding.ItemListMeetingBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;
import com.javacourse.oc.maru.service.MeetingApiService;

public class MeetingViewHolder extends RecyclerView.ViewHolder {

    ItemListMeetingBinding binding;

    public MeetingViewHolder(@NonNull ItemListMeetingBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Meeting meeting) {

        binding.itemListMeetingName.setText( meeting.getDate() + " - " +meeting.getLocation() + " - "+ meeting.getTime()+" - " + meeting.getSubject());
        binding.itemListMeetingPeople.setText(meeting.getParticipants().toString());


        binding.itemListMeetingDeleteButton.setOnClickListener(view -> {
            // Supprimer la réunion en utilisant l'id de la réunion
            DI.getMeetingApiService().deleteMeeting(meeting);

        });

    }
}
