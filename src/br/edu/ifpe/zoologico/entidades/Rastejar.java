package br.edu.ifpe.zoologico.entidades;

public class Rastejar implements Comportamento  {
	private Comportamento comportamento;

    public Rastejar(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public void Acao() {
        comportamento.Acao();
        rastejar();
    }

    private void rastejar() {
        System.out.println("este animal voa.");
    }
}
