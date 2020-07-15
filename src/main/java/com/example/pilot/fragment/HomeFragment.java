package com.example.pilot.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pilot.AddNoteActivity;
import com.example.pilot.Note;
import com.example.pilot.NoteAdapter;
import com.example.pilot.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class HomeFragment extends Fragment {
    private FloatingActionButton buttonAddNote;
    private RecyclerView recyclerView;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,null);
        recyclerView = view.findViewById(R.id.recyclerview_notes);
        buttonAddNote = view.findViewById(R.id.floating_button_add);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        buttonAddNote.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddNoteActivity.class);
            startActivity(intent);
        });
        getNotes();
       return view;
    }

    private void getNotes() {
        class GetNotes extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                List<Note> notes = NoteDatabase.getDatabase(getContext().getApplicationContext())
                        .noteDao()
                        .getAll();
                return notes;
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                NoteAdapter adapter = new NoteAdapter(getActivity(), notes);
                recyclerView.setAdapter(adapter);
            }

        }
        GetNotes gn = new GetNotes();
        gn.execute();

    }
    }


