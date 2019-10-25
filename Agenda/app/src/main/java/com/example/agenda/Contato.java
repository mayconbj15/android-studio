package com.example.agenda;

public class Contato
{
    private String nome;
    private String email;
    private String key;

   public Contato(){

   }

    @Override
    public String toString()
    {
        return nome + "\n" + email + "\n";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
