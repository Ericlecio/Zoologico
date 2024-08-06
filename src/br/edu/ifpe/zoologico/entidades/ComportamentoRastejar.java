package br.edu.ifpe.zoologico.entidades;

public class ComportamentoRastejar implements Comportamento {
	private Comportamento comportamento;

	public ComportamentoRastejar(Comportamento comportamento) {
		this.comportamento = comportamento;
	}

	@Override
	public String Acao() {
		String acao = "";
		if (comportamento != null) {
			acao = comportamento.Acao();
		}
		return acao + rastejar();
	}

	private String rastejar() {
		return " rasteja.";
	}
}