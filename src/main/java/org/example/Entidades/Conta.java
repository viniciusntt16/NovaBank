package org.example.Entidades;

import org.example.Exceptions.ContaException;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private final int numero;
    private final String titular;
    private long saldo;
    private final List<String> historico = new ArrayList<>();

    public Conta(int numero, String titular, long saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    private void registrarHistorico(String descricao) {
        historico.add(descricao);
    }

    public void depositar(long valorEmCentavos) {
        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor do depósito deve ser maior que zero.");
        }

        if (Long.MAX_VALUE - saldo < valorEmCentavos) {
            throw new ContaException("O saldo máximo da conta foi excedido.");
        }

        saldo += valorEmCentavos;

        registrarHistorico(
                "DEPÓSITO | Valor: " + valorEmCentavos
                        + " centavos | Saldo: " + saldo + " centavos"
        );
    }

    public void sacar(long valorEmCentavos) {
        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor do saque deve ser maior que zero.");
        }

        if (valorEmCentavos > saldo) {
            throw new ContaException("Saldo insuficiente.");
        }

        saldo -= valorEmCentavos;

        registrarHistorico(
                "SAQUE | Valor: " + valorEmCentavos
                        + " centavos | Saldo: " + saldo + " centavos"
        );
    }

    public void debitarTransferencia(
            long valorEmCentavos,
            int numeroContaDestino
    ) {
        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor da transferência deve ser maior que zero.");
        }

        if (valorEmCentavos > saldo) {
            throw new ContaException("Saldo insuficiente para realizar a transferência.");
        }

        saldo -= valorEmCentavos;

        registrarHistorico(
                "TRANSFERÊNCIA ENVIADA | Valor: " + valorEmCentavos
                        + " centavos | Destino: " + numeroContaDestino
                        + " | Saldo: " + saldo + " centavos"
        );
    }

    public void creditarTransferencia(
            long valorEmCentavos,
            int numeroContaOrigem
    ) {
        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor da transferência deve ser maior que zero.");
        }

        if (Long.MAX_VALUE - saldo < valorEmCentavos) {
            throw new ContaException("O saldo máximo da conta foi excedido.");
        }

        saldo += valorEmCentavos;

        registrarHistorico(
                "TRANSFERÊNCIA RECEBIDA | Valor: " + valorEmCentavos
                        + " centavos | Origem: " + numeroContaOrigem
                        + " | Saldo: " + saldo + " centavos"
        );
    }

    @Override
    public String toString() {
        return "Número da conta: " + numero +
                "\nTitular: " + titular +
                "\nSaldo: " + getSaldoFormatado();
    }

    public List<String> getHistorico() {
        return List.copyOf(historico);
    }

    public String getSaldoFormatado() {
        long reais = saldo / 100;
        long centavos = saldo % 100;

        return String.format("R$ %d,%02d", reais, centavos);
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public long getSaldo() {
        return saldo;
    }
}
