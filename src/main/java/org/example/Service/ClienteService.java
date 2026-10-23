package org.example.Service;

import org.example.Entidades.Cliente;
import org.example.Exceptions.ClienteException;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrarCliente(String nome, String cpf) {
        if (nome == null || nome.isBlank()) {
            throw new ClienteException("O nome não pode estar vazio.");
        }

        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ClienteException("O CPF deve conter exatamente 11 dígitos.");
        }

        if (buscarClientePorCpf(cpf) != null) {
            throw new ClienteException("Já existe um cliente cadastrado com esse CPF.");
        }

        Cliente cliente = new Cliente(nome.trim(), cpf);
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
}
