package br.edu.ifpe.zoologico.entidades;

public class ComportamentoCorrer implements Comportamento {
	private Comportamento comportamento;

	public ComportamentoCorrer(Comportamento comportamento) {
		this.comportamento = comportamento;
	}

	@Override
	public String Acao() {
		String acao = "";
		if (comportamento != null) {
			acao = comportamento.Acao();
		}
		return acao + Correr();
	}

	private String Correr() {
		return " corre.";
	}
}