package com.javacourse.oc.maru;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.javacourse.oc.maru.databinding.ActivityAddMeetingBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;

    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        String[] roomOptions ={"Salle 1" , "Salle 2", "Salle 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roomOptions);
        binding.roomPicker.setAdapter(adapter);
        // Set click listener for date picker button
        Button datePickerButton = binding.datePickerButton;
        Button timePickerButton = binding.timePickerButton;

        binding.roomPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the selected room
                String room = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(AddMeetingActivity.this, "Selected room: " + room, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show date picker dialog
                showDatePicker();
            }
        });
        timePickerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showTimePicker();
            }
        });

        // Set click listener for add meeting button
        Button addMeetingButton = binding.addMeetingButton;
        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from input fields
                String date = selectedDate != null ? new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(selectedDate.getTime()) : null;
                String time = binding.timePickerEdittext.getText().toString();
                String room = binding.roomPicker.getText().toString();
                String theme = binding.themePickerEdittext.getText().toString();
                String participants = binding.peoplesEdittext.getText().toString();

                if (date == null || time.isEmpty() || room.isEmpty() || theme.isEmpty() || participants.isEmpty()){
                    Toast.makeText(AddMeetingActivity.this, "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                    return;

                }
                Meeting newMeeting = new Meeting(UUID.randomUUID().toString(),time,room,theme,participants,date);
                DI.getMeetingApiService().addMeeting(newMeeting);

                // Show success message
                Toast.makeText(AddMeetingActivity.this, "Meeting added", Toast.LENGTH_SHORT).show();

                // Finish activity
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
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

        // Set minimum date to today
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // Update time picker edit text
                        String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                        binding.timePickerEdittext.setText(time);
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }


}
