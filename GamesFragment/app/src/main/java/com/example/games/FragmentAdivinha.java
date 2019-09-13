package com.example.games;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAdivinha extends Fragment{
    private int number;
    private int numberAttempts;

    private Button buttonTest;

    private Random random;

    public FragmentAdivinha() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_adivinha, container, false);

        buttonTest = view.findViewById(R.id.buttonText);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theGame();
            }
        });

        //gera um número aleatório
        random = new Random();
        number = random.nextInt(100);
        numberAttempts = 0;

        // Inflate the layout for this fragment
        return view;
    }

    public void theGame(){
        int number = takeNumber();

        if(!compareNumber(number) || number < 0)
            loseGame();
        else{
            winGame();
        }
    }

    private int takeNumber(){
        int number;
        EditText editText = getView().findViewById(R.id.editTextNumber);

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
        TextView textViewResult = getView().findViewById(R.id.textViewResult);

        textViewResult.setText("ACERTÔ\n" + this.numberAttempts + "tentativas");

        this.number = random.nextInt(100);
        numberAttempts = 0;
    }

    private void loseGame(){
        TextView textViewResult = getView().findViewById(R.id.textViewResult);

        textViewResult.setText("ERROU!");
        this.numberAttempts++;
    }

}
