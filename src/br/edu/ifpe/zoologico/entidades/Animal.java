package br.edu.ifpe.zoologico.entidades;

import br.edu.ifpe.zoologico.util.AdapterDataNascimento;
import br.edu.ifpe.zoologico.util.DataNascimento;


public abstract class Animal extends EntidadeBase {
    private String nome;
    private String especie;
    private String dataNascimento;
    private static final AdapterDataNascimento adapterDataNascimento = new DataNascimento();
    private Comportamento comportamento;

    public Animal(String nome, String especie, String dataNascimento) {
        super(); // ID é gerado automaticamente pela classe base
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public String getDataNascimentoExtenso() {
        return adapterDataNascimento.formatarExtenso(dataNascimento);
    }

    public String getDataNascimentoSistemaPortugues() {
        return adapterDataNascimento.formatarSistemaPortugues(dataNascimento);
    }

    public abstract String limpar();
    public abstract String alimentarAnimal();

    public static class AnimalBuilder {
        private String nome;
        private String especie;
        private String dataNascimento;

        public AnimalBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public AnimalBuilder especie(String especie) {
            this.especie = especie;
            return this;
        }

        public AnimalBuilder dataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Animal criar() {
            return new Animal(nome, especie, dataNascimento) {
                @Override
                public String limpar() {
                    return "Limpando animal genérico";
                }

                @Override
                public String alimentarAnimal() {
                    return "Alimentando animal genérico";
                }
            };
        }
    }
}
