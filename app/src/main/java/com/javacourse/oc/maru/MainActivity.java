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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
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
