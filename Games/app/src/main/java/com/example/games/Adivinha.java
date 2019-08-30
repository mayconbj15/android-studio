package com.example.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Adivinha extends AppCompatActivity {
    private int number;
    private int numberAttempts;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivinha);

        //gera um número aleatório
        random = new Random();
        number = random.nextInt(100);
        numberAttempts = 0;
    }

    public void theGame(View v){
        int number = takeNumber();

        TextView textView = findViewById(R.id.textViewResult);
        Toast.makeText(getApplicationContext(), ""+this.number, Toast.LENGTH_LONG).show();

        if(!compareNumber(number) || number < 0)
            loseGame();
        else{
            winGame();
        }
    }

    private int takeNumber(){
        int number;
        EditText editText = findViewById(R.id.editTextNumber);

        if(editText.getText().length() != 0){
            number = Integer.valueOf(editText.getText().toString());
        }
        else{
            number = -1;
        }
        return number;
    }

    private boolean compareNumber(int number){
        return (number == this.number);
    }

    private void winGame() {
        TextView textViewResult = findViewById(R.id.textViewResult);

        textViewResult.setText("ACERTÔ\n" + this.numberAttempts + "tentativas");

        this.number = random.nextInt(100);
        numberAttempts = 0;
    }

    private void loseGame(){
        TextView textViewResult = findViewById(R.id.textViewResult);

        textViewResult.setText("ERROU!");
        this.numberAttempts++;
    }



}
