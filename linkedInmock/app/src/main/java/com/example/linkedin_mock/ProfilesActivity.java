package com.example.linkedin_mock;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfilesActivity extends AppCompatActivity {

    private ListView listView;
    private List<User> userList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        // Initialize views
        listView = findViewById(R.id.appUsers);
        userList = new ArrayList<>();
        adapter = new UserAdapter(this, userList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = userList.get(position);

                // Create an Intent to start UserProfileActivity
                Intent intent = new Intent(ProfilesActivity.this, UserProfileActivity.class);
                intent.putExtra("user", (Serializable) selectedUser);
                startActivity(intent);
            }
        });
        // Retrieve data from Firebase
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear the existing list
                userList.clear();

                // Iterate through the dataSnapshot to get user data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    // Custom adapter to display user names
    private static class UserAdapter extends ArrayAdapter<User> {

        UserAdapter(Context context, List<User> userList) {
            super(context, 0, userList);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            User user = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
            }

            // Customize the display by setting the username
            TextView textUsername = convertView.findViewById(R.id.textUsername);
            TextView textEmail = convertView.findViewById(R.id.textEmail);
            TextView textGender = convertView.findViewById(R.id.textGender);
            ImageView imageView = convertView.findViewById(R.id.imageView);
            if (user != null) {
                textUsername.setText("Username: " + user.getUsername());
                textEmail.setText("Email: " + user.getEmail());
                textGender.setText("Gender: " + user.getGender());
                //imageView.setImageURI(Uri.parse(user.getImagePath()));
            }

            return convertView;
        }
    }

}
