package com.example.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tab1Cadastrar extends Fragment
{
    private Button buttonSalvar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextCep;
    private EditText editTextEndereco;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inicializarVars(inflater, container);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cadastrarUsuarios();
            }
        });
        
        return rootView;
    }

    private View inicializarVars(LayoutInflater inflater, ViewGroup container)
    {
        View rootView = inflater.inflate(R.layout.tab1_cadastrar, container, false);

        buttonSalvar = rootView.findViewById(R.id.button_salvar_id);
        editTextNome = rootView.findViewById(R.id.nome_edit_text1);
        editTextEmail = rootView.findViewById(R.id.email_edit_text1);
        editTextCep = rootView.findViewById(R.id.cep_edit_text1);
        editTextEndereco = rootView.findViewById(R.id.end_edit_text1);

        return rootView;
    }

    private void cadastrarUsuarios(){
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String cep = editTextCep.getText().toString();
        String endereco = editTextEndereco.getText().toString();

        if ((!nome.equals("")) && (!email.equals("")))
        {
            Contato pessoa = new Contato();
            pessoa.setNome(nome);
            pessoa.setEmail(email);
            pessoa.setCep(cep);
            pessoa.setEndereco(endereco);

            cadastrarUsuarios(pessoa);
            Toast.makeText(getContext().getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getContext().getApplicationContext(), "Campos vazios", Toast.LENGTH_SHORT).show();

        clearEditText();
    }

    private void clearEditText(){
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextCep.setText("");
        editTextEndereco.setText("");
    }

    private void cadastrarUsuarios (Contato pessoa)
    {
        databaseReference.child("contatos").push().setValue(pessoa);
    }
}
