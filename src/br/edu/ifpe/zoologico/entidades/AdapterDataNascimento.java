package br.edu.ifpe.zoologico.entidades;

import org.joda.time.LocalDate;

public interface AdapterDataNascimento {
    String formatarExtenso(LocalDate dataNascimento);
    String formatarSistemaPortugues(LocalDate dataNascimento);
}
