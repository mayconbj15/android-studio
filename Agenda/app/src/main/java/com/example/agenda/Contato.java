package com.example.agenda;

public class Contato
{
    private String nome;
    private String email;

   public Contato(){

   }

    @Override
    public String toString()
    {
        return nome + "\n" + email + "\n";
    }

    public String getNome()
    {
        return this.nome;
    }

    public void setNome(String nome){ this.nome = nome; }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email) { this.email = email; }
}
