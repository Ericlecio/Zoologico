package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.persistencia.FabricaDAO;
import br.edu.ifpe.zoologico.persistencia.IAnimalDAO;

public class ControladorAnimal implements IControladorAnimal {

	@Override
	public void inserir(Animal animal) throws ExcecaoNegocio {
		if (!isValido(animal)) {
			throw new ExcecaoNegocio("Animal inválido!");
		}

		IAnimalDAO dao = FabricaDAO.getAnimalDAO();
		dao.inserir(animal);
	}

	@Override
	public void editar(Animal animal) throws ExcecaoNegocio {
		if (!isValido(animal)) {
			throw new ExcecaoNegocio("Animal inválido!");
		}

		IAnimalDAO dao = FabricaDAO.getAnimalDAO();
		dao.editar(animal);
	}

	@Override
	public Animal consultar(Integer id) throws ExcecaoNegocio {
		IAnimalDAO dao = FabricaDAO.getAnimalDAO();
		return dao.consultar(id);
	}

	@Override
	public void remover(Integer id) throws ExcecaoNegocio {
		IAnimalDAO dao = FabricaDAO.getAnimalDAO();
		dao.remover(id);
	}

	@Override
	public List<Animal> consultarTodos() throws ExcecaoNegocio {
		IAnimalDAO dao = FabricaDAO.getAnimalDAO();
		return dao.consultarTodos();
	}

	private boolean isValido(Animal animal) {
		return true;
	}
}
