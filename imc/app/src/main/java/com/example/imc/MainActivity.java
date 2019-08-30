package com.example.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateIMC(View view){
        TextView txtIMC = findViewById(R.id.textViewIMC);
        TextView txtClassification = findViewById(R.id.textViewClassification);
        EditText heightValue = findViewById(R.id.editTextHeight);
        EditText weightValue = findViewById(R.id.editTextWeight);

        double height =  Double.valueOf(heightValue.getText().toString());
        double weight = Double.valueOf(weightValue.getText().toString());

        double imc = getImc(height, weight);

        if(imc > 0){
            updateTextViewImc(txtIMC, imc); //atualiza a textView do IMC
            updateLabelClassification(txtClassification, imc); //atualiza a textView da classificação do imc
        }

    }

    private void updateLabelClassification(TextView txtClassification, double imc){
        if(imc < 16){
            txtClassification.setText("Magreza grave");
        }
        else if(imc < 17){
            txtClassification.setText("Magreza moderada");
        }
        else if(imc < 18.5){
            txtClassification.setText("Magreza leve");
        }
        else if(imc < 25){
            txtClassification.setText("Saudável");
        }
        else if(imc < 30){
            txtClassification.setText("Sobrepeso");
        }
        else if(imc < 35){
            txtClassification.setText("Obesidade Grau I");
        }
        else if(imc < 40){
            txtClassification.setText("Obesidade Grau II (severa)");
        }
        else{
            txtClassification.setText("Obesidade Grau III (mórbida)");
        }
    }

    private double getImc(double height, double weight){
        return Math.round(weight/(height*height));
    }

    private void updateTextViewImc(TextView txtIMC, double imc){
        txtIMC.clearComposingText(); //limpa o texto atual
        String newText = "IMC: " + Double.valueOf(imc).toString();

        txtIMC.setText(newText);
    }

    /**
     * ISSUES
     *  Tratar a divisão por 0
     *  Tratar a visualização das casas decimais no resultado
     */

}


