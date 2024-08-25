package br.edu.ifpe.zoologico.entidades;

import java.util.ArrayList;
import java.util.List;

public class Zoologico extends EntidadeBase {
    private String nome;
    private String endereco;
    private List<Animal> animais;

    public Zoologico(String nome, String endereco) {
        super(); // 
        this.nome = nome;
        this.endereco = endereco;
        this.animais = new ArrayList<>();
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void adicionarAnimal(Animal animal) {
        this.animais.add(animal);
    }

    public void removerAnimal(Animal animal) {
        this.animais.remove(animal);
    }

    public Animal encontrarAnimalPorId(Integer id) {
        for (Animal animal : animais) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
