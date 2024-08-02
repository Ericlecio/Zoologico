package br.edu.ifpe.zoologico.entidades;

public class AnimalReptil extends Animal {

    public AnimalReptil(String nome, String especie, String dataNascimento) {
        super(nome, especie, dataNascimento);
    }

    @Override
    public void limpar() {
        System.out.println("Limpando réptil");
    }

    @Override
    public void alimentarAnimal() {
        System.out.println("Alimentando réptil");
    }
}
