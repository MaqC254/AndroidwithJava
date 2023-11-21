package com.example.learnclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name = findViewById(R.id.name);
    EditText course = findViewById(R.id.course);
    EditText yrOfStdy = findViewById(R.id.yearOfStudy);
    Button submit = findViewById(R.id.submit);
    String name1, course1, yrOfStdy1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                name1 = String.valueOf(name);
                course1 = String.valueOf(course);
                yrOfStdy1 = String.valueOf(yrOfStdy);
                Student [] students;
                Student student = new Student(name1, course1, yrOfStdy1);
            }
        });

    }
}