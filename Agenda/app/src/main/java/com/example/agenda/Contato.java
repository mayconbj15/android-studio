package com.example.agenda;

public class Contato
{
    private String nome;
    private String email;
    private String key;
    private String cep;
    private String endereco;

   public Contato(){

   }

    public Contato(String nome, String email, String cep, String endereco) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
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


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
