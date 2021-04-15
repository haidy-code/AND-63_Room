package com.omar.and_63_room.asyncTasks;

import android.os.AsyncTask;

import com.omar.and_63_room.room.NoteDAO;
import com.omar.and_63_room.room.Note;

import java.util.List;

public class GetNotesAsyncTask extends AsyncTask<Void, Void, List<Note>> {

    private NoteDAO noteDAO;

    public GetNotesAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected List<Note> doInBackground(Void... voids) {
        return noteDAO.getAllNotes();
    }
}
