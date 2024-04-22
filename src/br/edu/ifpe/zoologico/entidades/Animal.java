package br.edu.ifpe.zoologico.entidades;

public class Animal {
	private int idAnimal;
	private String nome;
	private String especie;
	private String dataNascimento;

	public Animal() {}

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
	
	public static class AnimalBuilder {
		private Integer idAnimal;
		private String nome;
		private String especie;
		private String dataNascimento;
		
		public AnimalBuilder idAnimal (Integer idAnimal) {
			this.idAnimal = idAnimal;
			return this;
		}
		
		public AnimalBuilder nome (String nome) {
			this.nome = nome;
			return this;
		}
		
		public AnimalBuilder especie (String especie) {
			this.especie = especie;
			return this;
		}
		
		public AnimalBuilder dataNascimento (String dataNascimento) {
			this.dataNascimento = dataNascimento;
			return this;
		}
		
		public Animal criar() {
			return new Animal(this.idAnimal, this.nome, this.especie, this.dataNascimento);
		}
		
	}
}