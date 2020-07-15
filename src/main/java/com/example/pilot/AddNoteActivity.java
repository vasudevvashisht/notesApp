package com.example.pilot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pilot.fragment.NoteDatabase;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitle, etDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        etTitle = findViewById(R.id.edit_note_title);
        etDesc = findViewById(R.id.edit_note_desc);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        final String title = etTitle.getText().toString().trim();
        final String desc = etDesc.getText().toString().trim();


        if (title.isEmpty()) {
            etTitle.setError("Title required");
            etTitle.requestFocus();
            return;
        }

        if (desc.isEmpty()) {
            etDesc.setError("Description required");
            etDesc.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a node
                Note note = new Note(title,desc);


                //adding to database
                NoteDatabase.getDatabase(getApplicationContext())
                        .noteDao()
                        .insert(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

}
