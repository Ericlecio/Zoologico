package br.edu.ifpe.zoologico.negocio;

import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class ControladorZoologico extends ControladorGenerico<Zoologico> implements IControladorZoologico {

    public ControladorZoologico() {
        super();
    }

    @Override
    public void inserir(Zoologico zoologico) throws ExcecaoNegocio {
        if (!isValido(zoologico)) {
            throw new ExcecaoNegocio("Zoológico inválido!");
        }
        getDao().inserir(zoologico);
    }

    private boolean isValido(Zoologico zoologico) {
        return true;
    }
}