package com.example.ecokids.ui.metas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecokids.databinding.FragmentMetasBinding;

public class MetasFragment extends Fragment {

    private FragmentMetasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MetasViewModel metasViewModel =
                new ViewModelProvider(this).get(MetasViewModel.class);

        binding = FragmentMetasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMetas;
        metasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}