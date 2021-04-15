package com.omar.and_63_room.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class} , version = 2 , exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {


    public abstract NoteDAO getNoteDao();


}
