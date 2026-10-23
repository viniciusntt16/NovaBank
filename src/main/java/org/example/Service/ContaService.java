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
}
