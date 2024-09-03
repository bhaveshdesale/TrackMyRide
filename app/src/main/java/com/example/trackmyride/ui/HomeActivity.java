package com.example.trackmyride.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trackmyride.R;
import com.example.trackmyride.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use ViewBinding to inflate the layout
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Enable Edge to Edge UI
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set the background of the BottomNavigationView to null
        binding.bottomNavigationView.setBackground(null);

        // Replace the fragment with HomeFragment by default
        replaceFragment(new HomeFragment());

        // Handle BottomNavigationView item selection
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id =item.getItemId(); {
                if  (id==R.id.home) {
                    replaceFragment(new HomeFragment());
                }
                if(id==R.id.profile)
                    replaceFragment(new ProfileFragement()); // Ensure this class is correctly imported and named


            }
            if(id==R.id.Add)
                replaceFragment(new AddFragment());
            return true;
        });
    }

    // Method to replace the current fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
