package org.example;

import org.example.Console.ClienteConsole;
import org.example.Console.ContaConsole;
import org.example.Service.ClienteService;
import org.example.Service.ContaService;
import org.example.Service.ExportacaoHistoricoService;
import java.io.IOException;
import java.nio.file.Path;

import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClienteService clienteService = new ClienteService();
        ContaService contaService = new ContaService();
        ExportacaoHistoricoService exportacaoHistoricoService =
                new ExportacaoHistoricoService();

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

                    case 7:
                        contaConsole.realizarDeposito();
                        break;

                    case 8:
                        contaConsole.realizarSaque();
                        break;

                    case 9:
                        contaConsole.realizarTransferencia();
                        break;

                    case 10:
                        contaConsole.consultarHistorico();
                        break;

                    case 11:
                        clienteConsole.atualizarCliente();
                        break;

                    case 12:
                        contaConsole.bloquearConta();
                        break;

                    case 13:
                        contaConsole.desbloquearConta();
                        break;

                    case 0:
                        confirmarSaida(
                                scanner,
                                contaService,
                                exportacaoHistoricoService
                        );
                        executando = false;
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
        System.out.println("7 - Realizar depósito");
        System.out.println("8 - Realizar saque");
        System.out.println("9 - Realizar transferência");
        System.out.println("10 - Consultar histórico");
        System.out.println("11 - Atualizar dados do cliente");
        System.out.println("12 - Bloquear conta");
        System.out.println("13 - Desbloquear conta");
        System.out.println("0 - Sair");
    }

    private static void confirmarSaida(
            Scanner scanner,
            ContaService contaService,
            ExportacaoHistoricoService exportacaoHistoricoService
    ) {
        while (true) {
            System.out.println("Deseja salvar o histórico desta interação?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

            switch (entrada) {
                case "1":
                    try {
                        Path arquivo = exportacaoHistoricoService.exportarHistorico(
                                contaService.listarContas()
                        );
                        System.out.println(
                                "Histórico salvo com sucesso em: " + arquivo
                        );
                    } catch (IOException e) {
                        System.out.println(
                                "Não foi possível salvar o histórico: " + e.getMessage()
                        );
                    }
                    return;

                case "2":
                    System.out.println("Histórico não será salvo.");
                    return;
                default:
                    System.out.println("Opção inválida. Digite 1 ou 2.");
            }
        }
    }

}
