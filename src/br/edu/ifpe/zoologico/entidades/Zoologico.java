package br.edu.ifpe.zoologico.entidades;

public class Zoologico extends EntidadeBase{
	 private String nome;
	    private String endereco;

	    public Zoologico(String nome, String endereco) {
	        super();
	        this.nome = nome;
	        this.endereco = endereco;
	    }

	    public Zoologico() {
			// TODO Auto-generated constructor stub
		}

		public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getEndereco() {
	        return endereco;
	    }

	    public void setEndereco(String endereco) {
	        this.endereco = endereco;
	    }


	    public static class ZoologicoBuilder {
	        private Integer id;
	        private String nome;
	        private String endereco;

	        public ZoologicoBuilder id(Integer id) {
	            this.id = id;
	            return this;
	        }

	        public ZoologicoBuilder nome(String nome) {
	            this.nome = nome;
	            return this;
	        }

	        public ZoologicoBuilder endereco(String endereco) {
	            this.endereco = endereco;
	            return this;
	        }

	        public Zoologico criar() {
				return new Zoologico(nome, endereco);
	        }
	    }
}
