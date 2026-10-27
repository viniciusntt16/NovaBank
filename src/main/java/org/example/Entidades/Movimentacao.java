package org.example.Entidades;

public class Movimentacao {

    private final TipoMovimentacao tipo;
    private final long valorEmCentavos;
    private final long saldoAposMovimentacao;
    private final Integer numeroContaRelacionada;

    public Movimentacao(
            TipoMovimentacao tipo,
            long valorEmCentavos,
            long saldoAposMovimentacao,
            Integer numeroContaRelacionada
    ) {
        this.tipo = tipo;
        this.valorEmCentavos = valorEmCentavos;
        this.saldoAposMovimentacao = saldoAposMovimentacao;
        this.numeroContaRelacionada = numeroContaRelacionada;
    }

    @Override
    public String toString() {
        String valorFormatado = formatarValor(valorEmCentavos);
        String saldoFormatado = formatarValor(saldoAposMovimentacao);

        return switch (tipo) {
            case DEPOSITO ->
                    "DEPÓSITO | Valor: " + valorFormatado
                            + " | Saldo após operação: " + saldoFormatado;

            case SAQUE ->
                    "SAQUE | Valor: " + valorFormatado
                            + " | Saldo após operação: " + saldoFormatado;

            case TRANSFERENCIA_ENVIADA ->
                    "TRANSFERÊNCIA ENVIADA | Valor: " + valorFormatado
                            + " | Destino: " + numeroContaRelacionada
                            + " | Saldo após operação: " + saldoFormatado;

            case TRANSFERENCIA_RECEBIDA ->
                    "TRANSFERÊNCIA RECEBIDA | Valor: " + valorFormatado
                            + " | Origem: " + numeroContaRelacionada
                            + " | Saldo após operação: " + saldoFormatado;

            case CONTA_BLOQUEADA ->
                    "CONTA BLOQUEADA | Saldo atual: " + saldoFormatado;

            case CONTA_DESBLOQUEADA ->
                    "CONTA DESBLOQUEADA | Saldo atual: " + saldoFormatado;
        };
    }

    private String formatarValor(long valorEmCentavos) {
        long reais = valorEmCentavos / 100;
        long centavos = valorEmCentavos % 100;

        return String.format("R$ %d,%02d", reais, centavos);
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public long getValorEmCentavos() {
        return valorEmCentavos;
    }

    public long getSaldoAposMovimentacao() {
        return saldoAposMovimentacao;
    }

    public Integer getNumeroContaRelacionada() {
        return numeroContaRelacionada;
    }
}
