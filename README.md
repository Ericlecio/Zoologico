Este projeto é um sistema de gerenciamento para um zoológico, desenvolvido em Java. O sistema permite o cadastro, atualização, remoção e consulta de animais. O projeto adota vários padrões de projeto para garantir uma arquitetura limpa e extensível.

Funcionalidades
Cadastro de Animais: Adiciona novos animais ao sistema.
Atualização de Animais: Modifica as informações dos animais já cadastrados.
Remoção de Animais: Remove animais do sistema.
Consulta de Animais: Permite a busca de animais por ID e a listagem de todos os animais cadastrados.
Padrões de Projeto Utilizados
Singleton: Garantia de que a classe de conexão com o banco de dados tenha uma única instância ao longo da aplicação.

Factory Method: Utilizado para criar instâncias de diferentes tipos de animais, permitindo a adição de novos tipos sem modificar o código existente.

DAO (Data Access Object): Abstrai o acesso ao banco de dados para as operações CRUD, separando a lógica de persistência da lógica de negócios.

Adapter: Adaptador para formatar e converter datas utilizando a biblioteca Joda-Time, permitindo que o sistema trabalhe com diferentes formatos de data de forma transparente.
