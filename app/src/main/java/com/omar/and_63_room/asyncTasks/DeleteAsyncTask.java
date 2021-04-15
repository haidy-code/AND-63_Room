package com.omar.and_63_room.asyncTasks;

import android.os.AsyncTask;

import com.omar.and_63_room.room.Note;
import com.omar.and_63_room.room.NoteDAO;

public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDAO noteDAO;

    public DeleteAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDAO.deleteNote(notes[0]);

        return null;
    }
}
