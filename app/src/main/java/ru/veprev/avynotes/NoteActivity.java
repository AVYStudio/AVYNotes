package ru.veprev.avynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.title_container, new NoteFragment())
                .commit();
    }
}