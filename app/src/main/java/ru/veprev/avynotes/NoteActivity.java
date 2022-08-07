package ru.veprev.avynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.navigation.NavigationView;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);

        if (!isLandscape())
            initDrawer();

        if (savedInstanceState == null)
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.title_container, new NoteFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                finish();
                break;
            case R.id.action_about:
                openAbout();
                break;
            case R.id.action_settings:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.title_container, new SettingsFragment())
                        .addToBackStack("")
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDrawer(){

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_drawer_about:
                    openAbout();
                    drawerLayout.close();
                    return true;
                case R.id.action_drawer_exit:
                    finish();
                    return true;
            }
            return false;
        });
    }

    private void openAbout() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.title_container, new AboutFragment())
                .addToBackStack("")
                .commit();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

}