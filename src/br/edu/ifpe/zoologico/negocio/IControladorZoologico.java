package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public interface IControladorZoologico {
    void inserir(Zoologico zoologico) throws ExcecaoNegocio;
    Zoologico consultarPorId(Integer id) throws ExcecaoNegocio;
    void remover(Integer id) throws ExcecaoNegocio;
    List<Zoologico> consultarTodos() throws ExcecaoNegocio;
}
