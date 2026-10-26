package org.example.Service;

import org.example.Entidades.Cliente;
import org.example.Exceptions.ClienteException;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrarCliente(
            String nome,
            String cpf,
            String email,
            String telefone
    )
    {
        if (nome == null || nome.isBlank()) {
            throw new ClienteException("O nome não pode estar vazio.");
        }

        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ClienteException("O CPF deve conter exatamente 11 dígitos.");
        }

        if (buscarClientePorCpf(cpf) != null) {
            throw new ClienteException("Já existe um cliente cadastrado com esse CPF.");
        }

        Cliente cliente = new Cliente(nome, cpf, email, telefone);
        clientes.add(cliente);

        return cliente;
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }

        return null;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public void atualizarNomeCliente(String cpf, String novoNome) {
        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente == null) {
            throw new ClienteException("Cliente não encontrado.");
        }

        if (novoNome == null || novoNome.isBlank()) {
            throw new ClienteException("O nome do cliente não pode ser vazio.");
        }

        cliente.atualizarNome(novoNome.trim());
    }

    public void atualizarEmailCliente(String cpf, String novoEmail) {
        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente == null) {
            throw new ClienteException("Cliente não encontrado.");
        }

        if (novoEmail == null || novoEmail.isBlank()) {
            throw new ClienteException("O e-mail não pode ser vazio.");
        }

        if (!novoEmail.contains("@")) {
            throw new ClienteException("E-mail inválido.");
        }

        cliente.atualizarEmail(novoEmail.trim());
    }

    public void atualizarTelefoneCliente(String cpf, String novoTelefone) {
        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente == null) {
            throw new ClienteException("Cliente não encontrado.");
        }

        if (novoTelefone == null || novoTelefone.isBlank()) {
            throw new ClienteException("O telefone não pode ser vazio.");
        }

        cliente.atualizarTelefone(novoTelefone.trim());
    }
}
