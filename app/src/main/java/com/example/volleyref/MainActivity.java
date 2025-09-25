package com.example.volleyref;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button staff = findViewById(R.id.btn_staff);
        Button sigs  = findViewById(R.id.btn_signatures);
        staff.setOnClickListener(v -> startActivity(new Intent(this, StaffSetupActivity.class)));
        sigs.setOnClickListener(v -> startActivity(new Intent(this, PreMatchSignaturesActivity.class)));
    }
}
