package org.example;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            exibirMenu();

            System.out.print("Escolha uma opção: ");
            String entrada = scanner.nextLine();

            try {
                int opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        System.out.println("Cadastrar cliente - em desenvolvimento.");
                        break;

                    case 2:
                        System.out.println("Listar clientes - em desenvolvimento.");
                        break;

                    case 3:
                        System.out.println("Buscar cliente por CPF - em desenvolvimento.");
                        break;

                    case 4:
                        System.out.println("Criar conta - em desenvolvimento.");
                        break;

                    case 5:
                        System.out.println("Listar contas - em desenvolvimento.");
                        break;

                    case 6:
                        System.out.println("Consultar saldo - em desenvolvimento.");
                        break;

                    case 7:
                        System.out.println("Realizar depósito - em desenvolvimento.");
                        break;

                    case 8:
                        System.out.println("Realizar saque - em desenvolvimento.");
                        break;

                    case 9:
                        System.out.println("Realizar transferência - em desenvolvimento.");
                        break;

                    case 10:
                        System.out.println("Consultar histórico - em desenvolvimento.");
                        break;

                    case 0:
                        executando = false;
                        System.out.println("Programa encerrado. Até logo!");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite somente um número.");
            }

            System.out.println();
        }

        scanner.close();
    }

    public static void exibirMenu() {
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
        System.out.println("0 - Sair");
    }
}
