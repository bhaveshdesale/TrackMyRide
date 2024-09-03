package com.example.trackmyride.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trackmyride.R;
import com.example.trackmyride.ui.Condoctor.CostomerRegisterdActivity;

public class StartActivity extends AppCompatActivity {
Button btnUser,btnConductor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            btnUser=findViewById(R.id.userButton);
            btnConductor=findViewById(R.id.conductorButton);
            btnUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(StartActivity.this,SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            btnConductor=findViewById(R.id.conductorButton);
            btnConductor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(StartActivity.this, CostomerRegisterdActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}