package br.edu.ifpe.zoologico.entidades;

public class AnimalAve extends Animal {

	public AnimalAve(String nome, String especie, String dataNascimento) {
		super(nome, especie, dataNascimento);
	}

	@Override
	public String limpar() {
		return "Limpando ave com spray de água";
	}

	@Override
	public String alimentarAnimal() {
		return "Alimentando ave com sementes e frutas";
	}
}
