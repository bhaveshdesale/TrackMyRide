package com.example.trackmyride.ui.Condoctor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trackmyride.R;
import com.example.trackmyride.ui.BusInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LocationActivity extends AppCompatActivity {
    private EditText firstStop, lastStop, busNo, location, busETA;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private FirebaseFirestore firestore;
    private String currentUserID;
    private Button uploadBusInfoButton;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);

        // Initialize views
        firstStop = findViewById(R.id.firstStop);
        lastStop = findViewById(R.id.lastStop);
        busNo = findViewById(R.id.busNo);
        location = findViewById(R.id.location);
        busETA = findViewById(R.id.busETA);
        uploadBusInfoButton = findViewById(R.id.uploadLocation);

        // Initialize Firebase components
        firebaseAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Check if the user is authenticated
        if (firebaseAuth.getCurrentUser() != null) {
            currentUserID = firebaseAuth.getCurrentUser().getUid();
        } else {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity if the user is not authenticated
            return;
        }

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set the click listener for the upload button
        uploadBusInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadBusInfo();
            }
        });
    }

    private void uploadBusInfo() {
        String firstStopText = firstStop.getText().toString().trim();
        String lastStopText = lastStop.getText().toString().trim();
        String busNoText = busNo.getText().toString().trim();
        String locationText = location.getText().toString().trim();
        String etaText = busETA.getText().toString().trim();

        if (TextUtils.isEmpty(firstStopText) || TextUtils.isEmpty(lastStopText) || TextUtils.isEmpty(busNoText) || TextUtils.isEmpty(etaText) || TextUtils.isEmpty(locationText)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new BusInfo object with the provided data
        BusInfo busInfo = new BusInfo(locationText, busNoText, "", "", currentUserID, etaText, firstStopText, lastStopText);

        // Get a new document reference in the BusInfo collection
        DocumentReference documentReference = firestore.collection("BusInfo").document();

        // Set the document data using the BusInfo object
        documentReference.set(busInfo, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            String docId = documentReference.getId();
                            busInfo.setBusDocID(docId); // Set the document ID in the BusInfo object

                            documentReference.set(busInfo, SetOptions.merge())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(LocationActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(LocationActivity.this, "Upload Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LocationActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
