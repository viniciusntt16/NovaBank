package org.example.Console;

import org.example.Entidades.Cliente;
import org.example.Entidades.Conta;
import org.example.Entidades.Movimentacao;
import org.example.Exceptions.ContaException;
import org.example.Service.ClienteService;
import org.example.Service.ContaService;

import java.util.List;
import java.util.Scanner;

public class ContaConsole {

    private final ContaService contaService;
    private final ClienteService clienteService;
    private final Scanner scanner;

    public ContaConsole(
            ContaService contaService,
            ClienteService clienteService,
            Scanner scanner
    ) {
        this.contaService = contaService;
        this.clienteService = clienteService;
        this.scanner = scanner;
    }

    public void criarConta() {
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteService.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println(
                    "Cliente não encontrado. Cadastre o cliente antes de criar uma conta."
            );
            return;
        }

        Conta conta = contaService.criarConta(cliente);

        System.out.println("Conta criada com sucesso!");
        System.out.println(conta);
    }

    public void listarContas() {
        List<Conta> contas = contaService.listarContas();

        if (contas.isEmpty()) {
            System.out.println("Não há contas cadastradas.");
            return;
        }

        System.out.println("===== CONTAS CADASTRADAS =====");

        for (Conta conta : contas) {
            System.out.println(conta);
            System.out.println("------------------------------");
        }
    }

    public void consultarSaldo() {
        try {
            System.out.print("Informe o número da conta: ");
            int numeroConta = Integer.parseInt(scanner.nextLine());

            Conta conta = contaService.buscarContaPorNumero(numeroConta);

            System.out.println("Número da conta: " + conta.getNumero());
            System.out.println("Titular: " + conta.getTitular());
            System.out.println("Saldo: " + conta.getSaldoFormatado());

        } catch (NumberFormatException e) {
            System.out.println("Número da conta inválido.");

        } catch (ContaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void realizarDeposito() {
        try {
            System.out.print("Número da conta: ");
            int numeroConta = Integer.parseInt(scanner.nextLine());

            System.out.print("Valor do depósito em centavos: ");
            long valorEmCentavos = Long.parseLong(scanner.nextLine());

            contaService.depositar(numeroConta, valorEmCentavos);

            Conta conta = contaService.buscarContaPorNumero(numeroConta);

            System.out.println("Depósito realizado com sucesso!");
            System.out.println("Saldo atual: " + conta.getSaldoFormatado());

        } catch (NumberFormatException e) {
            System.out.println("Número da conta ou valor inválido.");

        } catch (ContaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void realizarSaque() {
        try {
            System.out.print("Número da conta: ");
            int numeroConta = Integer.parseInt(scanner.nextLine());

            System.out.print("Valor do saque em centavos: ");
            long valorEmCentavos = Long.parseLong(scanner.nextLine());

            contaService.sacar(numeroConta, valorEmCentavos);

            Conta conta = contaService.buscarContaPorNumero(numeroConta);

            System.out.println("Saque realizado com sucesso!");
            System.out.println("Saldo atual: " + conta.getSaldoFormatado());

        } catch (NumberFormatException e) {
            System.out.println("Número da conta ou valor inválido.");

        } catch (ContaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void realizarTransferencia() {
        try {
            System.out.print("Número da conta de origem: ");
            int numeroContaOrigem = Integer.parseInt(scanner.nextLine());

            System.out.print("Número da conta de destino: ");
            int numeroContaDestino = Integer.parseInt(scanner.nextLine());

            System.out.print("Valor da transferência em centavos: ");
            long valorEmCentavos = Long.parseLong(scanner.nextLine());

            contaService.transferir(
                    numeroContaOrigem,
                    numeroContaDestino,
                    valorEmCentavos
            );

            System.out.println("Transferência realizada com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Número da conta ou valor inválido.");

        } catch (ContaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void consultarHistorico() {
        try {
            System.out.print("Informe o número da conta: ");
            int numeroConta = Integer.parseInt(scanner.nextLine());

            List<Movimentacao> historico = contaService.consultarHistorico(numeroConta);

            if (historico.isEmpty()) {
                System.out.println("Não há movimentações para esta conta.");
                return;
            }

            System.out.println("===== HISTÓRICO DA CONTA " + numeroConta + " =====");

            for (Movimentacao movimentacao : historico) {
                System.out.println(movimentacao);
                System.out.println("------------------------------");
            }

        } catch (NumberFormatException e) {
            System.out.println("Número da conta inválido.");

        } catch (ContaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void bloquearConta() {
        alterarStatusConta(true);
    }

    public void desbloquearConta() {
        alterarStatusConta(false);
    }

    private void alterarStatusConta(boolean bloquear) {
        try {
            System.out.print("Informe o número da conta: ");
            int numeroConta = Integer.parseInt(scanner.nextLine());

            if (bloquear) {
                contaService.bloquearConta(numeroConta);
                System.out.println("Conta bloqueada com sucesso!");
            } else {
                contaService.desbloquearConta(numeroConta);
                System.out.println("Conta desbloqueada com sucesso!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Número da conta inválido.");

        } catch (ContaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
