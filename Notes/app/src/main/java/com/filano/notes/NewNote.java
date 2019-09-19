package com.filano.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class NewNote extends AppCompatActivity {

    private Button btnDiscard, btnSave;
    private EditText etJudul, etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etJudul = findViewById(R.id.etJudul);
        etNote = findViewById(R.id.etNote);
        btnDiscard = findViewById(R.id.btnDIscard);
        btnSave = findViewById(R.id.btnSave);


        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewNote.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewNote.this, MainActivity.class);
                Bundle extras = new Bundle();
                extras.putString("head",etJudul.getText().toString());
                extras.putString("note", etNote.getText().toString());
                i.putExtras(extras);
                startActivity(i);
                finish();
            }
        });

    }

}
