package br.edu.ifpe.zoologico.persistencia;

import java.util.List;

import br.edu.ifpe.zoologico.entidades.Animal;

public interface IAnimalDAO {
    void inserir(Animal animal);

	void editar(Animal animal);

	Animal consultar(int idAnimal);

	void remover(int idAnimal);

	List<Animal> consultarTodos();
}
