package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class FachadaZoologico {
	private IControladorZoologico controladorZoologico;

    public FachadaZoologico() {
        controladorZoologico = FabricaControlador.getControladorZoologico();
    }

    public void adicionarZoologico(Zoologico zoologico) throws ExcecaoNegocio {
        controladorZoologico.inserir(zoologico);
    }

    public void editarZoologico(Zoologico zoologico) throws ExcecaoNegocio {
        controladorZoologico.editar(zoologico);
    }

    public void remover(int id) throws ExcecaoNegocio {
        controladorZoologico.remover(id);
    }

    public Zoologico consultarPorId(int id) throws ExcecaoNegocio {
        return controladorZoologico.consultarPorId(id);
    }

    public List<Zoologico> listarTodosZoologicos() throws ExcecaoNegocio {
        return controladorZoologico.consultarTodos();
    }
}
