package br.edu.ifpe.zoologico.persistencia;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class AnimalDAOList implements IAnimalDAO {

	private List<Animal> lista;
	private static AnimalDAOList instancia;

	private AnimalDAOList() {
		this.lista = new ArrayList<>();
	}

	public static synchronized AnimalDAOList getInstancia() {
		if (instancia == null) {
			instancia = new AnimalDAOList();
		}
		return instancia;
	}

	@Override
	public void inserir(Animal animal) {
		this.lista.add(animal);
	}

	@Override
	public void editar(Animal animal) throws ExcecaoNegocio {
		boolean encontrado = false;

		for (int id = 0; id < lista.size(); id++) {
			if (lista.get(id).getId() == animal.getId()) {
				lista.set(id, animal);
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			throw new ExcecaoNegocio("Animal não encontrado com o ID: " + animal.getId());
		}
	}

	@Override
	public void remover(Integer id) {
		lista.removeIf(animal -> animal.getId() == id);
	}

	@Override
	public List<Animal> consultarTodos() {
		return new ArrayList<>(lista);
	}

	public Animal consultar(Integer id) {
		for (Animal animal : lista) {
			if (animal.getId() == id) {
				return animal;
			}
		}
		return null;
	}
}