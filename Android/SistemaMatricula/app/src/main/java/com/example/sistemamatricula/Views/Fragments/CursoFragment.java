package com.example.sistemamatricula.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sistemamatricula.databinding.FragmentCursoBinding;

public class CursoFragment extends Fragment {

    private FragmentCursoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCursoBinding.inflate(inflater);

        return binding.getRoot();
    }
}
