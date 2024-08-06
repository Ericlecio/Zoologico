package br.edu.ifpe.zoologico.entidades;

public class AnimalComportamento implements Comportamento {
    private Animal animal;

    public AnimalComportamento(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String Acao() {
        return ""; 
    }
}