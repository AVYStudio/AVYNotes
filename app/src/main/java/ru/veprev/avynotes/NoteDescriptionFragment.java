package ru.veprev.avynotes;

import static ru.veprev.avynotes.NoteFragment.CURRENT_NOTE;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Optional;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDescriptionFragment extends Fragment {

    private NotesStructure note;

    public static NoteDescriptionFragment newInstance(NotesStructure note) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null)
            setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            NotesStructure paramNote = arguments.getParcelable(CURRENT_NOTE);

            if (paramNote != null) {
                Optional<NotesStructure> selectedNote = NotesStructure.getNotes().stream().filter(n -> n.getId() == paramNote.getId()).findFirst();
                note = selectedNote.orElseGet(() -> NotesStructure.getNotes().get(0));
            }



            EditText title = view.findViewById(R.id.note_title);
            title.setText(note.getName());
            title.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setName(title.getText().toString());
                    updateData();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            EditText description = view.findViewById(R.id.note_description);
            description.setText(note.getDescription());
            description.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setDescription(description.getText().toString());
                    updateData();
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

//          EditText date = layout.findViewById(R.id.date);
//          date.setText(NotesStructure.getNote(index).getDate());
        }
    }

    private void updateData(){
        NoteFragment noteFragment = (NoteFragment) requireActivity().getSupportFragmentManager()
                .getFragments().stream().filter( fragment -> fragment instanceof NoteFragment)
                .findFirst().get();
        noteFragment.initNote();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.note_description_menu, menu);

        MenuItem menuItemExit = menu.findItem(R.id.action_exit);
        if (menuItemExit != null)
            menuItemExit.setVisible(false);
        MenuItem menuItemAbout = menu.findItem(R.id.action_about);
        if (menuItemAbout != null)
            menuItemAbout.setVisible(false);
        MenuItem menuItemSettings = menu.findItem(R.id.action_settings);
        if (menuItemSettings != null)
            menuItemSettings.setVisible(false);
        MenuItem menuItemBack = menu.findItem(R.id.action_back);
        if (isLandscape() && menuItemBack != null) {
            menuItemBack.setVisible(false);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {
            NotesStructure.getNotes().remove(note);
            updateData();

            if (!isLandscape())
                requireActivity().getSupportFragmentManager().popBackStack();
            return true;
        }

        if (item.getItemId() == R.id.action_back) {
            if (!isLandscape())
                requireActivity().getSupportFragmentManager().popBackStack();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
}