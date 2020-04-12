package com.example.sistemamatricula.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.sistemamatricula.R;
import com.example.sistemamatricula.Views.Fragments.CursosFragment;
import com.example.sistemamatricula.Views.Fragments.EstudiantesFragment;
import com.example.sistemamatricula.databinding.ActivityNavigationDrawerBinding;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityNavigationDrawerBinding binding;
    private Fragment coursesFragment, studentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_drawer);

        setSupportActionBar(binding.tbToolbar);

        ActionBarDrawerToggle abdt_toggle = new ActionBarDrawerToggle(this, binding.dlMain, binding.tbToolbar, 0, 0);
        coursesFragment = new CursosFragment();
        studentsFragment = new EstudiantesFragment();

        binding.dlMain.setDrawerListener(abdt_toggle);
        abdt_toggle.syncState();

        binding.nvController.setNavigationItemSelectedListener(this);
        binding.nvController.getMenu().getItem(0).setChecked(true);

        getSupportFragmentManager().beginTransaction().add(R.id.fl_host, studentsFragment, "estudiantes")
                .add(R.id.fl_host, coursesFragment, "cursos")
                .show(studentsFragment)
                .hide(coursesFragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_cursos:
                getSupportFragmentManager().beginTransaction().show(coursesFragment).hide(studentsFragment).commit();
                getSupportActionBar().setTitle("Cursos");
                break;

            case R.id.nav_estudiantes:
                getSupportFragmentManager().beginTransaction().show(studentsFragment).hide(coursesFragment).commit();
                getSupportActionBar().setTitle("Estudiantes");
                break;

            case R.id.nav_logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
        }

        binding.dlMain.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.dlMain.isDrawerOpen(GravityCompat.START)) {
            binding.dlMain.closeDrawer(GravityCompat.START);

        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
