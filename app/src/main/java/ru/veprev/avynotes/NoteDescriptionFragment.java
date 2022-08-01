package ru.veprev.avynotes;

import static ru.veprev.avynotes.NoteFragment.CURRENT_NOTE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.time.LocalDateTime;
import java.util.Arrays;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            NotesStructure paramNote = arguments.getParcelable(CURRENT_NOTE);
            note = Arrays.stream(NotesStructure.getNotes()).filter(n -> n.getId() == paramNote.getId()).findFirst().get();


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


 }