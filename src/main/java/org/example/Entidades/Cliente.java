package org.example.Entidades;

public class Cliente {
    private String nome;
    private final String cpf;
    private String email;
    private String telefone;

    public Cliente(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public void atualizarNome(String novoNome) {
        this.nome = novoNome;
    }

    public void atualizarEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public void atualizarTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | CPF: " + cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
