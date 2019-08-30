package com.example.games;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Bichos extends AppCompatActivity implements View.OnClickListener {
    private String[] animals = {"ape", "cat", "cow", "dog", "duck", "elephant", "horse", "lion", "moose", "owl", "pig", "rooster", "sheep", "tiger", "turkey"};

    private ImageView[] btnAnimals = new ImageView[animals.length];

    private Map<Integer,String> mapButtonId = new HashMap<Integer, String>();

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bichos);

        for(int i=0; i<animals.length; i++){
            btnAnimals[i] = findViewById(getIdByName(animals[i], "id")); //pega o id do botão através de seu nome e consequentemente o botão pelo id
            btnAnimals[i].setOnClickListener(this); //associa a função de evento ao botão
            mapButtonId.put(getIdByName(animals[i], "id"), animals[i]); //coloca na hash o atual nome do animal identifico pelo seu id
        }
    }

    @Override
    public void onClick(View v){
        Toast.makeText(getApplicationContext(), getIdByName(mapButtonId.get(v.getId()), "raw"), Toast.LENGTH_LONG).show();
        openContextMenu(v);
        //int id = getIdByName(mapButtonId.get(v.getId()), "raw");
        mediaPlayer = MediaPlayer.create(Bichos.this, getIdByName(mapButtonId.get(v.getId()), "raw"));

        if(mediaPlayer != null)
            mediaPlayer.start();
    }

    /**
     * Metódo que pega o Id de um componente através do seu nome
     * @param name
     * @param resource
     * @return
     */
    public int getIdByName(String name, String resource){
        return getResources().getIdentifier(name, resource, getApplicationContext().getPackageName());
    }
}
