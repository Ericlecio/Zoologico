package br.edu.ifpe.zoologico.persistencia;

public class FabricaDAO {
	public static IAnimalDAO getAnimalDAO() {
		return AnimalDAOList.getInstancia();
	}
}