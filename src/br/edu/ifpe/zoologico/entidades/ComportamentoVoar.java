package br.edu.ifpe.zoologico.entidades;

public class ComportamentoVoar implements Comportamento {
	private Comportamento comportamento;

    public ComportamentoVoar(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public void Acao() {
        if (comportamento != null) {
            comportamento.Acao();
        }
        voar();
    }

    private void voar() {
        System.out.println("Este animal voa.");
    }
}
