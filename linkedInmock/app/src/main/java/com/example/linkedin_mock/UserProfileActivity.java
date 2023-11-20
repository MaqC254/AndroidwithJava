package com.example.linkedin_mock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Retrieve User object from Intent
        Intent intent = getIntent();
        if (intent != null) {
            User user = (User) intent.getSerializableExtra("user");
            // Display user details in the UI elements
            TextView textUsername = findViewById(R.id.textUsername);
            TextView textGender = findViewById(R.id.textGender);
            TextView textPhone = findViewById(R.id.textPhone);
            TextView textSkill = findViewById(R.id.textSkill);
            TextView textBio = findViewById(R.id.textBio);

            // Add more TextViews for other attributes

            if (user != null) {
                textUsername.setText("Username: " + user.getUsername());
                textGender.setText("Gender: " + user.getGender());
                textPhone.setText("Phone: " + user.getPhoneNumber());
                textSkill.setText("Skills: " + user.getSkills());
                textBio.setText("Bio: " + user.getShortBio());
                // Set other TextViews for additional attributes
            }
        }
    }
}