package com.example.sistemamatricula.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.sistemamatricula.R;
import com.example.sistemamatricula.Views.Fragments.CursoFragment;
import com.example.sistemamatricula.Views.Fragments.EstudianteFragment;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar tb_toolbar;
    private DrawerLayout dl_main;
    private NavigationView nv_controller;
    private ActionBarDrawerToggle abdt_toggle;
    private Fragment coursesFragment, studentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        tb_toolbar = findViewById(R.id.tb_toolbar);
        setSupportActionBar(tb_toolbar);

        dl_main = findViewById(R.id.drawer_layout);
        nv_controller = findViewById(R.id.nav_view);
        abdt_toggle = new ActionBarDrawerToggle(this, dl_main, tb_toolbar, 0, 0);
        coursesFragment = new CursoFragment();
        studentsFragment = new EstudianteFragment();

        dl_main.setDrawerListener(abdt_toggle);
        abdt_toggle.syncState();

        nv_controller.setNavigationItemSelectedListener(this);
        nv_controller.getMenu().getItem(0).setChecked(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_host, studentsFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_cursos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_host, coursesFragment).commit();
                getSupportActionBar().setTitle("Cursos");
                break;

            case R.id.nav_estudiantes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_host, studentsFragment).commit();
                getSupportActionBar().setTitle("Estudiantes");
                break;

            case R.id.nav_logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
        }

        dl_main.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dl_main.isDrawerOpen(GravityCompat.START)) {
            dl_main.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }


}
