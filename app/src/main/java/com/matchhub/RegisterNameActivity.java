package com.matchhub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterNameActivity extends AppCompatActivity {

    EditText nameET;
    Button nextBtn;
    Context context = this;

    //firebase
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_name);

        nameET = findViewById(R.id.nameET);
        nextBtn = findViewById(R.id.nextBtn);

        //init firabase
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //database
                name = nameET.getText().toString().trim();
                HashMap<String, Object> result = new HashMap<>();
                result.put("name", name);
                databaseReference.child(user.getUid()).updateChildren(result);

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
