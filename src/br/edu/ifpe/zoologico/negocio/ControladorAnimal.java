package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.persistencia.FabricaDAO;
import br.edu.ifpe.zoologico.persistencia.GenericDao;

public class ControladorAnimal implements IControladorAnimal {

	@Override
	public void inserir(Animal animal) throws ExcecaoNegocio {
		if (!isValido(animal)) {
			throw new ExcecaoNegocio("Animal inválido!");
		}

		GenericDao<Object> dao = FabricaDAO.getDAO();
		dao.inserir(animal);
	}

	@Override
	public void editar(Animal animal) throws ExcecaoNegocio {
		if (!isValido(animal)) {
			throw new ExcecaoNegocio("Animal inválido!");
		}

		GenericDao<Object> dao = FabricaDAO.getDAO();
		dao.editar(animal);
	}

	@Override
	public Animal consultar(Integer id) throws ExcecaoNegocio {
		GenericDao<Animal> dao = FabricaDAO.getDAO();
		return (Animal) dao.consultar(id);
	}

	@Override
	public void remover(Integer id) throws ExcecaoNegocio {
		GenericDao<Animal> dao = FabricaDAO.getDAO();
		return dao.remover(id);
	}

	@Override
	public List<Animal> consultarTodos() throws ExcecaoNegocio {
		GenericDao<Animal> dao = FabricaDAO.getDAO();
		return dao.listar();
	}

	private boolean isValido(Animal animal) {
		return true;
	}
}
