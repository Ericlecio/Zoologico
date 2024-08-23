package br.edu.ifpe.zoologico.apresentacao;


import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;
import br.edu.ifpe.zoologico.log.LogZoologico;
import br.edu.ifpe.zoologico.negocio.FachadaZoologico;

import java.util.List;
import java.util.Scanner;

public class TelaZoologico {
    private FachadaZoologico fachada;
    private Scanner scanner;

    public TelaZoologico() {
        fachada = new FachadaZoologico();
        scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("==== Tela Zoológico ====");
            System.out.println("1. Adicionar Zoológico");
            System.out.println("2. Remover Zoológico");
            System.out.println("3. Consultar Zoológico por ID");
            System.out.println("4. Listar Todos os Zoológicos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarZoologico();
                    break;
                case 2:
                    removerZoologico();
                    break;
                case 3:
                    consultarZoologico();
                    break;
                case 4:
                    listarTodosZoologicos();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void adicionarZoologico() {
        System.out.print("Digite o nome do zoológico: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço do zoológico: ");
        String endereco = scanner.nextLine();

        Zoologico zoologico = new Zoologico.ZoologicoBuilder()
                .nome(nome)
                .endereco(endereco)
                .criar();

        try {
            fachada.adicionarZoologico(zoologico);
            System.out.println("Zoológico adicionado com sucesso!");
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao adicionar zoológico: " + e.getMessage());
        }
    }

    private void removerZoologico() {
        System.out.print("Digite o ID do zoológico a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        try {
            fachada.remover(id);
            System.out.println("Zoológico removido com sucesso!");
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao remover zoológico: " + e.getMessage());
        }
    }

    private void consultarZoologico() {
        System.out.print("Digite o ID do zoológico a ser consultado: ");
        int id = lerInteiro("ID do zoologico: ");

        try {
            Zoologico zoologico = fachada.consultarPorId(id);
            if (zoologico != null) {
            	exibirInformacoesZoologico(zoologico);
				LogZoologico.registrarMovimentacao(String.format("Consulta de zoologico com sucesso. ID: %d", id));
			} else {
				System.out.println("Zoológico não encontrado.");
				LogZoologico.registrarMovimentacao("Zoológico não encontrado com ID: " + id);
			}
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao consultar zoológico: " + e.getMessage());
            LogZoologico.registrarMovimentacao("Erro ao consultar zoologico com ID: " + id + " - " + e.getMessage());
        }
    }

    private void listarTodosZoologicos() {
        try {
            List<Zoologico> zoologicos = fachada.listarTodosZoologicos();
            System.out.println("Lista de Zoológicos:");
            for (Zoologico zoologico : zoologicos) {
                exibirInformacoesZoologico(zoologico);
            }
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao listar zoológicos: " + e.getMessage());
        }
    }
    
    
    private void exibirInformacoesZoologico(Zoologico zoologico) {
		System.out.println("ID: " + zoologico.getId());
		System.out.println("Nome: " + zoologico.getNome());
		System.out.println("Endereço: " + zoologico.getEndereco());
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
}
