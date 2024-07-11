package br.edu.ifpe.zoologico.apresentacao;

import java.util.List;
import java.util.Scanner;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.negocio.FabricaControlador;
import br.edu.ifpe.zoologico.negocio.IControladorAnimal;

public class TelaAnimal {

	private Scanner scanner;

	public TelaAnimal() {
		this.scanner = new Scanner(System.in);
	}

	public void exibir() {
		int opcao = 0;
		do {
			System.out.println("-------------------------------------------");
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
				continue;
			}

			switch (opcao) {
			case 1:
				cadastrarAnimal();
				break;
			case 2:
				editarAnimal();
				break;
			case 3:
				removerAnimal();
				break;
			case 4:
				consultarAnimal();
				break;
			case 5:
				consultarTodosAnimais();
				break;
			case 6:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida! Digite os números entre 1 e 6.");
				break;
			}
		} while (opcao != 6);
	}

	private void cadastrarAnimal() {
		System.out.println("Cadastro de Animal");
		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		String nome = lerString("nome");
		String especie = lerString("espécie");
		String dataNascimento = lerString("data de nascimento");

		Animal.AnimalBuilder builder = new Animal.AnimalBuilder()
				.nome(nome)
				.especie(especie)
				.dataNascimento(dataNascimento);

		Animal animal = builder.criar();

		try {
			controlador.inserir(animal);
			System.out.println("Animal cadastrado com sucesso! ID: " + animal.getId());
		} catch (ExcecaoNegocio excecao) {
			System.out.println("Erro ao cadastrar animal: " + excecao.getMessage());
		}
	}

	private void editarAnimal() {
		System.out.println("Edição de Animal");
		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		int id = lerInteiro("ID do animal");

		Animal animalExistente = null;
		try {
			animalExistente = controlador.consultar(id);
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao consultar animal: " + e.getMessage());
			return;
		}

		if (animalExistente == null) {
			System.out.println("Animal não encontrado com o ID: " + id);
			return;
		}

		String novoNome = lerString("novo nome");
		String novaEspecie = lerString("nova espécie");
		String novaDataNascimento = lerString("nova data de nascimento");

		Animal animal = new Animal(novoNome, novaEspecie, novaDataNascimento);
		animal.setId(id); 

		try {
			controlador.editar(animal);
			System.out.println("Animal editado com sucesso!");
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao editar animal com o id " + animal.getId());
		}
	}

	private void removerAnimal() {
		System.out.println("Remoção de Animal");
		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		int id = lerInteiro("ID do animal");

		try {
			controlador.remover(id);
			System.out.println("Animal removido com sucesso!");
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao remover animal: " + e.getMessage());
		}
	}

	private void consultarAnimal() {
		System.out.println("Consulta de Animal");
		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		int id = lerInteiro("ID do animal");

		try {
			Animal animal = controlador.consultar(id);
			if (animal != null) {
				System.out.println("Animal encontrado:");
				System.out.println("ID: " + animal.getId());
				System.out.println("Nome: " + animal.getNome());
				System.out.println("Espécie: " + animal.getEspecie());
				System.out.println("Data de Nascimento: " + animal.getDataNascimento());
			} else {
				System.out.println("Animal não encontrado.");
			}
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao consultar animal: " + e.getMessage());
		}
	}

	private void consultarTodosAnimais() {
		System.out.println("Lista de Todos os Animais");
		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		try {
			List<Animal> animais = controlador.consultarTodos();
			if (!animais.isEmpty()) {
				System.out.println("Lista de animais:");
				for (Animal animal : animais) {
					System.out.println("---------------------------------------------");
					System.out.println("ID: " + animal.getId());
					System.out.println("Nome: " + animal.getNome());
					System.out.println("Espécie: " + animal.getEspecie());
					System.out.println("Data de Nascimento: " + animal.getDataNascimento());
				}
				System.out.println("---------------------------------------------");
			} else {
				System.out.println("Não há animais cadastrados.");
			}
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao consultar animais: " + e.getMessage());
		}
	}

	private int lerInteiro(String mensagem) {
		int entrada = 0;
		boolean valido = false;

		while (!valido) {
			System.out.println("Digite o " + mensagem + ": ");
			String input = scanner.nextLine();

			try {
				entrada = Integer.parseInt(input);
				valido = true;
			} catch (NumberFormatException ex) {
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