package br.edu.ifpe.zoologico.entidades;

public class Animal extends EntidadeBase {
	private String nome;
	private String especie;
	private String dataNascimento;

	public Animal(String nome, String especie, String dataNascimento) {
		super(); 
		this.nome = nome;
		this.especie = especie;
		this.dataNascimento = dataNascimento;
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
		private Integer id; 
		private String nome;
		private String especie;
		private String dataNascimento;

		public AnimalBuilder id(Integer id) {
			this.id = id;
			return this;
		}

		public AnimalBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public AnimalBuilder especie(String especie) {
			this.especie = especie;
			return this;
		}

		public AnimalBuilder dataNascimento(String dataNascimento) {
			this.dataNascimento = dataNascimento;
			return this;
		}

		public Animal criar() {
			return new Animal(nome, especie, dataNascimento);
		}
	}



}
