# Alura Parking Meter System

## Resumo:
Este projeto foi desenvolvido como parte do Alura Tech Challenge Fase III e implementa um sistema web com interfaces RESTful para a gestão de um sistema de estacionamento com zona azul digital.

## Descrição dos Endpoints das APIs

### API de Sessão de Estacionamento:

- `POST localhost:8080/parking_session` - Cria uma nova sessão de estacionamento.
- `GET localhost:8080/parking_session` - Lista todas as sessões de estacionamento.
- `GET localhost:8080/parking_session/{id}` - Busca uma sessão de estacionamento por ID.
- `PUT localhost:8080/parking_session/{id}` - Atualiza uma sessão de estacionamento existente.
- `DELETE localhost:8080/parking_session/{id}` - Remove uma sessão de estacionamento.

### API de Cobranças:

- `POST localhost:8080/blue_zone_parking/charge` - Registra uma nova cobrança.
- `GET localhost:8080/blue_zone_parking/charge/{id}` - Obtém os detalhes de uma cobrança por ID.

### API de Veículos:

- `POST localhost:8080/vehicles` - Adiciona um novo veículo ao sistema.
- `GET localhost:8080/vehicles` - Lista todos os veículos.
- `GET localhost:8080/vehicles/{id}` - Detalha um veículo específico.
- `PUT localhost:8080/vehicles/{id}` - Atualiza os dados de um veículo.
- `DELETE localhost:8080/vehicles/{id}` - Exclui um veículo do registro.

### API de Usuários:

- `POST localhost:8080/users` - Cadastra um novo usuário.
- `GET localhost:8080/users` - Lista todos os usuários.
- `GET localhost:8080/users/{id}` - Retorna os detalhes de um usuário.
- `PUT localhost:8080/users/{id}` - Atualiza informações de um usuário.
- `DELETE localhost:8080/users/{id}` - Apaga o cadastro de um usuário.

## Requisitos
- JDK 17 ou superior
- Maven para gestão e build do projeto
- Spring Boot 2.5+ para desenvolvimento dos endpoints REST
- Hibernate para ORM
- Docker para containerização da aplicação
- DBeaver para gerenciamento do banco de dados

## Testes
TBD

## Desenvolvedora
- [Isadora](mailto:isadora.mendoncal@mercadolivre.com)
