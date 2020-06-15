package com.example.matricula.Views.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.matricula.R;
import com.example.matricula.Views.Fragments.CursosFragment;
import com.example.matricula.Views.Fragments.EstudiantesFragment;
import com.example.matricula.databinding.ActivityNavigationDrawerBinding;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityNavigationDrawerBinding binding;
    private Fragment coursesFragment, studentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

            case R.id.nav_salir:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }

        binding.dlMain.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.dlMain.isDrawerOpen(GravityCompat.START)) {
            binding.dlMain.closeDrawer(GravityCompat.START);

        } else {
            new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                    .setIcon(R.drawable.ic_salir)
                    .setTitle("Cerrar sesión")
                    .setMessage("¿Deseas cerrar sesión?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                    })
                    .create()
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
