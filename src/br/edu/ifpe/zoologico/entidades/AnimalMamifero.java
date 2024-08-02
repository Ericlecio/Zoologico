package br.edu.ifpe.zoologico.entidades;

public class AnimalMamifero extends Animal {

    public AnimalMamifero(String nome, String especie, String dataNascimento) {
        super(nome, especie, dataNascimento);
    }

    @Override
    public void limpar() {
        System.out.println("Limpando mamífero");
    }

    @Override
    public void alimentarAnimal() {
        System.out.println("Alimentando mamífero");
    }
}
