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
            System.out.println("3. Voltar");
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
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
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
   
}
