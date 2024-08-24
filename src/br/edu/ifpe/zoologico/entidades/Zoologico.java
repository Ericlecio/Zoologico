package br.edu.ifpe.zoologico.entidades;

import java.util.ArrayList;
import java.util.List;

public class Zoologico extends EntidadeBase {
    private String nome;
    private String endereco;
    private List<Animal> animais; // Lista de animais

    // Construtor para inicializar nome, endereco e lista de animais
    public Zoologico(String nome, String endereco) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.animais = new ArrayList<>(); // Inicializa a lista de animais
    }

    // Construtor padrão
    public Zoologico() {
        this.animais = new ArrayList<>(); // Inicializa a lista de animais
    }

    // Getter para a lista de animais
    public List<Animal> getAnimais() {
        return animais;
    }

    // Método para adicionar um animal ao zoológico
    public void adicionarAnimal(Animal animal) {
        this.animais.add(animal);
    }

    // Método para remover um animal do zoológico
    public void removerAnimal(Animal animal) {
        this.animais.remove(animal);
    }

    // Método para encontrar um animal pelo nome
    public Animal encontrarAnimalPorNome(String nome) {
        for (Animal animal : animais) {
            if (animal.getNome().equalsIgnoreCase(nome)) {
                return animal;
            }
        }
        return null; // Retorna null se não encontrar
    }

    // Getters e Setters para outros atributos
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

    // Classe interna para Builder
    public static class ZoologicoBuilder {
        private Integer id;
        private String nome;
        private String endereco;

        public ZoologicoBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ZoologicoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ZoologicoBuilder endereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public Zoologico criar() {
            return new Zoologico(nome, endereco);
        }
    }
}
