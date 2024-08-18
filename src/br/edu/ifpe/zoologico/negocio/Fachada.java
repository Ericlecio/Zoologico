package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class Fachada  {
	private IControladorAnimal controladorAnimal;

	public Fachada() {
		controladorAnimal = FabricaControlador.getControladorAnimal();
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

	public Animal consultarPorId(int id) throws ExcecaoNegocio {
		return controladorAnimal.consultarPorId(id);
	}

	public List<Animal> consultarTodos() throws ExcecaoNegocio {
		return controladorAnimal.consultarTodos();
	}
}

