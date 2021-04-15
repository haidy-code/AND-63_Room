package com.omar.and_63_room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omar.and_63_room.room.Note;

import java.util.List;

public class NotesRvAdapter extends RecyclerView.Adapter<NotesRvAdapter.NotesViewHolder> {

    private List<Note> noteList;
    private OnViewClickListener onItemClickListener;

    public interface OnViewClickListener {
        void onViewClick(View view, int position);
    }


    public NotesRvAdapter(List<Note> noteList, OnViewClickListener onItemClickListener) {
        this.noteList = noteList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_rv_item, parent, false);

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, int position) {

        Note note = noteList.get(position);

        holder.titleTv.setText(note.getTitle());
        holder.descriptionTv.setText(note.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onViewClick(v, holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView descriptionTv;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.notes_title_tv);
            descriptionTv = itemView.findViewById(R.id.notes_description_tv);

        }
    }

}
