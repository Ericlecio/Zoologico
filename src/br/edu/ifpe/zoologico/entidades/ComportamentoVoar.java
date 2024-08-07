package br.edu.ifpe.zoologico.entidades;

public class ComportamentoVoar implements Comportamento {
	private Comportamento comportamento;

	public ComportamentoVoar(Comportamento comportamento) {
		this.comportamento = comportamento;
	}

	@Override
	public String Acao() {
		String acao = "";
		if (comportamento != null) {
			acao = comportamento.Acao();
		}
		return acao + voar();
	}

	private String voar() {
		return " voa.";
	}
}