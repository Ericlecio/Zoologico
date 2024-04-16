package br.edu.ifpe.zoologico.negocio;

import br.edu.ifpe.zoologico.entidades.Animal;

public interface IControladorAnimal {
	void inserir(Animal animal) throws ExcecaoAnimalJaCadastrado;
}