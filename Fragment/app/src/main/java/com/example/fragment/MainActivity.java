package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment,
                    new BlankFragment()).commit();
        }

        Button btnFragm1 = findViewById(R.id.buttonFragment);
        Button btnFragm2 = findViewById(R.id.buttonFragment2);

        btnFragm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(
                        R.id.fragment,
                        new BlankFragment()
                ).commit();
            }
        });

        btnFragm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(
                        R.id.fragment2,
                        new SecondFragment()
                ).commit();
            }
        });

    }
}
