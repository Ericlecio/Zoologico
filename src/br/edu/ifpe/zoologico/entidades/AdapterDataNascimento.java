package br.edu.ifpe.zoologico.entidades;

import java.time.LocalDate;


public interface AdapterDataNascimento {
    String formatarExtenso(LocalDate dataNascimento);
    String formatarSistemaPortugues(LocalDate dataNascimento);
}
