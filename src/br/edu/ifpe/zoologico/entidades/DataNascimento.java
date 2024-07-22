package br.edu.ifpe.zoologico.entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;


public class DataNascimento implements AdapterDataNascimento {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_PT_BR = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));

 
    @Override
    public String formatarExtenso(LocalDate dataNascimento) {
        return dataNascimento.format(FORMATTER_PT_BR);
    }

    @Override
    public String formatarSistemaPortugues(LocalDate dataNascimento) {
        int dia = dataNascimento.getDayOfMonth();
        String mes = dataNascimento.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        int ano = dataNascimento.getYear();
        return String.format("%02d-%s-%04d", dia, mes, ano);
    }
}
