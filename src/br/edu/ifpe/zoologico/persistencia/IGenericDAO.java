package br.edu.ifpe.zoologico.persistencia;

import java.util.List;

public interface IGenericDAO<T> {

	public void inserir(T objeto) {
	}
	public void editar(T objeto) {
	}
	public boolean remover(int idAnimal);
	
	public List<T> listar();
}
