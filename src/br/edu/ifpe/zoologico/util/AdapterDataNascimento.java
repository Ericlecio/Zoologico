package br.edu.ifpe.zoologico.util;

public interface AdapterDataNascimento {
	String formatarExtenso(String dataNascimento);
	String formatarSistemaPortugues(String dataNascimento);
	String parse(String dataNascimento);
}
