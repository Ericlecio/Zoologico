package br.edu.ifpe.zoologico.entidades;

public class Correr implements Comportamento {
	private Comportamento comportamento;

    public Correr(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public void Acao() {
        comportamento.Acao();
        Correr();
    }

    private void Correr() {
        System.out.println("este animal voa.");
    }
}
