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

	@Override
	public void editar(Animal animal) {
	    for (Animal a : lista) {
	        if (a.getIdAnimal() == animal.getIdAnimal()) {
	            a.setNome(animal.getNome());
	            a.setEspecie(animal.getEspecie());
	            a.setDataNascimento(animal.getDataNascimento());
	            return;
	        }
	    }
	    throw new IllegalArgumentException("Animal não encontrado com o ID: " + animal.getIdAnimal());
	}
	
	public Animal consultar(int idAnimal) {
	    for (Animal animal : lista) {
	        if (animal.getIdAnimal() == idAnimal) {
	            return animal;
	        }
	    }
	    return null;
	}
	
	public void remover(int idAnimal) {
	    lista.removeIf(animal -> animal.getIdAnimal() == idAnimal);
	}

	
	@Override
	public List<Animal> consultarTodos() {
	    return new ArrayList<>(lista);
	}
	
	// outros métodos de acesso aos dados do animal
}