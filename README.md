# 💰 Financial IT — Sistema de Contas a Pagar e Receber
### Arquitetura Hexagonal • Spring Boot • PostgreSQL • Docker • Profiles DEV/QA/PROD


---

## 📌 **📖 Sobre o Projeto**

O **Financial IT** é um sistema financeiro voltado para o controle de **Contas a Pagar**, **Contas a Receber** e **Fornecedores**, utilizando **Arquitetura Hexagonal** para garantir baixo acoplamento, alta testabilidade e forte separação entre domínio e infraestrutura.

Ele foi criado para servir como um template robusto para aplicações financeiras profissionais.

---

## 🧱 **Arquitetura Utilizada: Hexagonal Architecture (Ports & Adapters)**

Este projeto é totalmente estruturado seguindo o modelo:


        src/main/java/com/itgen/financialit/
        │
        ├── application → Orquestra regras, serviços e casos de uso
            ├── port
            │   ├── in → Entrada (controllers chamam isso)
            │   └── out → Saída (repositories, gateways)
        │   └── service → Implementação dos casos de uso
        │
        ├── domain → Regras de negócio puras (sem Spring)
        │   ├── model
        │   └── exception
        │
        ├── adapters
        │   ├── in/rest → Controllers + DTOs + Mappers
        │   └── out/persistence → Entities + Repositories + Mappers
        │
        └── config → Beans, Swagger, Profiles




✔ Baixo acoplamento  
✔ Independência de framework  
✔ Fácil troca de banco de dados  
✔ Domínio 100% puro

---

## 🧩 **Modelagem das Entidades**

### 📌 **ContaPagar**
- id
- descricao
- valor
- vencimento
- status
- categoria
- fornecedor_id

### 📌 **ContaReceber**
- id
- descricao
- valor
- vencimento
- status
- categoria

### 📌 **Fornecedor**
- id
- nome
- cnpj

### 📌 **Enums**
- **Status:** PENDENTE, PAGO, ATRASADO
- **Categoria:** PRODUTO, SERVICO, OUTROS

---

## 🗃️ **DER – Diagrama Entidade Relacionamento**

---

## 📦 **Configuração de Perfis (DEV, QA, PROD)**

O projeto possui três arquivos:

    application.yml
    application-dev.yml
    application-qa.yml
    application-prod.yml



### 🔹 Ativando um profile


Ou em `application.yml`:


`spring:
  profiles:
    active: dev`

### 🔹 🚀 Como Rodar o Projeto
### ▶️ 1. Subir Banco com Docker


`docker-compose up -d`


Verificar:

`docker exec -it financial-postgres psql -U financial -d financialdb`

### ▶️ 2. Rodar a Aplicação (DEV)
`mvn spring-boot:run -Dspring-boot.run.profiles=dev`

🔧 Tecnologias Utilizadas 
Tecnologia	Uso
Java 17+	Linguagem principal
Spring Boot 3	Framework
Spring Web	Controllers REST
Spring Data JPA	Persistência
PostgreSQL	Banco de Dados
Docker / Docker Compose	Infraestrutura
Lombok	Redução de boilerplate
Flyway	Migração de banco
Swagger / OpenAPI	Documentação da API
### 📘 Swagger UI

Após rodar:

`http://localhost:8080/swagger-ui.html`

### 🧪 Testes
`mvn test`

### 🎯 Roadmap

 Implementar autenticação JWT

 Criar módulo de relatórios (PDF/Excel)

 Criar dashboard financeiro

 Criar microserviços independentes

### 🤝 Contribuição

Pull requests são bem-vindos!
Sugestões? Abra uma issue.

### 📄 Licença

MIT License.

### ✨ Autor

Enio Oliveira
Desenvolvedor Backend Java / Arquitetura Hexagonal / Spring Boot
