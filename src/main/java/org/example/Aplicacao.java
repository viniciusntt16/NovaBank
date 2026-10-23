package org.example;

import org.example.Console.ClienteConsole;
import org.example.Console.ContaConsole;
import org.example.Service.ClienteService;
import org.example.Service.ContaService;

import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClienteService clienteService = new ClienteService();
        ContaService contaService = new ContaService();

        ClienteConsole clienteConsole = new ClienteConsole(clienteService, scanner);

        ContaConsole contaConsole = new ContaConsole(
                contaService,
                clienteService,
                scanner
        );

        boolean executando = true;

        while (executando) {
            exibirMenu();

            System.out.print("Escolha uma opção: ");
            String entrada = scanner.nextLine();

            try {
                int opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        clienteConsole.cadastrarCliente();
                        break;

                    case 2:
                        clienteConsole.listarClientes();
                        break;

                    case 3:
                        clienteConsole.buscarClientePorCpf();
                        break;

                    case 4:
                        contaConsole.criarConta();
                        break;

                    case 5:
                        contaConsole.listarContas();
                        break;

                    case 6:
                        contaConsole.consultarSaldo();
                        break;

                    case 0:
                        executando = false;
                        System.out.println("Programa encerrado. Até logo!");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção numérica válida.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("========== NOVABANK ==========");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Buscar cliente por CPF");
        System.out.println("4 - Criar conta");
        System.out.println("5 - Listar contas");
        System.out.println("6 - Consultar saldo");
        System.out.println("0 - Sair");
    }
}
