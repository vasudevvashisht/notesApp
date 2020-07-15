package com.example.pilot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//note adapter
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> notes;
    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;


    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.note_card_view,parent,false);
       return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getTitle());
        holder.desc.setText(note.getDescription());
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, desc;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            desc = itemView.findViewById(R.id.note_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Note note = notes.get(getAdapterPosition());
            Intent intent = new Intent(context, UpdateNoteActivity.class);
            intent.putExtra("note", note);

            context.startActivity(intent);
        }
    }
}
