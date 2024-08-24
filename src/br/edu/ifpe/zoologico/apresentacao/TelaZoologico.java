package br.edu.ifpe.zoologico.apresentacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import br.edu.ifpe.zoologico.log.LogZoologico;
import br.edu.ifpe.zoologico.entidades.*;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.negocio.Fachada;
import br.edu.ifpe.zoologico.util.AdapterDataNascimento;
import br.edu.ifpe.zoologico.util.DataNascimento;

public class TelaZoologico {
    
    private Scanner scanner;
    private AdapterDataNascimento dataNascimentoAdapter;
    private Fachada fachada;

    public TelaZoologico() {
        this.scanner = new Scanner(System.in);
        this.dataNascimentoAdapter = new DataNascimento();
        this.fachada = new Fachada();
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n=============================================");
            System.out.println("         SEJA BEM-VINDO AO ZOOLÓGICO         ");
            System.out.println("=============================================");
            System.out.println("1. Cadastrar um animal");
            System.out.println("2. Editar os dados de um animal");
            System.out.println("3. Remover um animal");
            System.out.println("4. Consultar um animal específico");
            System.out.println("5. Consultar todos os animais");
            System.out.println("6. Cadastrar Zoológico");
            System.out.println("7. Consultar Zoológico");
            System.out.println("8. Consultar todos os zoologicos");
            System.out.println("9. Remover Zoologico");
            System.out.println("10. Sair do sistema");
            System.out.println("=============================================");

            opcao = lerInteiro("uma opção");

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
                    adicionarZoologico();
                    break;
                case 7:
                    consultarZoologico();
                    break;
                case 8:
                	consultarTodosZoologicos();
                    break;
                case 9:
                    removerZoologico();
                    break;
                case 10:
                    System.out.println("\nSaindo do sistema... Até mais!");
                    LogZoologico.registrarMovimentacao("Usuário saiu do sistema.");
                    break;
                default:
                    System.out.println("\n[ERRO] Opção inválida! Digite um número entre 1 e 9.");
                    break;
            }
        } while (opcao != 10);
    }

    private void cadastrarAnimal() {
        System.out.println("Cadastro de Animal");
        
        // Verifica se há pelo menos um zoológico cadastrado
        try {
			if (fachada.listarTodosZoologicos().isEmpty()) {
			    System.out.println("Não há zoológicos cadastrados. Não é possível cadastrar um animal.");
			    LogZoologico.registrarMovimentacao("Tentativa de cadastrar um animal sem zoológicos cadastrados.");
			    return;
			}
		} catch (ExcecaoNegocio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String nome = lerString("nome");
        String especie = lerString("espécie");
        String dataNascimento = lerDataNascimento();
        
        int tipoAnimal = 0;
        boolean tipoAnimalValido = false;
        
        while (!tipoAnimalValido) {
            tipoAnimal = lerInteiro("tipo de animal (1-Mamífero, 2-Ave, 3-Réptil)");
            
            if (tipoAnimal < 1 || tipoAnimal > 3) {
                System.out.println("Tipo de animal inválido. Por favor, tente novamente.");
                LogZoologico.registrarMovimentacao(String.format("Tentativa de cadastrar um animal com tipo inválido: %d", tipoAnimal));
            } else {
                tipoAnimalValido = true;
            }
        }

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
                return;
        }

        Comportamento comportamento = inserirComportamentos();
        animal.setComportamento(comportamento);

        try {
            fachada.cadastrarAnimal(animal);
            System.out.println("Animal cadastrado com sucesso! ID: " + animal.getId());
            LogZoologico.registrarMovimentacao(String.format("Animal cadastrado com sucesso. ID: %d, Nome: %s, Espécie: %s", animal.getId(), animal.getNome(), animal.getEspecie()));
        } catch (ExcecaoNegocio excecao) {
            System.out.println("Erro ao cadastrar animal: " + excecao.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao cadastrar animal: " + excecao.getMessage());
        }
    }

	private void editarAnimal() {
	    System.out.println("Edição de Animal");

	    Animal animalExistente = null;
	    int id = -1;

	    while (animalExistente == null) {
	        id = lerInteiro("ID do animal");

	        try {
	            animalExistente = fachada.consultarPorId(id);
	        } catch (ExcecaoNegocio e) {
	            System.out.println("Erro ao consultar animal: " + e.getMessage());
	            LogZoologico.registrarMovimentacao("Erro ao consultar animal com ID: " + id + " - " + e.getMessage());
	            continue;
	        }

	        if (animalExistente == null) {
	            System.out.println("Animal não encontrado com o ID: " + id);
	            LogZoologico.registrarMovimentacao("Tentativa de editar animal com ID inexistente: " + id);
	        }
	    }

	    String novoNome = lerString("novo nome");
	    String novaEspecie = lerString("nova espécie");
	    String novaDataNascimento = lerDataNascimento();

	    animalExistente.setNome(novoNome);
	    animalExistente.setEspecie(novaEspecie);
	    animalExistente.setDataNascimento(novaDataNascimento);

	    Comportamento comportamento = inserirComportamentos();
	    animalExistente.setComportamento(comportamento);

	    try {
	        fachada.editar(animalExistente);
	        System.out.println("Animal editado com sucesso!");
	        LogZoologico.registrarMovimentacao(String.format("Animal editado com sucesso. ID: %d, Novo Nome: %s, Nova Espécie: %s", animalExistente.getId(), novoNome, novaEspecie));
	    } catch (ExcecaoNegocio e) {
	        System.out.println("Erro ao editar animal com o id " + animalExistente.getId());
	        LogZoologico.registrarMovimentacao("Erro ao editar animal com ID: " + animalExistente.getId() + " - " + e.getMessage());
	    }
	}

	private void removerAnimal() {
		System.out.println("Remoção de Animal");
		int id = lerInteiro("ID do animal");

		try {
			fachada.remover(id);
			System.out.println("Animal removido com sucesso!");
			LogZoologico.registrarMovimentacao("Animal removido com sucesso. ID: " + id);
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao remover animal: " + e.getMessage());
			LogZoologico.registrarMovimentacao("Erro ao remover animal com ID: " + id + " - " + e.getMessage());
		}
	}

	private void consultarAnimal() {
		System.out.println("Consulta de Animal");
		int id = lerInteiro("ID do animal");

		try {
			Animal animal = fachada.consultarPorId(id);
			if (animal != null) {
				exibirInformacoesAnimal(animal);
				LogZoologico.registrarMovimentacao(String.format("Consulta de animal com sucesso. ID: %d", id));
			} else {
				System.out.println("Animal não encontrado.");
				LogZoologico.registrarMovimentacao("Animal não encontrado com ID: " + id);
			}
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao consultar animal: " + e.getMessage());
			LogZoologico.registrarMovimentacao("Erro ao consultar animal com ID: " + id + " - " + e.getMessage());
		}
	}

	private void consultarTodosAnimais() {
		System.out.println("Lista de Todos os Animais");

		try {
			List<Animal> animais = fachada.consultarTodos();
			if (!animais.isEmpty()) {
				System.out.println("Lista de animais:");
				for (Animal animal : animais) {
					System.out.println("---------------------------------------------");
					exibirInformacoesAnimal(animal);
				}
				System.out.println("---------------------------------------------");
				LogZoologico.registrarMovimentacao("Consulta de todos os animais realizada com sucesso.");
			} else {
				System.out.println("Não há animais cadastrados.");
				LogZoologico.registrarMovimentacao("Nenhum animal cadastrado encontrado na consulta de todos os animais.");
			}
		} catch (ExcecaoNegocio e) {
			System.out.println("Erro ao consultar animais: " + e.getMessage());
			LogZoologico.registrarMovimentacao("Erro ao consultar todos os animais: " + e.getMessage());
		}
	}
	
	private void exibirInformacoesAnimal(Animal animal) {
		System.out.println("ID: " + animal.getId());
		System.out.println("Nome: " + animal.getNome());
		System.out.println("Espécie: " + animal.getEspecie());
		System.out.println("Data de Nascimento (Extenso): " + animal.getDataNascimentoExtenso());
		System.out.println("Data de Nascimento (Sistema Português): " + animal.getDataNascimentoSistemaPortugues());

		if (animal instanceof AnimalMamifero) {
			System.out.println("Tipo: Mamífero");
		} else if (animal instanceof AnimalAve) {
			System.out.println("Tipo: Ave");
		} else if (animal instanceof AnimalReptil) {
			System.out.println("Tipo: Réptil");
		}

		System.out.println("Método de Limpeza: " + animal.limpar());
		System.out.println("Método de Alimentação: " + animal.alimentarAnimal());

		if (animal.getComportamento() != null) {
			System.out.println("Comportamento: " + animal.getComportamento().Acao());
		} else {
			System.out.println("Este animal não possui ações especiais.");
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
				System.out.println("Entrada inválida! Digite apenas números inteiros.");
			}
		}
		return entrada;
	}

	private String lerString(String nomeAtributo) {
		String entrada = "";

		while (entrada.trim().isEmpty() || !isStringValida(entrada)) {
			System.out.println("Digite o " + nomeAtributo + " (somente letras e espaços): ");
			entrada = scanner.nextLine();
			if (!isStringValida(entrada)) {
				System.out.println("Entrada inválida! O " + nomeAtributo + " deve conter apenas letras e espaços.");
			}
		}
		return entrada;
	}

	private boolean isStringValida(String entrada) {
		return entrada.matches("[a-zA-Z\\s]+");
	}

	private String lerDataNascimento() {
	    String data = null;
	    boolean valido = false;

	    while (!valido) {
	        System.out.println("Digite a data de nascimento (no formato Ano-Mês-Dia): ");
	        String input = scanner.nextLine();

	        try {
	            LocalDate dataNascimento = LocalDate.parse(input);
	            int anoAtual = LocalDate.now().getYear();

	            if (dataNascimento.getYear() > anoAtual) {
	                System.out.println("O ano de nascimento não pode ser maior que o ano atual.");
	            } else if (dataNascimento.isAfter(LocalDate.now())) {
	                System.out.println("A data de nascimento não pode ser no futuro.");
	            } else {
	                data = input;
	                valido = true;
	            }
	        } catch (Exception ex) {
	            System.out.println("Data inválida! Use o formato Ano-Mês-Dia (por exemplo, 2020-01-31).");
	        }
	    }
	    return data;
	}

	private Comportamento inserirComportamentos() {
		Comportamento comportamento = null;
		int opcaoComportamento;

		while (true) {
			System.out.println("Escolha um comportamento para adicionar:");
			System.out.println("1. Correr");
			System.out.println("2. Nadar");
			System.out.println("3. Rastejar");
			System.out.println("4. Voar");
			System.out.println("5. Finalizar");
			opcaoComportamento = lerInteiro("uma opção");

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
				System.out.println("Finalizado a adição de comportamentos.");
				LogZoologico.registrarMovimentacao("Finalização da adição de comportamentos.");
				return comportamento;
			default:
				System.out.println("Opção inválida! Digite números entre 1 e 5.");
				break;
			}
		}
	}

    private void adicionarZoologico() {
        System.out.println("Cadastro de Zoológico");
        String nome = lerString("nome do zoológico");
        String localizacao = lerString("localização");

        Zoologico zoologico = new Zoologico(nome, localizacao);

        try {
            fachada.adicionarZoologico(zoologico);
            System.out.println("Zoológico adicionado com sucesso!");
            LogZoologico.registrarMovimentacao(String.format("Zoológico adicionado com sucesso. Nome: %s, Localização: %s", nome, localizacao));
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao adicionar zoológico: " + e.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao adicionar zoológico: " + e.getMessage());
        }
    }

    private void removerZoologico() {
        System.out.println("Remoção de Zoológico");
        int id = lerInteiro("ID do zoológico");

        try {
            fachada.removerZoologico(id);
            System.out.println("Zoológico removido com sucesso!");
            LogZoologico.registrarMovimentacao("Zoológico removido com sucesso. ID: " + id);
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao remover zoológico: " + e.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao remover zoológico com ID: " + id + " - " + e.getMessage());
        }
    }

    private void consultarZoologico() {
        System.out.println("Consulta de Zoológico");
        int id = lerInteiro("ID do zoológico");

        try {
            Zoologico zoologico = fachada.consultarPorIdZoologico(id);
            if (zoologico != null) {
                System.out.println("Informações do Zoológico:");
                System.out.println("Nome: " + zoologico.getNome());
                System.out.println("Localização: " + zoologico.getEndereco());
                LogZoologico.registrarMovimentacao(String.format("Consulta de zoológico com sucesso. ID: %d", id));
            } else {
                System.out.println("Zoológico não encontrado.");
                LogZoologico.registrarMovimentacao("Zoológico não encontrado com ID: " + id);
            }
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao consultar zoológico: " + e.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao consultar zoológico com ID: " + id + " - " + e.getMessage());
        }
    }

    private void consultarTodosZoologicos() {
        System.out.println("Lista de Todos os Zoológicos");

        try {
            List<Zoologico> zoologicos = fachada.listarTodosZoologicos();
            if (!zoologicos.isEmpty()) {
                System.out.println("Lista de zoológicos:");
                for (Zoologico zoologico : zoologicos) {
                    System.out.println("---------------------------------------------");
                    System.out.println("ID: " + zoologico.getId());
                    System.out.println("Nome: " + zoologico.getNome());
                    System.out.println("Localização: " + zoologico.getEndereco());
                    System.out.println("---------------------------------------------");
                }
                LogZoologico.registrarMovimentacao("Consulta de todos os zoológicos realizada com sucesso.");
            } else {
                System.out.println("Não há zoológicos cadastrados.");
                LogZoologico.registrarMovimentacao("Nenhum zoológico cadastrado encontrado na consulta de todos os zoológicos.");
            }
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao consultar zoológicos: " + e.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao consultar todos os zoológicos: " + e.getMessage());
        }
    }

    
}
