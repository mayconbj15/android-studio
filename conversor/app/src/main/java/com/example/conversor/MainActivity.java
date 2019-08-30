package com.example.conversor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public double convertCelsiusToFahrenheit(double temperature){
        return ((1.8)*temperature) + 32;
    }

    public double convertCelsiusToReaumur(double temperature){
        return (0.8)*temperature;
    }

    public double convertCelsiusToRankine(double temperature){
        return (temperature*(1.8)) + 491.67;
    }

    public double convertCelsiusToKelvin(double temperature){
        return temperature + 273.15;
    }

    public void onRadioButtonClicked(View view) {
        EditText editTextTemperature = findViewById(R.id.editTextTemperature);

        if(editTextTemperature.getText().length() != 0){
            RadioGroup radioGroup = findViewById(R.id.radioGroup);

            double temperatureValue = Double.valueOf(editTextTemperature.getText().toString());
            double newTemperature = temperatureValue;

            // Check which radio button was clicked
            switch(radioGroup.getCheckedRadioButtonId()) {
                case R.id.radioButtonCelsius:
                    showResult(newTemperature, "ºC");
                    break;

                case R.id.radioButtonFahrenheit:
                    newTemperature = convertCelsiusToFahrenheit(temperatureValue);
                    showResult(newTemperature, "°F");
                    break;

                case R.id.radioButtonReaumur:
                    newTemperature = convertCelsiusToReaumur(temperatureValue);
                    showResult(newTemperature, "°Re");
                    break;

                case R.id.radioButtonRankine:
                    newTemperature = convertCelsiusToRankine(temperatureValue);
                    showResult(newTemperature, "°R");
                    break;

                case R.id.radioButtonKelvin:
                    newTemperature = convertCelsiusToKelvin(temperatureValue);
                    showResult(newTemperature, "°K");
                    break;
            }
        }
    }

    public void showResult(double newTemperature, String unit){
        TextView txtViewResult = findViewById(R.id.convertResult3);

        txtViewResult.setText(newTemperature + unit);
    }
}
