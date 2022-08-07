package ru.veprev.avynotes;

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
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class NoteFragment extends Fragment {

    static final String CURRENT_NOTE = "currentNote";
    private FloatingActionButton addBtn;
    private int lastIndex;
    NotesStructure note;
    View container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null)
            note = savedInstanceState.getParcelable(CURRENT_NOTE);

        container = view.findViewById(R.id.data_container);
        initNote(container);

        if (isLandscape()) {
            showLandNote(note);
        }

        addBtn = view.findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesStructure.getNotes().add(NotesStructure.getNote(lastIndex + 1));
                initNote();
            }
        });
    }

    public void initNote() {
        initNote(container);
    }

    private void initNote(View view) {
        LinearLayout layout = (LinearLayout) view;
        layout.removeAllViews();

        for (int i = 0; i < NotesStructure.getNotes().size(); i++) {
            TextView tv = new TextView(getContext());
            tv.setText(NotesStructure.getNotes().get(i).getName());
            tv.setTextSize(30);
            layout.addView(tv);

            final int index = i;
            initPopupMenu(layout, tv, index);
            lastIndex = i;
            tv.setOnClickListener(view1 -> {
                showNote(NotesStructure.getNotes().get(index));
            });
        }
    }

//    private void showNote(int index) {
//        if (isLandscape()) showLandNote(index);
//        else showPortNote(index);
//    }

    private void showNote(NotesStructure note) {
        this.note = note;
        if (isLandscape()) showLandNote(note);
        else showPortNote(note);
    }

    private void showPortNote(NotesStructure note) {
        NoteDescriptionFragment fragment = NoteDescriptionFragment.newInstance(note);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.title_container, fragment)
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void showLandNote(NotesStructure note) {
        NoteDescriptionFragment fragment = NoteDescriptionFragment.newInstance(note);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.description_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        if (note == null)
            note = NotesStructure.getNotes().get(0);

        outState.putParcelable(CURRENT_NOTE, note);
        super.onSaveInstanceState(outState);
    }

    private void initPopupMenu(LinearLayout rootView, TextView view, int index){
        view.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), view);
            requireActivity().getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.action_popup_delete:
                        NotesStructure.getNotes().remove(index);
                        rootView.removeView(view);
                        break;
                }
                return true;
            });
            popupMenu.show();
            return true;
        });
    }
}