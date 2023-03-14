package com.javacourse.oc.maru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.javacourse.oc.maru.databinding.ActivityMainBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView mRecyclerView;
    private MeetingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configure RecyclerView
        mRecyclerView = binding.activityListUserRv;
        mAdapter = new MeetingAdapter(new ArrayList<Meeting>());
        mRecyclerView.setAdapter(mAdapter);

        // Set click listener for FAB to add new meeting
        binding.activityListUserFab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMeetings();
    }

    private void updateMeetings() {
        List<Meeting> meetings = DI.getMeetingApiService().getMeetings();
        mAdapter.updateData(meetings);
    }
}
