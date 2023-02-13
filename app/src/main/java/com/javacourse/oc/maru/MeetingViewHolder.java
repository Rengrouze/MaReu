package com.javacourse.oc.maru;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.javacourse.oc.maru.databinding.ItemListMeetingBinding;
import com.javacourse.oc.maru.model.Meeting;

public class MeetingViewHolder extends RecyclerView.ViewHolder {

    private ItemListMeetingBinding binding;

    public MeetingViewHolder(@NonNull ItemListMeetingBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Meeting meeting) {

        binding.itemListMeetingName.setText(meeting.getLocation() + " - " + meeting.getDate() + " - " + meeting.getTime()+" - " + meeting.getLocation());
        binding.itemListMeetingPeople.setText(meeting.getParticipants().toString());
    }
}
