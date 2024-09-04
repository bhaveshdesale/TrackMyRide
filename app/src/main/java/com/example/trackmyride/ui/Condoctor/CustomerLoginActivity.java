package com.example.trackmyride.ui.Condoctor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trackmyride.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CustomerLoginActivity extends AppCompatActivity {

    private EditText loginUsername, loginPassword, loginID;
    private Button loginButton;
    private TextView signupRedirectText;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_login);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize views
        loginUsername = findViewById(R.id.login_username_customer);
        loginPassword = findViewById(R.id.login_password_customer);
        loginID = findViewById(R.id.login_Id_customer);
        loginButton = findViewById(R.id.login_button_customer);
        signupRedirectText = findViewById(R.id.signupRedirectText_customer);

        // Handle edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Set onClick listeners
        loginButton.setOnClickListener(view -> {
            if (!validateUsername() || !validatePassword() || !validateID()) {
                // Validation failed
                return;
            } else {
                checkUser();
            }
        });

        signupRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(CustomerLoginActivity.this, CostomerRegisterdActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public Boolean validateID() {
        String val = loginID.getText().toString();
        if (val.isEmpty()) {
            loginID.setError("ID cannot be empty");
            return false;
        } else {
            loginID.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        String userID = loginID.getText().toString().trim();

        // Reference to the database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Conductor");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Fetching the email associated with the username
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String emailFromDB = userSnapshot.child("email").getValue(String.class);
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);
                        String idFromDB = userSnapshot.child("id").getValue(String.class);

                        if (emailFromDB != null && passwordFromDB != null && idFromDB != null) {
                            // Now use the retrieved email for Firebase Authentication
                            firebaseAuth.signInWithEmailAndPassword(emailFromDB, userPassword)
                                    .addOnCompleteListener(CustomerLoginActivity.this, task -> {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = firebaseAuth.getCurrentUser();
                                            if (user != null) {
                                                // Authentication successful, now proceed with the rest of the logic
                                                if (passwordFromDB.equals(userPassword) && idFromDB.equals(userID)) {
                                                    String nameFromDB = userSnapshot.child("name").getValue(String.class);
                                                    String usernameFromDB = userSnapshot.child("username").getValue(String.class);

                                                    Intent intent = new Intent(CustomerLoginActivity.this, LocationActivity.class);
                                                    intent.putExtra("name", nameFromDB);
                                                    intent.putExtra("email", emailFromDB);
                                                    intent.putExtra("username", usernameFromDB);
                                                    intent.putExtra("password", passwordFromDB);
                                                    intent.putExtra("id", idFromDB);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    loginPassword.setError("Invalid Credentials");
                                                    loginPassword.requestFocus();
                                                }
                                            }
                                        } else {
                                            Log.e("CustomerLoginActivity", "Authentication Failed: " + task.getException().getMessage());
                                            Toast.makeText(CustomerLoginActivity.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            loginUsername.setError("Credentials not found");
                            loginUsername.requestFocus();
                        }
                    }
                } else {
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("CustomerLoginActivity", "Error: " + error.getMessage());
                Toast.makeText(CustomerLoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
