package br.edu.ifpe.zoologico.apresentacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import br.edu.ifpe.zoologico.log.LogZoologico;
import br.edu.ifpe.zoologico.entidades.*;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.negocio.Fachada;
import br.edu.ifpe.zoologico.util.DataNascimento;

public class TelaZoologico {
    
    private Scanner scanner;
    private Fachada fachada;

    public TelaZoologico() {
        this.scanner = new Scanner(System.in);
        new DataNascimento();
        this.fachada = new Fachada();
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n=============================================");
            System.out.println("         SEJA BEM-VINDO AO ZOOLÓGICO         ");
            System.out.println("=============================================");
            System.out.println("1. Inserir Zoológico");
            System.out.println("2. Consultar Zoológico específico");
            System.out.println("3. Consultar todos os zoológicos");
            System.out.println("4. Remover Zoológico");
            System.out.println("===============================================");
            System.out.println("5. Inserir animal");
            System.out.println("6. Editar Animal");
            System.out.println("7. Remover Animal");
            System.out.println("8. Consultar Animal específico");
            System.out.println("9. Consultar todos os animais");
            System.out.println("==============================================");
            System.out.println("0. Sair do sistema");
            System.out.println("=============================================");

            opcao = lerInteiro("opção");

            switch (opcao) {
                case 1:
                    inserirZoologico();
                    break;
                case 2:
                    consultarZoologicoID();
                    break;
                case 3:
                    consultarTodosZoologicos();
                    break;
                case 4:
                	removerZoologicoPorID();
                    break;
                case 5:
                    inserirAnimal();
                    break;
                case 6:
                    editarAnimal();
                    break;
                case 7:
                    removerAnimal();
                    break;
                case 8:
                    consultarAnimalID();
                    break;
                case 9:
                    consultarTodosAnimais();
                    break;
                case 0:
                    System.out.println("\nSaindo do sistema... Até mais!");
                    LogZoologico.registrarMovimentacao("Usuário saiu do sistema.");
                    break;
                default:
                    System.out.println("\n[ERRO] Opção inválida! Digite um número entre 1 e 10.");
                    break;
            }
        } while (opcao != 9);
    }

    //ANIMAL CRUD
    private void inserirAnimal() {
        System.out.println("Cadastro de Animal");
        
       
        try {
            if (fachada.consultarTodosZoologicos().isEmpty()) {
                System.out.println("Não há zoológicos cadastrados. Não é possível cadastrar um animal.");
                LogZoologico.registrarMovimentacao("Tentativa de cadastrar um animal sem zoológicos cadastrados.");
                return;
            }
        } catch (ExcecaoNegocio e) {
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
    	
    	try {
            if (fachada.consultarTodosZoologicos().isEmpty()) {
                System.out.println("Não há zoológicos cadastrados. Não é possível editar um animal.");
                LogZoologico.registrarMovimentacao("Tentativa de editar um animal sem zoológicos cadastrados.");
                return;
            }
        } catch (ExcecaoNegocio e) {
            e.printStackTrace();
        }
    	
        System.out.println("Edição de Animal");

        Animal animalExistente = null;
        int id = -1;

        while (animalExistente == null) {
            id = lerInteiro("ID do animal");

            try {
                animalExistente = fachada.consultarAnimalPorId(id);
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
            fachada.editarAnimal(animalExistente);
            System.out.println("Animal editado com sucesso!");
            LogZoologico.registrarMovimentacao(String.format("Animal editado com sucesso. ID: %d, Novo Nome: %s, Nova Espécie: %s", animalExistente.getId(), novoNome, novaEspecie));
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao editar animal com o id " + animalExistente.getId());
            LogZoologico.registrarMovimentacao("Erro ao editar animal com ID: " + animalExistente.getId() + " - " + e.getMessage());
        }
    }

    private void removerAnimal() {
    	
    	try {
            if (fachada.consultarTodosZoologicos().isEmpty()) {
                System.out.println("Não há zoológicos cadastrados. Não é possível remover um animal.");
                LogZoologico.registrarMovimentacao("Tentativa de remover um animal sem zoológicos cadastrados.");
                return;
            }
        } catch (ExcecaoNegocio e) {
            e.printStackTrace();
        }
    	
        System.out.println("Remoção de Animal");
        int id = lerInteiro("ID do animal");

        try {
            fachada.removerAnimal(id);
            System.out.println("Animal removido com sucesso!");
            LogZoologico.registrarMovimentacao("Animal removido com sucesso. ID: " + id);
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao remover animal: " + e.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao remover animal com ID: " + id + " - " + e.getMessage());
        }
    }

    private void consultarAnimalID() {
        System.out.println("Consulta de Animal");
        int id = lerInteiro("ID do animal");

        try {
            Animal animal = fachada.consultarAnimalPorId(id);
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
            List<Animal> animais = fachada.consultarTodosAnimais();
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
            try {
                entrada = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Entrada inválida. Digite um número inteiro.");
            }
        }

        return entrada;
    }

    private String lerString(String mensagem) {
        System.out.println("Digite o " + mensagem + ": ");
        return scanner.nextLine();
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

    //ZOOLOGICO CRD
    private void inserirZoologico() {
        System.out.println("Cadastro de Zoológico");
        String nome = lerString("nome do zoológico");
        String endereco = lerString("endereço");

        try {
            Zoologico zoologico = new Zoologico(nome, endereco); 
            zoologico = fachada.adicionarZoologico(zoologico); 
            System.out.println("Zoológico cadastrado com sucesso! ID: " + zoologico.getId()); 
            LogZoologico.registrarMovimentacao("Zoológico cadastrado com sucesso: " + zoologico.getNome() + ", ID: " + zoologico.getId());
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao cadastrar zoológico: " + e.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao cadastrar zoológico: " + e.getMessage());
        }
    }

    private void removerZoologicoPorID() {
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

    private void consultarZoologicoID() {
        System.out.println("Consulta de Zoológico");
        int id = lerInteiro("ID do zoológico");

        try {
            Zoologico zoologico = fachada.consultarZoologicoPorId(id);
            if (zoologico != null) {
                System.out.println("ID: " + zoologico.getId());
                System.out.println("Nome: " + zoologico.getNome());
                System.out.println("Endereço: " + zoologico.getEndereco());
                LogZoologico.registrarMovimentacao("Consulta de zoológico com sucesso. ID: " + id);
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
            List<Zoologico> zoologicos = fachada.consultarTodosZoologicos();
            if (!zoologicos.isEmpty()) {
                System.out.println("Lista de zoológicos:");
                for (Zoologico zoologico : zoologicos) {
                    System.out.println("---------------------------------------------");
                    System.out.println("ID: " + zoologico.getId());
                    System.out.println("Nome: " + zoologico.getNome());
                    System.out.println("Endereço: " + zoologico.getEndereco());
                }
                System.out.println("---------------------------------------------");
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
