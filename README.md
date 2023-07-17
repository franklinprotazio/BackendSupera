# BackendSupera
Este é o repositório do projeto BackendSupera, desenvolvido como parte do processo seletivo da empresa Supera. O projeto consiste em um backend para uma aplicação bancária.

## Descrição do Projeto

O BackendSupera é um sistema bancário que oferece funcionalidades de gerenciamento de contas bancárias e realização de transferências entre contas. A aplicação é desenvolvida em Java utilizando o framework Spring Boot e o banco de dados H2 (em memória).

## Funcionalidades

- Cadastro e gerenciamento de contas bancárias.
- Realização de transferências entre contas.
- Consulta de informações de contas por ID.
- Listagem de todas as contas e transferencias.

## Endpoints da API

O BackendSupera oferece os seguintes endpoints para interação com a API:

- GET /contas - Retorna a lista de todas as contas bancárias cadastradas.
- GET /contas/{id} - Retorna os detalhes de uma conta específica com base no ID.
- POST /contas - Cria uma nova conta bancária.
- PUT /contas/{id} - Atualiza os dados de uma conta existente.
- DELETE /contas/{id} - Remove uma conta bancária existente.
- POST /transferencias - Realiza uma transferência entre contas bancárias.
