package br.edu.ifpe.zoologico.entidades;

import org.joda.time.LocalDate;

public class Animal extends EntidadeBase {
    private String nome;
    private String especie;
    private LocalDate dataNascimento; // Alterado para Joda-Time LocalDate
    private static final AdapterDataNascimento adapterDataNascimento = new DataNascimento();

    public Animal(String nome, String especie, LocalDate dataNascimento) {
        super();
        this.nome = nome;
        this.especie = especie;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimentoExtenso() {
        return adapterDataNascimento.formatarExtenso(dataNascimento);
    }

    public String getDataNascimentoSistemaPortugues() {
        return adapterDataNascimento.formatarSistemaPortugues(dataNascimento);
    }

    public static class AnimalBuilder {
        private Integer id;
        private String nome;
        private String especie;
        private LocalDate dataNascimento;

        public AnimalBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AnimalBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public AnimalBuilder especie(String especie) {
            this.especie = especie;
            return this;
        }

        public AnimalBuilder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Animal criar() {
            return new Animal(nome, especie, dataNascimento);
        }
    }
}
