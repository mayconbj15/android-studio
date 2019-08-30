package com.example.conversor_temperatura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    //protected SensorActivity sensorActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void changeActivity(View view){
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }
}
