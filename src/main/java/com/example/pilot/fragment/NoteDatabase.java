package com.example.pilot.fragment;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pilot.Note;
import com.example.pilot.NoteDao;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static volatile NoteDatabase noteRoomInstance;

   public static NoteDatabase getDatabase(final Context context) {
        if (noteRoomInstance == null) {
            synchronized (NoteDatabase.class) {
                if (noteRoomInstance == null) {
                    noteRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            .build();
                }
            }
        }
        return noteRoomInstance;
    }

}
