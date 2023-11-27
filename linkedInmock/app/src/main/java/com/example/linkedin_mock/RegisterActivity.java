package com.example.linkedin_mock;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView imageView;
    String imagePath;

    private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText,
            bioEditText, skillsEditText, phoneNoEditText;
    FirebaseAuth mAuth;
    private DatabaseReference mDataBase;


    public void sendData(View v){
        writeNewUser();
    }

    public void writeNewUser(){
        Spinner genderSpinner = (Spinner) findViewById(R.id.gender);
        String gender = genderSpinner.getSelectedItem().toString();
        User user = new User(usernameEditText.getText().toString(), emailEditText.getText().toString(), gender, bioEditText.getText().toString(), skillsEditText.getText().toString(), phoneNoEditText.getText().toString(),imagePath);

        mDataBase.child("users").child(user.getUsername()).setValue(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        imageView = findViewById(R.id.imageView);

        // Initialize UI components
        Spinner genders=findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        genders.setAdapter(adapter);
        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        bioEditText = findViewById(R.id.bio);
        skillsEditText = findViewById(R.id.skills);
        phoneNoEditText = findViewById(R.id.phone_no);
        Button signUpButton = findViewById(R.id.register);
        Button goToSignInButton = findViewById(R.id.goToSignIn);
        ImageView imageView = findViewById(R.id.imageView);
        Button addImage = findViewById(R.id.addImage);


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent, PICK_IMAGE_REQUEST);
            }
        });



        goToSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to the LoginActivity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });

        // Set click listener for the Sign Up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtain user inputs
                String email = String.valueOf(emailEditText.getText());
                String password = String.valueOf(passwordEditText.getText());
                String username = String.valueOf(usernameEditText.getText());
                String confirmPassword = String.valueOf(confirmPasswordEditText.getText());
                String bio = String.valueOf(bioEditText.getText());
                String skills = String.valueOf(skillsEditText.getText());
                String phoneNo = String.valueOf(phoneNoEditText.getText());

                //Check for empty fields
                if(TextUtils.isEmpty(email ) || TextUtils.isEmpty(password) ||TextUtils.isEmpty(username) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(bio) || TextUtils.isEmpty(skills) || TextUtils.isEmpty(phoneNo)) {
                    Toast.makeText(RegisterActivity.this, "Enter all required details", Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if passwords match
                if(!password.equals(confirmPassword)){
                    Toast.makeText(RegisterActivity.this, "The passwords you entered do not match", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegisterActivity.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Could not create account at this time.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                sendData(view);
                // For now, let's just display the user inputs in the log
               // displayUserInputs(username, email, password, confirmPassword, bio, skills, phoneNo);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            // Now you can do something with the selected image URI, for example, set it to an ImageView
            imageView.setImageURI(selectedImageUri);

            // Save it to a variable or use it as needed
            imagePath = selectedImageUri.toString();
        }
    }
}
