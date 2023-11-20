package com.example.linkedin_mock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MessageActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Retrieve User object from Intent
        Intent intent = getIntent();
        if (intent != null) {
            user = (User) intent.getSerializableExtra("user");

            // Set up click listener for the "Send Email" button
            Button sendEmailButton = findViewById(R.id.send_email);
            sendEmailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendEmail();
                }
            });

            // Set up click listener for the "Call" button
            Button callButton = findViewById(R.id.call);
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callUser();
                }
            });
        }
    }

    private void sendEmail() {
        // Get the email content from the EditText
        EditText emailContentEditText = findViewById(R.id.email_content);
        String emailContent = emailContentEditText.getText().toString();

        // Create an Intent to send an email
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent);

        // Verify that the device has an app to handle this intent
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Choose an Email client"));
        }
    }


    private void callUser() {
        try {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + user.getPhoneNumber()));

            if (dialIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(dialIntent);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            // Handle the exception (e.g., request permissions)
        }
    }

}