package br.edu.ifpe.zoologico.entidades;

public class ComportamentoNadar implements Comportamento {
	private Comportamento comportamento;

	public ComportamentoNadar(Comportamento comportamento) {
		this.comportamento = comportamento;
	}

	@Override
	public String Acao() {
		String acao = "";
		if (comportamento != null) {
			acao = comportamento.Acao();
		}
		return acao + nadar();
	}

	private String nadar() {
		return " nada.";
	}
}