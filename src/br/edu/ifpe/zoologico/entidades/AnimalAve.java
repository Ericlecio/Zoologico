package br.edu.ifpe.zoologico.entidades;

public class AnimalAve extends Animal {

    public AnimalAve(String nome, String especie, String dataNascimento) {
        super(nome, especie, dataNascimento);
    }

    @Override
    public void limpar() {
        System.out.println("Limpando ave");
    }

    @Override
    public void alimentarAnimal() {
        System.out.println("Alimentando ave");
    }
}
