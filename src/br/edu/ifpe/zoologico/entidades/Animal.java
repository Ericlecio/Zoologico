package br.edu.ifpe.zoologico.entidades;

public class Animal {
	private int idAnimal;
	private String nome;
	private String especie;
	private String dataNascimento;

	public Animal() {
		// TODO Auto-generated constructor stub
	}

	// Construtor
	public Animal(int idAnimal, String nome, String especie, String dataNascimento) {
		this.idAnimal = idAnimal;
		this.nome = nome;
		this.especie = especie;
		this.dataNascimento = dataNascimento;
	}

	// Getters e Setters
	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}