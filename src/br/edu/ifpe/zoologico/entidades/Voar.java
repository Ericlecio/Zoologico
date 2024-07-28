package br.edu.ifpe.zoologico.entidades;

public class Voar implements Comportamento {
	private Comportamento comportamento;

    public Voar(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public void Acao() {
        comportamento.Acao();
        voar();
    }

    private void voar() {
        System.out.println("este animal voa.");
    }
}
