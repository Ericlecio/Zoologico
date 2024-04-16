package br.edu.ifpe.zoologico.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.zoologico.entidades.Animal;

public class AnimalDAOList implements IAnimalDAO {

	private List<Animal> lista;

	private static AnimalDAOList instancia;

	private AnimalDAOList() {
		this.lista = new ArrayList<>();
	}

	protected static AnimalDAOList getInstancia() {
		if (instancia == null) {
			instancia = new AnimalDAOList();
		}
		return instancia;
	}

	public void inserir(Animal animal) {
		this.lista.add(animal);
	}

	public List<Animal> getLista() {
		return lista;
	}

	public void setLista(List<Animal> lista) {
		this.lista = lista;
	}

	public static void setInstancia(AnimalDAOList instancia) {
		AnimalDAOList.instancia = instancia;
	}

	public AnimalDAOList(List<Animal> lista) {
		super();
		this.lista = lista;
	}
	
	// outros métodos de acesso aos dados do animal
}