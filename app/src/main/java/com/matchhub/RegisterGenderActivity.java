package com.matchhub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterGenderActivity extends AppCompatActivity {

    Button genderMaleBtn, genderFemaleBtn, nextBtn;
    Context context = this;

    //firebase
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gender);

        genderMaleBtn = findViewById(R.id.genderMaleBtn);
        genderFemaleBtn = findViewById(R.id.genderFemaleBtn);
        nextBtn = findViewById(R.id.nextBtn);

        //init firabase
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        //change gender as male on db
        genderMaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender= "Male";
                HashMap<String, Object> result = new HashMap<>();
                result.put("gender", gender);

                databaseReference.child(user.getUid()).updateChildren(result);

            }
        });

        //change gender as female on db
        genderFemaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender= "Female";
                HashMap<String, Object> result = new HashMap<>();
                result.put("gender", gender);

                databaseReference.child(user.getUid()).updateChildren(result);

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterBirthdayActivity.class);
                startActivity(intent);
            }
        });

    }
}
