package br.edu.ifpe.zoologico.apresentacao;

import java.util.List;
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
			System.out.println("CRUD Zoologico!");  
			System.out.println("Digite 1 para cadastrar um animal;");
			System.out.println("Digite 2 para editar os dados de um animal;");
			System.out.println("Digite 3 para remover um animal;");
			System.out.println("Digite 4 para consultar um animal;");
			System.out.println("Digite 5 para consultar todos os animais, ou ");
			System.out.println("Digite 6 para sair");

			System.out.println("-------------------------------------------");


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
				this.editar();
				break;
			case 3:
				this.remover();
				break;
			case 4:
				this.consultar();
				break;
			case 5:
				this.consultarTodos();
				break;
			case 6:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida! Digite os numeros entre 1 e 6.");
				break;
			}
		} while (opcao != 6);
	}


	private void inserir() {
		int idAnimal = lerInteiro("idAnimal");

		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		try {
			Animal animalExistente = controlador.consultar(idAnimal);
			if (animalExistente != null) {
				System.out.println("Já existe um animal cadastrado com este ID!");
				return;
			}

			Animal animal = new Animal();
			animal.setIdAnimal(idAnimal);
			animal.setNome(this.lerString("nome"));
			animal.setEspecie(this.lerString("espécie"));
			animal.setDataNascimento(this.lerString("data de nascimento"));
			System.out.println("---------------------------------------------");

			controlador.inserir(animal);
			System.out.println("Animal cadastrado com sucesso!");
		} catch (ExcecaoNegocio excecao) {
			System.out.println("Erro ao cadastrar animal: " + excecao.getMessage());
		}
	}

	private void editar() {
		int idAnimal = lerInteiro("idAnimal");

		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();
		String novoNome = lerString("novo nome");
		String novaEspecie = lerString("nova especie");
		String novadata = lerString("novo data");

		Animal Animal = new Animal(idAnimal, novaEspecie, novadata, novoNome);
		try {
			controlador.editar(Animal);
			System.out.println("Animal editado com sucesso!");
		} catch (ExcecaoNegocio e) {
			System.out.println( e.getMessage());
		}
	}

	private void consultar() {
		int idAnimal = lerInteiro("idAnimal");

		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		try {
			Animal animal = controlador.consultar(idAnimal);
			if (animal != null) {
				System.out.println("Animal encontrado:");
				System.out.println("ID: " + animal.getIdAnimal());
				System.out.println("Nome: " + animal.getNome());
				System.out.println("Espécie: " + animal.getEspecie());
				System.out.println("Data de Nascimento: " + animal.getDataNascimento());
				System.out.println("---------------------------------------------");
			} else {
				System.out.println("Animal não encontrado.");
			}
		} catch (ExcecaoNegocio e) {
			System.out.println(e.getMessage());
		}
	}

	private void remover() {
		int idAnimal = lerInteiro("idAnimal");

		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		try {
			controlador.remover(idAnimal);
			System.out.println("Animal removido!");
		} catch (ExcecaoNegocio e) {
			System.out.println(e.getMessage());
		}
	}

	private void consultarTodos() {
		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		try {
			List<Animal> animais = controlador.consultarTodos();
			if (!animais.isEmpty()) {
				System.out.println("Lista de animais:");
				for (Animal animal : animais) {
					System.out.println("---------------------------------------------");
					System.out.println("ID: " + animal.getIdAnimal());
					System.out.println("Nome: " + animal.getNome());
					System.out.println("Espécie: " + animal.getEspecie());
					System.out.println("Data de Nascimento: " + animal.getDataNascimento());
				}
				System.out.println("---------------------------------------------");
			} else {
				System.out.println("Não há animais cadastrados.");
			}
		} catch (ExcecaoNegocio e) {
			System.out.println(e.getMessage());
		}
	}


	private int lerInteiro(String mensagem) {
		int entrada = 0;
		boolean valido = false;

		while (!valido) {
			System.out.println("Digite " + mensagem + ": ");
			String input = scanner.nextLine();

			if (input.matches("\\d+")) {
				entrada = Integer.parseInt(input);
				valido = true;
			} else {
				System.out.println("Digite apenas números inteiros!");
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