package br.edu.ifpe.zoologico.entidades;

public class AnimalMamifero extends Animal {

	public AnimalMamifero(String nome, String especie, String dataNascimento) {
		super(nome, especie, dataNascimento);
	}

	@Override
	public String limpar() {
		return "Limpando mamífero com água e sabão";
	}

	@Override
	public String alimentarAnimal() {
		return "Alimentando mamífero com ração específica para mamíferos";
	}
}
