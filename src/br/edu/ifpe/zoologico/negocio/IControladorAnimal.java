package br.edu.ifpe.zoologico.negocio;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public interface IControladorAnimal {
	void inserir(Animal animal) throws ExcecaoNegocio;
}