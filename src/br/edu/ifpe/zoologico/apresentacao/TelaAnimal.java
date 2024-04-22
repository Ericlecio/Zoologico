package br.edu.ifpe.zoologico.apresentacao;

import java.util.Scanner;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.negocio.FabricaControlador;
import br.edu.ifpe.zoologico.negocio.IControladorAnimal;

public class TelaAnimal {

	Scanner scanner = new Scanner(System.in);

	public void exibir() {
		int opcao = 0;
		do {
			System.out.println("Bem vindo(a) ao Zoologico!");  
			System.out.println("Digite 1 para cadastrar um animal;");
			System.out.println("Digite 2 para editar os dados de um animal;");
			System.out.println("Digite 3 para remover um animal;");
			System.out.println("Digite 4 para consultar um animal; ou");
			System.out.println("Digite 5 para sair");

			try {
				opcao = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Digite um número válido!");
			}

			switch (opcao) {
			case 1:
				this.inserir();
				break;
			case 2:
				// this.editar();
				break;
			case 3:
				//  this.remover();
				break;
			case 4:
				//  this.consultar();
				break;
			case 5:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida! Digite novamente.");
				break;
			}
		} while (opcao != 5);
	}

	private void inserir() {
		Animal animal = new Animal();

		animal.setNome(this.lerString("nome"));
		animal.setEspecie(this.lerString("espécie"));
		animal.setDataNascimento(this.lerString("data de nascimento"));

		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();
		try {
			controlador.inserir(animal);
		} catch (ExcecaoNegocio excecao) {
			System.out.println("Animal já cadastrado: " + excecao.getMessage());
		}
	}


	private int lerInteiro(String mensagem) {
		int entrada = 0;
		boolean valido = false;

		while (!valido) {
			System.out.println("Digite " + mensagem + ": ");
			try {
				entrada = Integer.parseInt(scanner.nextLine());
				valido = true;
			} catch (NumberFormatException ex) {
				System.out.println("Digite um número válido!");
			}
		} 
		return entrada;
	}

	private String lerString(String nomeAtributo) {
		String entrada = "";

		while (entrada.trim().isEmpty()) {
			System.out.println("Digite o " + nomeAtributo + ": ");
			entrada = scanner.nextLine();
		}

		return entrada;
	}
}