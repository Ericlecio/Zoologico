package br.edu.ifpe.zoologico.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogZoologico {
    private static final String CAMINHO_ARQUIVO_LOG = "src/br/edu/ifpe/zoologico/log/log.txt";

    public static void registrarMovimentacao(String mensagem) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO_LOG, true))) {
            String dataHoraAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println(dataHoraAtual + " - " + mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
