package com.omar.and_63_room.asyncTasks;

import android.os.AsyncTask;

import com.omar.and_63_room.room.Note;
import com.omar.and_63_room.room.NoteDAO;

public class InsertAsyncTask extends AsyncTask<Note, Void,Void> {


    private NoteDAO noteDAO;

    public InsertAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDAO.insertNote(notes[0]);
        return null;
    }


}
