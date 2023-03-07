package com.javacourse.oc.maru;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.javacourse.oc.maru.databinding.ActivityAddMeetingBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;

    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Set click listener for date picker button
        Button datePickerButton = binding.datePickerButton;
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show date picker dialog
                showDatePicker();
            }
        });

        // Set click listener for add meeting button
        Button addMeetingButton = binding.addMeetingButton;
        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from input fields
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.getTime());
                String time = binding.timePickerEdittext.getText().toString();
                String room = binding.roomPickerEdittext.getText().toString();
                String participants = binding.peoplesEdittext.getText().toString();

                // TODO: Create new meeting with input values

                // Show success message
                Toast.makeText(AddMeetingActivity.this, "Meeting added", Toast.LENGTH_SHORT).show();

                // Finish activity
                finish();
            }
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, day);

                // Set date to edit text
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                binding.datePickerEdittext.setText(sdf.format(selectedDate.getTime()));
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}
