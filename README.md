![Status](https://img.shields.io/badge/STATUS-EM_DESENVOLVIMENTO-red?style=for-the-badge)
![Java](https://img.shields.io/badge/Java_-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Roadmap](https://img.shields.io/badge/Roadmap-EM_EVOLUÇÃO-purple?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

# 💰 API de Gestão Financeira

API REST desenvolvida para controle completo de finanças pessoais. 
Este projeto utiliza **Java**, **Spring Boot**, **PostgreSQL**, seguindo padrões de arquitetura em camadas (Controller, Service, Repository).

---

## Funcionalidades Principais

* **Entradas e Saídas:** Registro de Receitas (lucros, salários) e Despesas (gastos).
* **Investimentos:** Área separada para o registro de ativos e aplicações financeiras.
* **Contas Bancárias:** Gerenciamento de múltiplas contas com atualização automática de saldo conforme as transações ocorrem.
* **Formas de Pagamento:** Suporte para diferentes métodos (Pix, Cartão de Crédito, Boleto, etc.).
* **Categorização:** Organização por tipos de gastos.
---

## Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 
- **Banco de Dados:** PostgreSQL
- **Persistência:** Spring Data JPA / Hibernate
- **Documentação:** SpringDoc OpenAPI (Swagger)
- **Segurança:** Spring Security 
- **Build:** Maven

---

## Como Executar o Projeto

### 1. Pré-requisitos
- JDK 21
- PostgreSQL rodando localmente (ou via Docker)
- Maven
- **Documentação:** Swagger UI disponível em: `http://localhost:8080/swagger-ui/index.html`

### 2. Configuração do Banco
No seu arquivo `src/main/resources/application.properties`, configure as credenciais do seu PostgreSQL:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
