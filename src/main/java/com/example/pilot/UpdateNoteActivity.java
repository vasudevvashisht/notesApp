package com.example.pilot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pilot.fragment.NoteDatabase;

public class UpdateNoteActivity extends AppCompatActivity{

    private EditText etTitle, etDesc;
    private Button update,delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_note);

        etTitle = findViewById(R.id.edit_note_title);
        etDesc = findViewById(R.id.edit_note_desc);

        final Note note = (Note) getIntent().getSerializableExtra("note");

        loadNote(note);

        update = findViewById(R.id.button_update);
        delete = findViewById(R.id.button_delete);
        update.setOnClickListener(view -> updateNote(note));
        delete.setOnClickListener(view -> deleteNote(note));
    }

    private void deleteNote(Note note) {
        class DeleteNote extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                NoteDatabase.getDatabase(getApplicationContext())
                        .noteDao()
                        .delete(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateNoteActivity.this, MainActivity.class));
            }
        }

        DeleteNote dn = new DeleteNote();
        dn.execute();
    }

    private void updateNote(Note note) {
        final String title = etTitle.getText().toString().trim();
        final String desc = etDesc.getText().toString().trim();

        if(title.isEmpty()){
            etTitle.setError("Title required");
            etTitle.requestFocus();
            return;
        }
        if(desc.isEmpty()){
            etDesc.setError("Title required");
            etDesc.requestFocus();
            return;
        }
        class UpdateNote extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                note.setTitle(title);
                note.setDescription(desc);

                NoteDatabase.getDatabase(getApplicationContext())
                        .noteDao()
                        .update(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateNoteActivity.this, MainActivity.class));
            }
        }

        UpdateNote un = new UpdateNote();
        un.execute();



    }

    private void loadNote(Note note) {
        etTitle.setText(note.getTitle());
        etDesc.setText(note.getDescription());


    }

}
