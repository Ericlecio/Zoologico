package br.edu.ifpe.zoologico.negocio;

import br.edu.ifpe.zoologico.persistencia.FabricaDAO;
import br.edu.ifpe.zoologico.persistencia.GenericDao;

import java.util.List;

import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public abstract class ControladorGenerico<T> {

	private GenericDao<T> dao;

	public ControladorGenerico() {
		this.dao = FabricaDAO.getDAO();
	}

	public T consultarPorId(Integer id) throws ExcecaoNegocio {
		T entidade = dao.consultarPorId(id);
		if (entidade == null) {
			throw new ExcecaoNegocio("Entidade não encontrada!");
		}
		return entidade;
	}

	public void remover(Integer id) throws ExcecaoNegocio {
		if (!dao.remover(id)) {
			throw new ExcecaoNegocio("Entidade não encontrada!");
		}
	}

	public List<T> consultarTodos() throws ExcecaoNegocio {
		return dao.listarTodos();
	}

	protected GenericDao<T> getDao() {
		return dao;
	}

	
}
