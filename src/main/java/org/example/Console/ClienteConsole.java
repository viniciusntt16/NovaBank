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

        System.out.print("E-mail (opcional): ");
        String email = scanner.nextLine();

        System.out.print("Telefone (opcional): ");
        String telefone = scanner.nextLine();

        try {
            Cliente cliente = clienteService.cadastrarCliente(nome, cpf, email, telefone);

            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());

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
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
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

    public void atualizarCliente() {
        System.out.print("Informe o CPF do cliente que deseja atualizar: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteService.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        boolean atualizando = true;

        while (atualizando) {
            System.out.println("===== ATUALIZAR CLIENTE =====");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("1 - Atualizar nome");
            System.out.println("2 - Atualizar e-mail");
            System.out.println("3 - Atualizar telefone");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

            try {
                int opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        System.out.print("Informe o novo nome: ");
                        String novoNome = scanner.nextLine();

                        clienteService.atualizarNomeCliente(cpf, novoNome);

                        System.out.println("Nome atualizado com sucesso!");
                        break;

                    case 2:
                        System.out.print("Informe o novo e-mail: ");
                        String novoEmail = scanner.nextLine();

                        clienteService.atualizarEmailCliente(cpf, novoEmail);

                        System.out.println("E-mail atualizado com sucesso!");
                        break;

                    case 3:
                        System.out.print("Informe o novo telefone: ");
                        String novoTelefone = scanner.nextLine();

                        clienteService.atualizarTelefoneCliente(cpf, novoTelefone);

                        System.out.println("Telefone atualizado com sucesso!");
                        break;

                    case 0:
                        atualizando = false;
                        System.out.println("Atualização finalizada.");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção numérica válida.");

            } catch (ClienteException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        }
    }

}
