package org.example.Entidades;

public class Conta {

    private int numero;
    private String titular;
    private long saldo;

    public Conta(int numero, String titular, long saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Número da conta: " + numero +
                "\nTitular: " + titular +
                "\nSaldo em centavos: " + saldo;
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
