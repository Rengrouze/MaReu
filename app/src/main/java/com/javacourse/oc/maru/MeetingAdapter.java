package com.javacourse.oc.maru;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.javacourse.oc.maru.databinding.ItemListMeetingBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private final List<Meeting> mMeetings;

    public MeetingAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MeetingViewHolder(ItemListMeetingBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false));
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.bind(meeting);
        holder.binding.itemListMeetingDeleteButton.setOnClickListener(view -> {
            DI.getMeetingApiService().deleteMeeting(meeting);
            mMeetings.remove(meeting);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
    public void updateData(List<Meeting> meetings){
        mMeetings.clear();
        mMeetings.addAll(meetings);
        notifyDataSetChanged();
    }
}
