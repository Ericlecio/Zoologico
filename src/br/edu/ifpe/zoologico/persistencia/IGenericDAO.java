package br.edu.ifpe.zoologico.persistencia;

import java.util.List;

import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public interface IGenericDAO<T> {
	void inserir(T objeto);
	void editar(T objeto) throws ExcecaoNegocio;
	boolean remover(Integer id) throws ExcecaoNegocio;
	T consultarPorId(Integer id);
	List<T> listarTodos();
}