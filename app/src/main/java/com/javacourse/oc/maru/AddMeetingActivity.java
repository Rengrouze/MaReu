package com.javacourse.oc.maru;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.javacourse.oc.maru.databinding.ActivityAddMeetingBinding;

public class AddMeetingActivity extends AppCompatActivity {

  private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }


}