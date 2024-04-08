package com.example.fightclub;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    EditText editEmailregister, editusernameregister, editPasswordregister;
    Button buttonregister;
    FirebaseAuth fAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        editEmailregister = findViewById(R.id.editTextEmailregister);
        editusernameregister = findViewById(R.id.editTextusernameregister);
        editPasswordregister = findViewById(R.id.editTextPasswordregister);
        buttonregister = findViewById(R.id.buttonEmailregister);
        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Registration.class));
            finish();
        }

        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmailregister.getText().toString().trim();
                String password = editPasswordregister.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    editEmailregister.setError("Email is Required");
                }
                if(TextUtils.isEmpty(email)){
                    editPasswordregister.setError("Password is Required");
                }
                if(editPasswordregister.length() < 8){
                    editPasswordregister.setError("Password should be more than 8 characters");
                }
                if(TextUtils.isEmpty(email)){
                    editusernameregister.setError("username is Required");
                }
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Registration.class));
                        }
                        else {
                            Toast.makeText(Registration.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}