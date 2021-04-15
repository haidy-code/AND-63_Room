package com.omar.and_63_room.asyncTasks;

import android.os.AsyncTask;

import com.omar.and_63_room.room.Note;
import com.omar.and_63_room.room.NoteDAO;

public class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDAO noteDAO;

    public UpdateAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDAO.updateNote(notes[0]);
        return null;
    }
}
