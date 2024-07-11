package br.edu.ifpe.zoologico.persistencia;

import java.util.List;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public interface IAnimalDAO {
    void inserir(Animal animal);

	void editar(Animal animal) throws ExcecaoNegocio;

	Animal consultar(Integer id);

	void remover(Integer id);

	List<Animal> consultarTodos();
}
