package com.example.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.content.Context.MODE_PRIVATE;

public class Tab3Atualizar extends Fragment
{
    private Button buttonBuscar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private Button buttonAtualizar;
    private Button buttonDeletar;
    private EditText editTextUserId;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference contatosReference = FirebaseDatabase.getInstance().getReference().child("contatos");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tab3_atualizar, container, false);
        buttonBuscar = rootView.findViewById(R.id.button_buscar_id);
        editTextNome = rootView.findViewById(R.id.nome_edit_text3);
        editTextEmail = rootView.findViewById(R.id.email_edit_text3);
        buttonAtualizar = rootView.findViewById(R.id.button_atualizar3);
        buttonDeletar = rootView.findViewById((R.id.button_delete_id));
        editTextUserId = rootView.findViewById(R.id.id_edit_text3);

        contatosReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("dataChange", "data change man");

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Contato contato = data.getValue(Contato.class);

                    Log.i("log", "nome " + contato.getNome());
                    Log.i("log", "email " + contato.getEmail());
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
                String email = editTextEmail.getText().toString();

                Contato contato = recuperarUsuarios(nome, email);


                if (contato != null)
                {
                    editTextNome.setText(contato.getNome());
                    editTextEmail.setText(contato.getEmail());
                }
                else
                    Toast.makeText(getContext().getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
            }


        });

        buttonAtualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();

                if(!nome.equals("") && !email.equals("")){
                    Contato contato = new Contato();
                    contato.setNome(nome);
                    contato.setEmail(email);

                    atualizaUsuarios (contato);
                    Toast.makeText(getContext().getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    editTextNome.setText("");
                    editTextEmail.setText("");
                }
                else
                    Toast.makeText(getContext().getApplicationContext(), "Favor preencher os campos", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeletar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!editTextUserId.getText().toString().equals("")){
                    int id = Integer.parseInt(editTextUserId.getText().toString());
                    deleteUsuarios (id);
                    Toast.makeText(getContext().getApplicationContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                    editTextNome.setText("");
                    editTextEmail.setText("");
                }
                else
                    Toast.makeText(getContext().getApplicationContext(), "Digite o id", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

    private Contato recuperarUsuarios (String nome, String email) {
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);

        String text = databaseReference.child("contatos").equalTo("nome").toString();
        //databaseReference.child("contatos").equalTo(nome, "nome").

        //Contato contato = new Contato(nome,nome);

        return contato;
    }

    private void atualizaUsuarios (Contato contato)
    {
        /*try
        {
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos" , MODE_PRIVATE, null);
            String update = "UPDATE Contato " + "SET nome = '" + contato.getNome() + "', " + "email = '" + contato.getEmail() + "' " + "WHERE id = " + contato.getId();
            bancoDeDados.execSQL(update);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }*/
    }//end atualizaUsuarios()

    private void deleteUsuarios (int id)
    {
        try
        {
            SQLiteDatabase bancoDeDados = getContext().getApplicationContext().openOrCreateDatabase("bancoContatos" , MODE_PRIVATE, null);
            String delete = "DELETE FROM Contato " + "WHERE id = " + id;
            bancoDeDados.execSQL(delete);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//end deleteUsuarios()
}
