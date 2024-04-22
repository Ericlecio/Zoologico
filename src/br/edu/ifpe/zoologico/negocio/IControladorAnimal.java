package br.edu.ifpe.zoologico.negocio;

import java.util.List;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public interface IControladorAnimal {
	void inserir(Animal animal) throws ExcecaoNegocio;
	void editar(Animal animal) throws ExcecaoNegocio;
	Animal consultar(int idAnimal) throws ExcecaoNegocio;
	void remover(int idAnimal) throws ExcecaoNegocio;
	List<Animal> consultarTodos() throws ExcecaoNegocio;
}