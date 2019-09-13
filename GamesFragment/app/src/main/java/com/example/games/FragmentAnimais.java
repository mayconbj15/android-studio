package com.example.games;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAnimais extends Fragment implements View.OnClickListener {
    private String[] animals = {"ape", "cat", "cow", "dog", "duck", "elephant", "horse", "lion", "moose", "owl", "pig", "rooster", "sheep", "tiger", "turkey"};

    private ImageView[] btnAnimals = new ImageView[animals.length];

    private Map<Integer,String> mapButtonId = new HashMap<Integer, String>();

    private MediaPlayer mediaPlayer;

    public FragmentAnimais() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_animais, container, false);

        for(int i=0; i<animals.length; i++){
            btnAnimals[i] = view.findViewById(getIdByName(animals[i], "id")); //pega o id do botão através de seu nome e consequentemente o botão pelo id
            btnAnimals[i].setOnClickListener(this); //associa a função de evento ao botão
            mapButtonId.put(getIdByName(animals[i], "id"), animals[i]); //coloca na hash o atual nome do animal identifico pelo seu id
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v){
        //openContextMenu(v);

        //int id = getIdByName(mapButtonId.get(v.getId()), "raw");
        mediaPlayer = MediaPlayer.create(v.getContext(), getIdByName(mapButtonId.get(v.getId()), "raw"));

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
        return getResources().getIdentifier(name, resource, getContext().getPackageName());
    }

}
