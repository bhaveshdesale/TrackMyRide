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

import com.example.trackmyride.MainActivity;
import com.example.trackmyride.R;
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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_login);

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
            if (!validateUsername() | !validatePassword() | !validateID()) {
                // Validation failed
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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Conductor");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginUsername.setError(null);

                    // Fetching data from the database
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    String idFromDB = snapshot.child(userUsername).child("id").getValue(String.class);

                    // Debugging - Check retrieved values
                    Log.d("CustomerLoginActivity", "Password from DB: " + passwordFromDB);
                    Log.d("CustomerLoginActivity", "ID from DB: " + idFromDB);

                    if (passwordFromDB != null && idFromDB != null) {
                        if (passwordFromDB.equals(userPassword) && idFromDB.equals(userID)) {
                            String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                            String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                            String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                            Intent intent = new Intent(CustomerLoginActivity.this, MainActivity.class);
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
                    } else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
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
