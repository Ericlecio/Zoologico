package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public interface IControladorAnimal {
    void inserir(Animal animal) throws ExcecaoNegocio;
    void editar(Animal animal) throws ExcecaoNegocio;
    Animal consultarPorId(Integer id) throws ExcecaoNegocio;
    void remover(Integer id) throws ExcecaoNegocio;
    List<Animal> consultarTodos() throws ExcecaoNegocio;
}
