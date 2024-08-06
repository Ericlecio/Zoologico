package br.edu.ifpe.zoologico.entidades;

public class AnimalReptil extends Animal {

	public AnimalReptil(String nome, String especie, String dataNascimento) {
		super(nome, especie, dataNascimento);
	}

	@Override
	public String limpar() {
		return "Limpando réptil com água morna e desinfetante suave";
	}

	@Override
	public String alimentarAnimal() {
		return "Alimentando réptil com insetos e vegetais";
	}
}
