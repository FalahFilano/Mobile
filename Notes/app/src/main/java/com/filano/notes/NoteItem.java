package com.filano.notes;

public class NoteItem {
    private String judul, note;

    public NoteItem(String judul, String note){
        this.judul = judul;
        this.note = note;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
