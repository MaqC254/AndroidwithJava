package com.example.linkedin_mock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfilesActivity extends AppCompatActivity {
    DatabaseReference mRef;
    ListView users;
    ArrayList <String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRef = FirebaseDatabase.getInstance().getReference("users");
        users = (ListView) findViewById(R.id.appUsers);

        setContentView(R.layout.activity_profiles);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    User yuser = snapshot.getValue(User.class);
                    arrayList.add(yuser.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        System.out.println(arrayList);
        ArrayAdapter <String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        users.setAdapter(myArrayAdapter);
//        String[] data = {"item 1", "item 2", "item 3"};
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
//        users.setAdapter(adapter);
    }
}