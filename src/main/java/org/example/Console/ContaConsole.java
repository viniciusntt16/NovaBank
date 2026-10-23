package org.example.Console;

import org.example.Entidades.Cliente;
import org.example.Entidades.Conta;
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
            System.out.println("Cliente não encontrado. Cadastre o cliente antes de criar uma conta.");
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
        System.out.print("Informe o número da conta: ");
        String entrada = scanner.nextLine();

        try {
            int numero = Integer.parseInt(entrada);

            Conta conta = contaService.buscarContaPorNumero(numero);

            System.out.println("Número da conta: " + conta.getNumero());
            System.out.println("Titular: " + conta.getTitular());
            System.out.println("Saldo em centavos: " + conta.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("Número da conta inválido.");

        } catch (ContaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
