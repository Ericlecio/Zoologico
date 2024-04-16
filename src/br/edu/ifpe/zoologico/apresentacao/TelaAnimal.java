package br.edu.ifpe.zoologico.apresentacao;

import java.util.Scanner;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.negocio.ExcecaoAnimalJaCadastrado;
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
		} while (opcao < 1 || opcao > 5);

		if (opcao == 1) {
			this.inserir();
		} else if (opcao == 2) {
			// implementar edição de animal
		} else if (opcao == 3) {
			// implementar remoção de animal
		} else if (opcao == 4) {
			// implementar consulta de animal
		} else {
			System.out.println("Saindo do sistema...");
		}
	}

	private void inserir() {
		Animal animal = new Animal();

		animal.setNome(this.lerString("nome"));
		animal.setEspecie(this.lerString("espécie"));
		animal.setDataNascimento(this.lerString("data de nascimento"));

		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();
		try {
			controlador.inserir(animal);
		}catch (ExcecaoAnimalJaCadastrado excecao) {
			System.out.println("Animal já cadastrado: " + excecao.getMessage());
		}
	}

	private String lerString(String nomeAtributo) {
		String entrada = "";

		while (entrada.trim().isEmpty()) {
			System.out.println("Digite o " + nomeAtributo + " do animal: ");
			entrada = scanner.nextLine();
		}

		return entrada;
	}
}