package com.matchhub;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class RegisterBirthdayActivity extends AppCompatActivity {

    Context context = this;
    TextView birthdate;
    Button nextBtn;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    //firebase
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_birthday);

        birthdate = findViewById(R.id.birthdate);
        nextBtn = findViewById(R.id.nextBtn);

        //init firabase
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = day + "/" + month + "/" + year;
        birthdate.setText(date);

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String monthString = "Ocak";
                month = month + 1;

                switch (month){
                    case 1:
                        monthString = "Ocak";
                        break;
                    case 2:
                        monthString = "Şubat";
                        break;
                    case 3:
                        monthString = "Mart";
                        break;
                    case 4:
                        monthString = "Nisan";
                        break;
                    case 5:
                        monthString = "Mayıs";
                        break;
                    case 6:
                        monthString = "Haziran";
                        break;
                    case 7:
                        monthString = "Temmuz";
                        break;
                    case 8:
                        monthString = "Ağustos";
                        break;
                    case 9:
                        monthString = "Eylül";
                        break;
                    case 10:
                        monthString = "Ekim";
                        break;
                    case 11:
                        monthString = "Kasım";
                        break;
                    case 12:
                        monthString = "Aralık";
                        break;
                }

                String date = day + "/" + monthString + "/" + year;
                birthdate.setText(date);

                //database
                String dateFb = monthString + " " + day + ", " + year;
                HashMap<String, Object> result = new HashMap<>();
                result.put("birthday", dateFb);

                databaseReference.child(user.getUid()).updateChildren(result);

            }
        };

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterNameActivity.class);
                startActivity(intent);
            }
        });

    }
}
