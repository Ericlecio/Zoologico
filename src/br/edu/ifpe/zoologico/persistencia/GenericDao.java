package br.edu.ifpe.zoologico.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.zoologico.entidades.Animal;

public class GenericDao<T> implements IGenericDAO<T> {

	
	private List<Animal> lista;

	private static GenericDao instancia;

	private GenericDao() {
		this.lista = new ArrayList<>();
	}

	protected static GenericDao getInstancia() {
		if (instancia == null) {
			instancia = new GenericDao();
		}
		return instancia;
	}
	@Override
	public void inserir(T objeto) {
		this.lista.add(T);
		
	}

	@Override
	public void editar(T objeto) {
		// TODO Auto-generated method stub
		
	}

	public boolean remover(T objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T consultarUm() {
		// TODO Auto-generated method stub
		return null;
	}

}


