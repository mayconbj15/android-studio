package com.example.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Tab3Atualizar extends Fragment
{
    private Button buttonBuscar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextCep;
    private EditText editTextEndereco;
    private Button buttonAtualizar;
    private Button buttonDeletar;
    private EditText editTextUserId;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference contatosReference = FirebaseDatabase.getInstance().getReference().child("contatos");

    private HashMap<String,Contato> contatos = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inicializarVars(inflater, container);

        contatosReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Contato contato = data.getValue(Contato.class);

                    if (!contatos.containsValue(contato))
                        contatos.put(contato.getNome(), contato);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext().getApplicationContext(), "Erro na busca no banco de dados", Toast.LENGTH_SHORT).show();
            }
        });

        buttonBuscar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nome = editTextNome.getText().toString();

                recuperarUsuario(nome);

            }


        });

        buttonAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String key = editTextUserId.getText().toString();

                if(key != null && !key.equals("")){
                    atualizaUsuario(key);
                }
            }
        });

        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletaUsuario();
            }
        });

        return rootView;
    }

    private Contato recuperarUsuario(String nome) {
        Contato contato = contatos.get(nome);

        if (contato != null)
        {
            editTextNome.setText(contato.getNome());
            editTextEmail.setText(contato.getEmail());
            editTextCep.setText(contato.getCep());
            editTextEndereco.setText(contato.getEndereco());
            findKey(contato.getNome(), contato.getEmail(), contato.getCep(), contato.getEndereco());
        }
        else
            Toast.makeText(getContext().getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_SHORT).show();

        return contato;
    }

    private void atualizaUsuario(String key){
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String cep = editTextCep.getText().toString();
        String endereco = editTextEndereco.getText().toString();

        Contato contato = new Contato(nome, email, cep, endereco);

        atualizaUsuario(key, contato);
    }
    private void atualizaUsuario(String nome, String email, String cep, String endereco)
    {
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setCep(cep);
        contato.setEndereco(endereco);

        atualizaFirebase(contato);

        Toast.makeText(getContext().getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();

        clearView();
    }//end atualizaUsuarios()

    private void atualizaUsuario(String key, Contato contato) {
        //contatosReference.child(key).push().setValue(contato);

        Map<String, Object> update = new HashMap<>();

        update.put(key, contato);

        contatosReference.updateChildren(update);

        Toast.makeText(getContext().getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();

        clearView();
    }//end atualizaUsuarios()

    private void atualizaFirebase(Contato contato){
        Map<String, Object> update = new HashMap<>();
        update.put(contato.getNome(), contato);

        contatosReference.updateChildren(update);

    }

    private void deletaUsuario(){
        String key = editTextUserId.getText().toString();

        if(key != null && !key.equals(""))
            deletaUsuario(key);
    }

    private void deletaUsuario(String key){
        contatosReference.child(key).removeValue();
    }

    private View inicializarVars(LayoutInflater inflater, ViewGroup container)
    {
        View rootView = inflater.inflate(R.layout.tab3_atualizar, container, false);

        editTextNome = rootView.findViewById(R.id.nome_edit_text3);
        editTextEmail = rootView.findViewById(R.id.email_edit_text3);
        editTextUserId = rootView.findViewById(R.id.id_edit_text3);
        editTextCep = rootView.findViewById(R.id.cep_edit_text3);
        editTextEndereco = rootView.findViewById(R.id.end_edit_text3);
        buttonBuscar = rootView.findViewById(R.id.button_buscar_id);
        buttonAtualizar = rootView.findViewById(R.id.button_atualizar3);
        buttonDeletar = rootView.findViewById((R.id.button_delete_id));

        return rootView;
    }

    private void findKey(final String nome, final String email, final String cep, final String endereco){

        contatosReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Contato contato = data.getValue(Contato.class);

                    if(contato.getNome().equals(nome) && contato.getEmail().equals(email) && contato.getCep().equals(cep) && contato.getEndereco().equals(endereco)){
                        editTextUserId.setText(data.getRef().getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext().getApplicationContext(), "Erro na busca no banco de dados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearView(){
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextCep.setText("");
        editTextEndereco.setText("");
        editTextUserId.setText("");
    }
}
