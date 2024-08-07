package br.edu.ifpe.zoologico.persistencia;

public class FabricaDAO {

	public static <T> GenericDao<T> getDAO() {
		return GenericDao.getInstancia();
	}
}
