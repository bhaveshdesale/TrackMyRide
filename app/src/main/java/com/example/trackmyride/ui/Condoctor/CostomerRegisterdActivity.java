package com.example.trackmyride.ui.Condoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trackmyride.R;
import com.example.trackmyride.ui.Helper2;
import com.example.trackmyride.ui.HelperClass;
import com.example.trackmyride.ui.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CostomerRegisterdActivity extends AppCompatActivity {
    private EditText signupName, signupUsername, signupEmail, signupPassword, signupID;
    private TextView loginRedirectText;
    private Button signupButton;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_costomer_registerd);

        // Handle edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Conductor");

        // Initialize view components
        signupName = findViewById(R.id.signup_name_customer);
        signupEmail = findViewById(R.id.signup_email_customer);
        signupUsername = findViewById(R.id.signup_username_customer);
        signupPassword = findViewById(R.id.signup_password_customer);
        signupID = findViewById(R.id.signup_id_customer);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button_customer);

        // Set onClick listeners
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                String id = signupID.getText().toString();

                // Validation checks
                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CostomerRegisterdActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Helper2 helper2 = new Helper2(name, email, username, password,id);
                    reference.child(username).setValue(helper2);
                    Toast.makeText(CostomerRegisterdActivity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CostomerRegisterdActivity.this, CustomerLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CostomerRegisterdActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
