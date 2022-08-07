package ru.veprev.avynotes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            switch (i) {
                case (R.id.type_1):
                case (R.id.type_2):
                case (R.id.type_3):
                    Toast.makeText(requireActivity(), "Кнопка активна", Toast.LENGTH_SHORT).show();
                    break;
            }
    });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.other_fragment_menu, menu);


        MenuItem menuItemExit = menu.findItem(R.id.action_exit);
        if (menuItemExit != null)
            menuItemExit.setVisible(false);
        MenuItem menuItemAbout = menu.findItem(R.id.action_about);
        if (menuItemAbout != null)
            menuItemAbout.setVisible(false);
        MenuItem menuItemSettings = menu.findItem(R.id.action_settings);
        if (menuItemSettings != null)
            menuItemSettings.setVisible(false);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_back && !isLandscape()) {
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
