package com.example.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class Tab1Cadastrar extends Fragment {
    private Button buttonSalvar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private Button buttonAtualizar;
    private Button buttonDeletar;
    private EditText editTextUserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.tab1_cadastrar, container, false);

        buttonSalvar = rootView.findViewById(R.id.buttonSalvar);
        editTextNome = rootView.findViewById(R.id.textInputEditTextNome);
        editTextEmail = rootView.findViewById(R.id.textInputEditTextEmail);

        buttonAtualizar = rootView.findViewById(R.id.buttonAtualizar);
        buttonDeletar = rootView.findViewById(R.id.buttonDeletar);
    }
}
