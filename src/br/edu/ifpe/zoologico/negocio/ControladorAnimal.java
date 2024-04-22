package br.edu.ifpe.zoologico.negocio;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.persistencia.FabricaDAO;
import br.edu.ifpe.zoologico.persistencia.IAnimalDAO;

public class ControladorAnimal implements IControladorAnimal {

	public void inserir(Animal animal) throws ExcecaoNegocio {
		if (!this.isValido(animal)) {
			throw new ExcecaoNegocio("Animal inválido!");
		}

		IAnimalDAO dao = FabricaDAO.getAnimalDAO();
		dao.inserir(animal);
	}

	private boolean isValido(Animal animal) {
		return true;
	}
}