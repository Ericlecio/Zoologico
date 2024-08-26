package br.edu.ifpe.zoologico.negocio;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class ControladorZoologico extends ControladorGenerico<Zoologico> implements IControladorZoologico {
    private List<Zoologico> zoologicos = new ArrayList<>();

    @Override
    public void inserir(Zoologico zoologico) throws ExcecaoNegocio {
        zoologicos.add(zoologico);
    }

    @Override
    public Zoologico consultarPorId(Integer id) throws ExcecaoNegocio {
        for (Zoologico zoologico : zoologicos) {
            if (zoologico.getId().equals(id)) {
                return zoologico;
            }
        }
        throw new ExcecaoNegocio("Zoológico não encontrado.");
    }

    @Override
    public void remover(Integer id) throws ExcecaoNegocio {
        zoologicos.removeIf(zoologico -> zoologico.getId().equals(id));
    }

    @Override
    public List<Zoologico> consultarTodos() throws ExcecaoNegocio {
        return new ArrayList<>(zoologicos);
    }
}
