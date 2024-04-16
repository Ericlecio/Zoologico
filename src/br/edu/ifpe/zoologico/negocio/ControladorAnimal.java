package br.edu.ifpe.zoologico.negocio;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.persistencia.FabricaDAO;
import br.edu.ifpe.zoologico.persistencia.IAnimalDAO;

public class ControladorAnimal implements IControladorAnimal {

	public void inserir(Animal animal) throws ExcecaoAnimalJaCadastrado {
		if (!this.isValido(animal)) {
			throw new ExcecaoAnimalJaCadastrado("Animal inválido!");
		}

		IAnimalDAO dao = FabricaDAO.getAnimalDAO();
		dao.inserir(animal);
	}

	private boolean isValido(Animal animal) {
		return true;
	}
}