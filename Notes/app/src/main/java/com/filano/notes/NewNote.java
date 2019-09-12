package com.filano.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNote extends AppCompatActivity {

    private Button btnDiscard, btnSave;
    private EditText etJudul, etNote;

    private String judul, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etJudul = findViewById(R.id.etJudul);
        etNote = findViewById(R.id.etNote);
        btnDiscard = findViewById(R.id.btnDIscard);
        btnSave = findViewById(R.id.btnSave);

        judul = etJudul.toString();
        note = etNote.toString();

        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewNote.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
