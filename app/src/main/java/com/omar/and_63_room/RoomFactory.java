package com.omar.and_63_room;

import android.content.Context;

import androidx.room.Room;

import com.omar.and_63_room.room.NotesDatabase;

public class RoomFactory {

    private static NotesDatabase databaseObject;

    public static NotesDatabase createRoomObject(Context context){

        if(databaseObject == null){

            databaseObject = Room.databaseBuilder(context ,NotesDatabase.class,"notes_db")
                    .build();

        }

        return databaseObject;
    }





}
