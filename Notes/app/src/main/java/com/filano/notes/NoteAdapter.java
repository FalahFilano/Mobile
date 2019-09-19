package com.filano.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<NoteItem> itemList;

    public NoteAdapter( ArrayList<NoteItem> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, int i) {
        NoteItem item =itemList.get(i);

        viewHolder.tvJudul.setText(item.getJudul());
        viewHolder.tvNote.setText(item.getNote());
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvJudul, tvNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul= itemView.findViewById(R.id.tvJudul);
            tvNote = itemView.findViewById(R.id.tvNote);

        }
    }
}
