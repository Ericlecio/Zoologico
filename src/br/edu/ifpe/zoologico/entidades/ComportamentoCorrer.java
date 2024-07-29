package br.edu.ifpe.zoologico.entidades;

public class ComportamentoCorrer implements Comportamento {
	private Comportamento comportamento;

	public ComportamentoCorrer(Comportamento comportamento) {
		this.comportamento = comportamento;
	}

	@Override
	public void Acao() {
		if (comportamento != null) {
			comportamento.Acao();
		}
		Correr();
	}

	private void Correr() {
		System.out.println("Este animal corre.");
	}
}
