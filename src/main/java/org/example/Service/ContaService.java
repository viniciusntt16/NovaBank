package org.example.Service;

import org.example.Entidades.Cliente;
import org.example.Entidades.Conta;
import org.example.Exceptions.ContaException;

import java.util.ArrayList;
import java.util.List;

public class ContaService {

    private final List<Conta> contas = new ArrayList<>();
    private int proximoNumeroConta = 1001;

    public Conta criarConta(Cliente cliente) {
        if (cliente == null) {
            throw new ContaException(
                    "Não é possível criar uma conta para um cliente inexistente."
            );
        }

        long saldoInicialEmCentavos = 0L;

        Conta conta = new Conta(
                proximoNumeroConta,
                cliente.getNome(),
                saldoInicialEmCentavos
        );

        contas.add(conta);
        proximoNumeroConta++;

        return conta;
    }

    public Conta buscarContaPorNumero(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }

        throw new ContaException("Conta número " + numero + " não encontrada.");
    }

    public List<Conta> listarContas() {
        return List.copyOf(contas);
    }

    public void depositar(int numeroConta, long valorEmCentavos) {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.depositar(valorEmCentavos);
    }

    public void sacar(int numeroConta, long valorEmCentavos) {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.sacar(valorEmCentavos);
    }

    public void transferir(
            int numeroContaOrigem,
            int numeroContaDestino,
            long valorEmCentavos
    ) {
        if (numeroContaOrigem == numeroContaDestino) {
            throw new ContaException("Não é possível transferir para a mesma conta.");
        }

        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor da transferência deve ser maior que zero.");
        }

        Conta contaOrigem = buscarContaPorNumero(numeroContaOrigem);
        Conta contaDestino = buscarContaPorNumero(numeroContaDestino);

        if (contaOrigem.isBloqueada()) {
            throw new ContaException(
                    "A conta de origem está bloqueada e não pode realizar transferências."
            );
        }

        if (contaDestino.isBloqueada()) {
            throw new ContaException(
                    "A conta de destino está bloqueada e não pode receber transferências."
            );
        }

        if (valorEmCentavos > contaOrigem.getSaldo()) {
            throw new ContaException("Saldo insuficiente para realizar a transferência.");
        }

        if (Long.MAX_VALUE - contaDestino.getSaldo() < valorEmCentavos) {
            throw new ContaException(
                    "O saldo máximo da conta de destino foi excedido."
            );
        }

        contaOrigem.debitarTransferencia(
                valorEmCentavos,
                contaDestino.getNumero()
        );

        contaDestino.creditarTransferencia(
                valorEmCentavos,
                contaOrigem.getNumero()
        );
    }



    public List<String> consultarHistorico(int numeroConta) {
        Conta conta = buscarContaPorNumero(numeroConta);

        return conta.getHistorico();
    }

    public void bloquearConta(int numeroConta) {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.bloquear();
    }

    public void desbloquearConta(int numeroConta) {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.desbloquear();
    }

}
