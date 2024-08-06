package br.edu.ifpe.zoologico.apresentacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import br.edu.ifpe.zoologico.entidades.*;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.negocio.FabricaControlador;
import br.edu.ifpe.zoologico.negocio.Fachada;
import br.edu.ifpe.zoologico.negocio.IControladorAnimal;
import br.edu.ifpe.zoologico.util.AdapterDataNascimento;
import br.edu.ifpe.zoologico.util.DataNascimento;

public class TelaAnimal {

	private Scanner scanner;
	private AdapterDataNascimento dataNascimentoAdapter;
	private Fachada fachada;

	public TelaAnimal() {
		this.scanner = new Scanner(System.in);
		this.dataNascimentoAdapter = new DataNascimento();
        this.fachada = new Fachada();
	}

	public void exibir() {
		int opcao = 0;
		do {
			System.out.println("-------------------------------------------");
			System.out.println("Seja Bem Vindo ao Zoologico!");
			System.out.println("Digite 1 para cadastrar um animal;");
			System.out.println("Digite 2 para editar os dados de um animal;");
			System.out.println("Digite 3 para remover um animal;");
			System.out.println("Digite 4 para consultar um animal específico;");
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
		String dataNascimento = lerDataNascimento();
		int tipoAnimal = lerInteiro("tipo de animal (1-Mamífero, 2-Ave, 3-Réptil)");

		Animal animal;
		switch (tipoAnimal) {
		case 1:
			animal = new AnimalMamifero(nome, especie, dataNascimento);
			break;
		case 2:
			animal = new AnimalAve(nome, especie, dataNascimento);
			break;
		case 3:
			animal = new AnimalReptil(nome, especie, dataNascimento);
			break;
		default:
			System.out.println("Tipo de animal inválido.");
			return;
		}

		Comportamento comportamento = new AnimalConcreto(animal);
		comportamento = inserirComportamentos(comportamento);
		animal.setComportamento(comportamento);

		try {
			fachada.cadastrarAnimal(animal);
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
			animalExistente = controlador.consultarPorId(id);
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
		String novaDataNascimento = lerDataNascimento();

		animalExistente.setNome(novoNome);
		animalExistente.setEspecie(novaEspecie);
		animalExistente.setDataNascimento(novaDataNascimento);

		Comportamento comportamento = new AnimalConcreto(animalExistente);
		comportamento = inserirComportamentos(comportamento);
		animalExistente.setComportamento(comportamento);

		try {
			fachada.editar(animalExistente);
			System.out.println("Animal editado com sucesso!");
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao editar animal com o id " + animalExistente.getId());
		}
	}

	private void removerAnimal() {
		System.out.println("Remoção de Animal");
		IControladorAnimal controlador = FabricaControlador.getControladorAnimal();

		int id = lerInteiro("ID do animal");

		try {
			fachada.remover(id);
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
			Animal animal = fachada.consultarPorId(id);
			if (animal != null) {
				System.out.println("Animal encontrado:");
				System.out.println("ID: " + animal.getId());
				System.out.println("Nome: " + animal.getNome());
				System.out.println("Espécie: " + animal.getEspecie());
				System.out.println("Data de Nascimento (Extenso): " + dataNascimentoAdapter.formatarExtenso(animal.getDataNascimento()));
				System.out.println("Data de Nascimento (Sistema Português): " + dataNascimentoAdapter.formatarSistemaPortugues(animal.getDataNascimento()));
				if (animal.getComportamento() != null) {
					System.out.println(animal.getComportamento().Acao());
				} else {
					System.out.println("Este animal não possui ações especiais.");
				}
				animal.limpar();
				animal.alimentarAnimal();
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
			List<Animal> animais = fachada.consultarTodos();
			if (!animais.isEmpty()) {
				System.out.println("Lista de animais:");
				for (Animal animal : animais) {
					System.out.println("---------------------------------------------");
					System.out.println("ID: " + animal.getId());
					System.out.println("Nome: " + animal.getNome());
					System.out.println("Espécie: " + animal.getEspecie());
					System.out.println("Data de Nascimento (Extenso): " + dataNascimentoAdapter.formatarExtenso(animal.getDataNascimento()));
					System.out.println("Data de Nascimento (Sistema Português): " + dataNascimentoAdapter.formatarSistemaPortugues(animal.getDataNascimento()));
					if (animal.getComportamento() != null) {
						System.out.println(animal.getComportamento().Acao());
					} else {
						System.out.println("Este animal não possui ações especiais.");
					}
					animal.limpar();
					animal.alimentarAnimal();
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

	private String lerDataNascimento() {
		String data = null;
		boolean valido = false;

		while (!valido) {
			System.out.println("Digite a data de nascimento (no formato Ano-Mes-Dia): ");
			String input = scanner.nextLine();

			try {
				LocalDate.parse(input);
				data = input;
				valido = true;
			} catch (Exception ex) {
				System.out.println("Data inválida! Use o formato Ano-Mes-Dia (por exemplo, 2020-01-31).");
			}
		}
		return data;
	}

	private Comportamento inserirComportamentos(Comportamento comportamento) {
		int opcaoComportamento = 0;

		while (true) {
			System.out.println("Digite 1 para adicionar comportamento de Correr;");
			System.out.println("Digite 2 para adicionar comportamento de Dormir;");
			System.out.println("Digite 3 para adicionar comportamento de Comer;");
			System.out.println("Digite 4 para adicionar comportamento de Brincar;");
			System.out.println("Digite 5 para finalizar a adição de comportamentos;");
			try {
				opcaoComportamento = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Digite um número válido!");
				continue;
			}

			switch (opcaoComportamento) {
			case 1:
				comportamento = new ComportamentoCorrer(comportamento);
				System.out.println("Comportamento de correr adicionado.");
				break;
			case 2:
				comportamento = new ComportamentoNadar(comportamento);
				System.out.println("Comportamento de nadar adicionado.");
				break;
			case 3:
				comportamento = new ComportamentoRastejar(comportamento);
				System.out.println("Comportamento de rastejar adicionado.");
				break;
			case 4:
				comportamento = new ComportamentoVoar(comportamento);
				System.out.println("Comportamento de voar adicionado.");
				break;
			case 5:
				System.out.println("Finalizando a adição de comportamentos.");
				return comportamento;
			default:
				System.out.println("Opção inválida! Digite números entre 1 e 5.");
				break;
			}
		}
	}
}
