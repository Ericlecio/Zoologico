package br.edu.ifpe.zoologico.negocio;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class ControladorAnimal extends ControladorGenerico<Animal> implements IControladorAnimal {

	public ControladorAnimal() {
		super();
	}

	@Override
	public void inserir(Animal animal) throws ExcecaoNegocio {
		if (!isValido(animal)) {
			throw new ExcecaoNegocio("Animal inválido!");
		}
		getDao().inserir(animal);
	}

	@Override
	public void editar(Animal animal) throws ExcecaoNegocio {
		if (!isValido(animal)) {
			throw new ExcecaoNegocio("Animal inválido!");
		}
		getDao().editar(animal);
	}

	private boolean isValido(Animal animal) {
		return true;
	}
}