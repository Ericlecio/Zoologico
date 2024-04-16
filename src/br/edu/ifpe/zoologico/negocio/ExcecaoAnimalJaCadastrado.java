package br.edu.ifpe.zoologico.negocio;

public class ExcecaoAnimalJaCadastrado extends Exception {
    public ExcecaoAnimalJaCadastrado(String mensagem) {
        super(mensagem);
    }
}