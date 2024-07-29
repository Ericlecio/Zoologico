package br.edu.ifpe.zoologico.entidades;

public class ComportamentoNadar implements Comportamento {
	private Comportamento comportamento;

    public ComportamentoNadar(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public void Acao() {
        if (comportamento != null) {
            comportamento.Acao();
        }
        nadar();
    }

    private void nadar() {
        System.out.println("Este animal nada.");
    }
}
