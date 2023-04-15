

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

/**
 * Cette classe permet l'ajout de réunion à la liste
 */
public class AddMeetingActivity extends AppCompatActivity {
    /**
     * Binding pour l'activité AddMeetingActivity.
     */
    private ActivityAddMeetingBinding binding;
    /**
     * Calendrier pour stocker la date sélectionnée.
     */
    private Calendar selectedDate;
    /**
     * Méthode appelée lors de la création de l'activité.
     *
     * @param savedInstanceState État sauvegardé de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialiser le spinner pour la sélection de la salle
        String[] roomOptions ={"Salle 1" , "Salle 2", "Salle 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roomOptions);
        binding.roomPicker.setAdapter(adapter);

        Button datePickerButton = binding.datePickerButton;
        Button timePickerButton = binding.timePickerButton;

        // Définir un listener de sélection d'item pour le spinner de la salle
        binding.roomPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Obtenir la salle sélectionnée
                String room = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(AddMeetingActivity.this, "Selected room: " + room, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Ne rien faire
            }
        });

//  Définir un listener au clic du bouton de sélection de la date
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ouvrir le date picker
                showDatePicker();
            }
        });
        //  Définir un listener au clic du bouton de sélection de l'heure
        timePickerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // ouvrir le time picker
                showTimePicker();
            }
        });

        // Définir un listener au clic du bouton d'ajout de la réunion
        Button addMeetingButton = binding.addMeetingButton;
        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenir les valeurs dans les champs
                String date = selectedDate != null ? new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(selectedDate.getTime()) : null;
                String time = binding.timePickerEdittext.getText().toString();
                String room = binding.roomPicker.getText().toString();
                String theme = binding.themePickerEdittext.getText().toString();
                String participants = binding.peoplesEdittext.getText().toString();


                // vérifiez si les champs sont vides
                if (date == null || time.isEmpty() || room.isEmpty() || theme.isEmpty() || participants.isEmpty()){
                    Toast.makeText(AddMeetingActivity.this, "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                    return;

                }

                // créer une nouvelle réunion
                Meeting newMeeting = new Meeting(UUID.randomUUID().toString(),time,room,theme,participants,date);
                DI.getMeetingApiService().addMeeting(newMeeting);

                // montre un petit message de succès
                Toast.makeText(AddMeetingActivity.this, "Meeting added", Toast.LENGTH_SHORT).show();

                // Termine l'activité
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    /**
     * Affiche le date picker.
     */
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

    /**
     * Affiche le dialogue de sélection de l'heure.
     */
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
