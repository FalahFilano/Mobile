package com.filano.notes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter> {

    private ArrayList<NoteItem> itemList;

    HomeAdapter(Context context, ArrayList<NoteItem> list){
        mContext =context;
        mlist = list;
    }

    public void setItemList(ArrayList<NoteActive> itemList){
        this.itemList=
    }
}
