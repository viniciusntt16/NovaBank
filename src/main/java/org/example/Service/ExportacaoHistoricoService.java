package org.example.Service;

import org.example.Entidades.Conta;
import org.example.Entidades.Movimentacao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ExportacaoHistoricoService {

    public Path exportarHistorico(List<Conta> contas) throws IOException {
        Path pastaExportacoes = Path.of("exportacoes");

        Files.createDirectories(pastaExportacoes);

        Path arquivo = pastaExportacoes.resolve("historico-novabank.txt");

        StringBuilder conteudo = new StringBuilder();

        conteudo.append("===== NOVABANK - HISTÓRICO DE MOVIMENTAÇÕES =====\n\n");

        if (contas.isEmpty()) {
            conteudo.append("Não há contas cadastradas.\n");
        }

        for (Conta conta : contas) {
            conteudo.append("Número da conta: ")
                    .append(conta.getNumero())
                    .append("\n");

            conteudo.append("Titular: ")
                    .append(conta.getTitular())
                    .append("\n");

            conteudo.append("Saldo final: ")
                    .append(conta.getSaldoFormatado())
                    .append("\n");

            conteudo.append("Status: ")
                    .append(conta.getStatus())
                    .append("\n");

            conteudo.append("Movimentações:\n");

            List<Movimentacao> historico = conta.getHistorico();

            if (historico.isEmpty()) {
                conteudo.append("Nenhuma movimentação registrada.\n");
            } else {
                for (Movimentacao movimentacao : historico) {
                    conteudo.append("- ")
                            .append(movimentacao)
                            .append("\n");
                }
            }

            conteudo.append("\n----------------------------------------\n\n");
        }

        Files.writeString(
                arquivo,
                conteudo.toString(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

        return arquivo.toAbsolutePath();
    }
}
