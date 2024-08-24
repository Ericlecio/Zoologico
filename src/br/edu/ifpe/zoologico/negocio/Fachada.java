package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class Fachada  {
	private IControladorAnimal controladorAnimal;
	private IControladorZoologico controladorZoologico;


	public Fachada() {
		controladorAnimal = FabricaControlador.getControladorAnimal();
		controladorZoologico = FabricaControlador.getControladorZoologico();
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
	
	
	public void adicionarZoologico(Zoologico zoologico) throws ExcecaoNegocio {
        controladorZoologico.inserir(zoologico);
    }
	
	public void removerZoologico(int id) throws ExcecaoNegocio {
        controladorZoologico.remover(id);
    }

    public Zoologico consultarPorIdZoologico(int id) throws ExcecaoNegocio {
        return controladorZoologico.consultarPorId(id);
    }

    public List<Zoologico> listarTodosZoologicos() throws ExcecaoNegocio {
        return controladorZoologico.consultarTodos();
    }
	
	
	
}
