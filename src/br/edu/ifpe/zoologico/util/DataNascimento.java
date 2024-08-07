package br.edu.ifpe.zoologico.util;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DataNascimento implements AdapterDataNascimento {
	private static final DateTimeFormatter FORMATTER_PT_BR = DateTimeFormat.forPattern("dd 'de' MMMM 'de' yyyy");
	private static final DateTimeFormatter FORMATTER_SISTEMA_PORTUGUES = DateTimeFormat.forPattern("dd-MMMM-yyyy");

	@Override
	public String formatarExtenso(String dataNascimento) {
		LocalDate localDate = LocalDate.parse(dataNascimento);
		return localDate.toString(FORMATTER_PT_BR);
	}

	@Override
	public String formatarSistemaPortugues(String dataNascimento) {
		LocalDate localDate = LocalDate.parse(dataNascimento);
		return localDate.toString(FORMATTER_SISTEMA_PORTUGUES);
	}

	@Override
	public String parse(String dataNascimento) {
		LocalDate localDate = LocalDate.parse(dataNascimento);
		return localDate.toString();
	}
}
