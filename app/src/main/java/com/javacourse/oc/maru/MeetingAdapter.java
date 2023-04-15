package com.javacourse.oc.maru;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.javacourse.oc.maru.databinding.ItemListMeetingBinding;
import com.javacourse.oc.maru.di.DI;
import com.javacourse.oc.maru.model.Meeting;

import java.util.List;

/**
 * Adapter pour afficher la liste des réunions dans un RecyclerView.
 */
public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private final List<Meeting> mMeetings;

    /**
     * Constructeur de l'adapter.
     *
     * @param meetings Liste des réunions à afficher.
     */
    public MeetingAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    /**
     * Méthode appelée lors de la création d'un nouveau ViewHolder pour un élément de liste.
     *
     * @param parent   Le parent du ViewHolder (généralement le RecyclerView).
     * @param viewType Type de vue du ViewHolder.
     * @return Nouveau ViewHolder créé.
     */
    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MeetingViewHolder(ItemListMeetingBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false));
    }

    /**
     * Méthode appelée lors de la liaison d'un ViewHolder avec des données.
     *
     * @param holder   Le ViewHolder à lier.
     * @param position Position de l'élément dans la liste.
     */
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

    /**
     * Méthode pour obtenir le nombre d'éléments dans la liste.
     *
     * @return Nombre d'éléments dans la liste.
     */
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    /**
     * Méthode pour mettre à jour les données de l'adapter avec une nouvelle liste de réunions.
     *
     * @param meetings Nouvelle liste de réunions.
     */
    public void updateData(List<Meeting> meetings){
        mMeetings.clear();
        mMeetings.addAll(meetings);
        notifyDataSetChanged();
    }
}
