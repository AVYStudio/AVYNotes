package ru.veprev.avynotes;

import static ru.veprev.avynotes.NoteDescriptionFragment.INDEX;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NoteFragment extends Fragment {

    private static final String CURRENT_NOTE = "currentNote";
    private int currentIndex;


    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_NOTE, currentIndex);
        }

        if (isLandscape()) {
            showLandNote(currentIndex);
        }

        initNote(view);

    }

    private void initNote(View view) {
        LinearLayout layout = (LinearLayout) view;

        for (int i = 0; i < NotesStructure.getNotes().length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(NotesStructure.getNotes()[i].getName());
            layout.addView(tv);
            tv.setTextSize(30);
            final int index = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentIndex = index;
                    showNote(index);
                }
            });
        }
    }

    private void showNote(int index) {
        if (isLandscape()) showLandNote(index);
        else showPortNote(index);
    }

    private void showPortNote(int index) {
        NoteDescriptionFragment fragment = NoteDescriptionFragment.newInstance(index);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.title_container, fragment)
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    private void showLandNote(int index) {
        NoteDescriptionFragment fragment = NoteDescriptionFragment.newInstance(index);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.description_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_NOTE, currentIndex);
    }
}