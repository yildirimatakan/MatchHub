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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gender);

        genderMaleBtn = findViewById(R.id.genderMaleBtn);
        genderFemaleBtn = findViewById(R.id.genderFemaleBtn);
        nextBtn = findViewById(R.id.nextBtn);

        genderFemaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set Users database gender=female

                //set button clicked style
                genderFemaleBtn.setText("Cinsiyet veritabanına girilecek");
                Toast.makeText(context, "Cinsiyet veritabanına girilecek", Toast.LENGTH_SHORT).show();
            }
        });

        genderMaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set Users database gender=male

                //set button clicked style

                genderMaleBtn.setText("Cinsiyet veritabanına girilecek");
                Toast.makeText(context, "Cinsiyet veritabanına girilecek", Toast.LENGTH_SHORT).show();
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
