package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.negocio.FabricaControlador;
import br.edu.ifpe.zoologico.negocio.IControladorAnimal;

public class Fachada {
	private IControladorAnimal controladorAnimal;

	public Fachada() {
		this.controladorAnimal = FabricaControlador.getControladorAnimal();
	}

	public void cadastrarAnimal(Animal animal) throws ExcecaoNegocio {
		controladorAnimal.inserir(animal);
	}

	public void editar(Animal animal) throws ExcecaoNegocio {
		controladorAnimal.editar(animal);
	}

	public void remover(int id) throws ExcecaoNegocio {
		controladorAnimal.remover(id);
	}

	public Animal consultarAnimalPorId(int id) throws ExcecaoNegocio {
		return controladorAnimal.consultarPorId(id);
	}

	public List<Animal> consultarTodosAnimais() throws ExcecaoNegocio {
		return controladorAnimal.consultarTodos();
	}
}
