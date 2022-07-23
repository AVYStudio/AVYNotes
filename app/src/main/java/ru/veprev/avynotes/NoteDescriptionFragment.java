package ru.veprev.avynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.time.LocalDateTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDescriptionFragment extends Fragment {

    static final String INDEX = "index";

    public NoteDescriptionFragment() {
        // Required empty public constructor
    }

    public static NoteDescriptionFragment newInstance(int index) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
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
            int index = getArguments().getInt(INDEX);


        LinearLayout layout = (LinearLayout) view;

        EditText title = layout.findViewById(R.id.note_title);
        title.setText(NotesStructure.getNote(index).getName());

        EditText description = layout.findViewById(R.id.note_description);
        description.setText(NotesStructure.getNote(index).getDescription());

//        EditText date = layout.findViewById(R.id.date);
//        date.setText(NotesStructure.getNote(index).getDate());
        }
    }


 }