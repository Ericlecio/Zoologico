package br.edu.ifpe.zoologico.entidades;

public class ComportamentoRastejar implements Comportamento {
	private Comportamento comportamento;

    public ComportamentoRastejar(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public void Acao() {
        if (comportamento != null) {
            comportamento.Acao();
        }
        rastejar();
    }

    private void rastejar() {
        System.out.println("Este animal rasteja.");
    }
}
