package org.example.Console;

import org.example.Entidades.Cliente;
import org.example.Exceptions.ClienteException;
import org.example.Service.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ClienteConsole {

    private final ClienteService clienteService;
    private final Scanner scanner;

    public ClienteConsole(ClienteService clienteService, Scanner scanner) {
        this.clienteService = clienteService;
        this.scanner = scanner;
    }

    public void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try {
            Cliente cliente = clienteService.cadastrarCliente(nome, cpf);

            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());

        } catch (ClienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
            return;
        }

        System.out.println("===== CLIENTES CADASTRADOS =====");

        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("------------------------------");
        }
    }

    public void buscarClientePorCpf() {
        System.out.print("Informe o CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteService.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Cliente encontrado:");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
    }
}
