package br.edu.ifpe.zoologico.persistencia;

import java.util.List;

import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public interface IGenericDAO<T>  {

	public void inserir(T objeto);

	public void editar(T objeto) throws ExcecaoNegocio;

	public boolean remover(T objeto);

	public T consultar(Integer id); 

	public List<T> listar();
}
