package br.edu.ifpe.zoologico.entidades;

public class Nadar implements Comportamento {
	private Comportamento comportamento;

    public Nadar(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public void Acao() {
        comportamento.Acao();
        nadar();
    }

    private void nadar() {
        System.out.println("este animal nada.");
    }
}
