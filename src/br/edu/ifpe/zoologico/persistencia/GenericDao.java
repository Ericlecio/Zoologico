package br.edu.ifpe.zoologico.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class GenericDao <T> implements IGenericDAO <T> {

	private List<T> lista;
	private static GenericDao<?> instancia;

	private GenericDao() {
		this.lista = new ArrayList<>();
	}

	public static <T> GenericDao<T> getInstancia() {
		if (instancia == null) {
			instancia = new GenericDao<>();
		}
		return (GenericDao <T>) instancia;
	}

	@Override
	public void inserir(T objeto) {
		this.lista.add(objeto);
	}

	@Override
	public void editar(T objeto) throws ExcecaoNegocio {
		int index = lista.indexOf(objeto);
		if (index != -1) {
			lista.set(index, objeto);
		} else {
			throw new ExcecaoNegocio("Objeto não encontrado na lista.");
		}
	}

	@Override
	public boolean remover(T objeto) {
		return this.lista.remove(objeto);
	}
	

	@Override
	public List<T> listar() {
		return new ArrayList<>(this.lista);
	}

	@Override
	public T consultar(Integer id) {
		for (T objeto : lista) {
			if (objeto.hashCode() == id) {
				return objeto;
			}
		}
		return null;
	}

	

}