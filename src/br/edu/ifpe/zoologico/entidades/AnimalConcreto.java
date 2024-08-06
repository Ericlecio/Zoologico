package br.edu.ifpe.zoologico.entidades;

public class AnimalConcreto implements Comportamento {
    private Animal animal;

    public AnimalConcreto(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String Acao() {
        return ""; 
    }
}