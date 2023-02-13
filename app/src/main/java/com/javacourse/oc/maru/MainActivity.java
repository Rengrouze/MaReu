package com.javacourse.oc.maru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.javacourse.oc.maru.databinding.ActivityMainBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private RecyclerView mRecyclerView;
    private MeetingAdapter mAdapter;

    private void configureRecyclerView(){
        mRecyclerView = binding.activityListUserRv;
        mAdapter = new MeetingAdapter(new ArrayList<Meeting>());
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        configureRecyclerView();

        // Mettre à jour les données
        updateMeetings();






    }
    private void updateMeetings() {
        List<Meeting> meetings = DI.getMeetingApiService().getMeetings();
        mAdapter.updateData(meetings);
    }
}