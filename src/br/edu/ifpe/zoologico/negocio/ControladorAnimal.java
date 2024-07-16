package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.persistencia.FabricaDAO;
import br.edu.ifpe.zoologico.persistencia.GenericDao;

public class ControladorAnimal implements IControladorAnimal {

    @Override
    public void inserir(Animal animal) throws ExcecaoNegocio {
        if (!isValido(animal)) {
            throw new ExcecaoNegocio("Animal inválido!");
        }

        GenericDao<Animal> dao = FabricaDAO.getDAO();
        dao.inserir(animal);
    }

    @Override
    public void editar(Animal animal) throws ExcecaoNegocio {
        if (!isValido(animal)) {
            throw new ExcecaoNegocio("Animal inválido!");
        }

        GenericDao<Animal> dao = FabricaDAO.getDAO();
        dao.editar(animal);
    }

    @Override
    public Animal consultarPorId(Integer id) throws ExcecaoNegocio {
        GenericDao<Animal> dao = FabricaDAO.getDAO();
        Animal animal = dao.consultarPorId(id);
        if (animal == null) {
            throw new ExcecaoNegocio("Animal não encontrado!");
        }
        return animal;
    }

    @Override
    public void remover(Integer id) throws ExcecaoNegocio {
        GenericDao<Animal> dao = FabricaDAO.getDAO();
        if (!dao.remover(id)) {
            throw new ExcecaoNegocio("Animal não encontrado!");
        }
    }

    @Override
    public List<Animal> consultarTodos() throws ExcecaoNegocio {
        GenericDao<Animal> dao = FabricaDAO.getDAO();
        return dao.listarTodos();
    }

    private boolean isValido(Animal animal) {
        // Implementar validação adequada
        return true;
    }
}
