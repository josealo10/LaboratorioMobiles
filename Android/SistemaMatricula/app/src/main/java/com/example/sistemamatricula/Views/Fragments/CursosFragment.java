package com.example.sistemamatricula.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sistemamatricula.databinding.FragmentCursosBinding;

public class CursosFragment extends Fragment {

    private FragmentCursosBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCursosBinding.inflate(inflater);

        return binding.getRoot();
    }
}
