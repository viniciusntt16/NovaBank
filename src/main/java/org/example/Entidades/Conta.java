package org.example.Entidades;

import org.example.Exceptions.ContaException;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private final int numero;
    private final String titular;
    private long saldo;
    private boolean bloqueada;
    private final List<Movimentacao> historico = new ArrayList<>();

    public Conta(int numero, String titular, long saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    private void registrarHistorico(
            TipoMovimentacao tipo,
            long valorEmCentavos,
            Integer numeroContaRelacionada
    ) {
        Movimentacao movimentacao = new Movimentacao(
                tipo,
                valorEmCentavos,
                saldo,
                numeroContaRelacionada
        );
        historico.add(movimentacao);
    }

    public void depositar(long valorEmCentavos) {
        verificarMovimentacaoPermitida();

        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor do depósito deve ser maior que zero.");
        }

        if (Long.MAX_VALUE - saldo < valorEmCentavos) {
            throw new ContaException("O saldo máximo da conta foi excedido.");
        }

        saldo += valorEmCentavos;

        registrarHistorico(
                TipoMovimentacao.DEPOSITO,
                valorEmCentavos,
                null
        );
    }

    public void sacar(long valorEmCentavos) {
        verificarMovimentacaoPermitida();

        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor do saque deve ser maior que zero.");
        }

        if (valorEmCentavos > saldo) {
            throw new ContaException("Saldo insuficiente.");
        }

        saldo -= valorEmCentavos;

        registrarHistorico(
                TipoMovimentacao.SAQUE,
                valorEmCentavos,
                null
        );
    }

    public void debitarTransferencia(
            long valorEmCentavos,
            int numeroContaDestino
    ) {
        verificarMovimentacaoPermitida();

        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor da transferência deve ser maior que zero.");
        }

        if (valorEmCentavos > saldo) {
            throw new ContaException("Saldo insuficiente para realizar a transferência.");
        }

        saldo -= valorEmCentavos;

        registrarHistorico(
                TipoMovimentacao.TRANSFERENCIA_ENVIADA,
                valorEmCentavos,
                numeroContaDestino
        );
    }

    public void creditarTransferencia(
            long valorEmCentavos,
            int numeroContaOrigem
    ) {
        verificarMovimentacaoPermitida();

        if (valorEmCentavos <= 0) {
            throw new ContaException("O valor da transferência deve ser maior que zero.");
        }

        if (Long.MAX_VALUE - saldo < valorEmCentavos) {
            throw new ContaException("O saldo máximo da conta foi excedido.");
        }

        saldo += valorEmCentavos;

        registrarHistorico(
                TipoMovimentacao.TRANSFERENCIA_RECEBIDA,
                valorEmCentavos,
                numeroContaOrigem
        );
    }

    public void bloquear() {
        if (bloqueada) {
            throw new ContaException("A conta já está bloqueada.");
        }

        bloqueada = true;

        registrarHistorico(
                TipoMovimentacao.CONTA_BLOQUEADA,
                0L,
                null
        );
    }

    public void desbloquear() {
        if (!bloqueada) {
            throw new ContaException("A conta já está desbloqueada.");
        }

        bloqueada = false;

        registrarHistorico(
                TipoMovimentacao.CONTA_DESBLOQUEADA,
                0L,
                null
        );
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    private void verificarMovimentacaoPermitida() {
        if (bloqueada) {
            throw new ContaException(
                    "A conta está bloqueada e não pode realizar movimentações."
            );
        }
    }

    public String getStatus() {
        if (bloqueada) {
            return "BLOQUEADA";
        }
        return "ATIVA";
    }

    @Override
    public String toString() {
        return "Número da conta: " + numero +
                "\nTitular: " + titular +
                "\nSaldo: " + getSaldoFormatado() +
                "\nStatus: " + getStatus();
    }

    public List<Movimentacao> getHistorico() {
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
