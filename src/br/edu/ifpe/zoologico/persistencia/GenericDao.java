package br.edu.ifpe.zoologico.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class GenericDao<T> implements IGenericDAO<T> {

	private List<T> lista;
	private static GenericDao<?> instancia;

	private GenericDao() {
		this.lista = new ArrayList<>();
	}

	public static <T> GenericDao<T> getInstancia() {
		if (instancia == null) {
			instancia = new GenericDao<>();
		}
		return (GenericDao<T>) instancia;
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
	public boolean remover(Integer id) throws ExcecaoNegocio {
		T objetoARemover = consultarPorId(id);
		if (objetoARemover != null) {
			return lista.remove(objetoARemover);
		} else {
			throw new ExcecaoNegocio("Objeto não encontrado na lista.");
		}
	}

	@Override
	public List<T> listarTodos() {
		return new ArrayList<>(this.lista);
	}

	@Override
	public T consultarPorId(Integer id) {
		for (T objeto : lista) {
			if (objeto instanceof Animal) {
				Animal animal = (Animal) objeto;
				if (animal.getId().equals(id)) {
					return objeto;
				}
			}
		}
		return null;
	}
}