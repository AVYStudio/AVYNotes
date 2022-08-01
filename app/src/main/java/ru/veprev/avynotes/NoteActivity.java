package ru.veprev.avynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);

        if (savedInstanceState == null)
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.title_container, new NoteFragment())
                .commit();
    }
}