package com.javacourse.oc.maru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.javacourse.oc.maru.databinding.ActivityMainBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Activité principale de l'application pour gérer les réunions.
 * Affiche une liste de réunions dans un RecyclerView et permet de filtrer les réunions par date ou par salle.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView mRecyclerView;
    private MeetingAdapter mAdapter;


    /**
     * Méthode appelée lors de la création de l'activité.
     * Elle configure l'interface utilisateur, notamment le RecyclerView et le bouton flottant pour ajouter de nouvelles réunions.
     *
     * @param savedInstanceState Instance de Bundle qui contient les données de l'activité précédemment enregistrées.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurer le Recycler view
        mRecyclerView = binding.activityListUserRv;
        mAdapter = new MeetingAdapter(new ArrayList<Meeting>());
        mRecyclerView.setAdapter(mAdapter);

        // Ajout d'un listener au clic du bouton d'ajout de réunion
        binding.activityListUserFab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
            startActivity(intent);
        });
    }


    /**
     * Méthode appelée pour créer le menu d'options dans la barre d'action de l'activité.
     *
     * @param menu Menu d'options à créer.
     * @return Retourne true pour afficher le menu, false sinon.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    /**
     * Méthode appelée pour gérer les événements du menu d'options.
     *
     * @param item Élément de menu sélectionné.
     * @return Retourne true si l'événement est géré, false sinon.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.app_bar_filter_date:
                dateDialog();
                return true;
            case R.id.app_bar_filter_room:
                roomList();
                return true;
            case R.id.app_bar_filter_nothing:
                updateMeetings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    /**
     * Affiche une boîte de dialogue pour sélectionner une salle de réunion et filtre les réunions en conséquence.
     */
    private void roomList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_room)
                .setItems(R.array.room_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String room = "";
                        switch (which){
                            case 0:
                                 room = "Salle 1";
                                 break;
                            case 1:
                                room = "Salle 2";
                                break;
                            case 2:
                                room = "Salle 3";
                                break;

                        }
                        List<Meeting> meetings;
                        meetings = DI.getMeetingApiService().getMeetingsFilteredByRoom(room);
                        mAdapter.updateData(meetings);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        }




    /**
     * Affiche le date picker puis filtre la liste en conséquences
     */
    private void dateDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, day);
                List<Meeting> meetings;
                try {
                    meetings = DI.getMeetingApiService().getMeetingsFilteredByDate(selectedDate.getTime());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                mAdapter.updateData(meetings);
            }
        }, year, month, day);



        datePickerDialog.show();
    }

    /**
     * Méthode appelée lors de la reprise de l'activité.
     * Elle met à jour la liste des réunions affichées dans le RecyclerView.
     */
    @Override
    protected void onResume() {
        super.onResume();
        updateMeetings();
    }

    /**
     * Met à jour la liste des réunions affichées dans le RecyclerView.
     */
    private void updateMeetings() {
        List<Meeting> meetings = DI.getMeetingApiService().getMeetings();
        mAdapter.updateData(meetings);
    }

}
