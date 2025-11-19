# ApiTechOn – Gestão de Ordem de Serviço em Campo

API REST desenvolvida em **Java 17** com **Spring Boot 3**, voltada para o **gerenciamento de Ordens de Serviço (OS) em campo**, controle de clientes e usuários (técnicos e administradores), com autenticação via **JWT** e regras de permissão por **role** (ADMIN / TECNICO).

---

## Tecnologias

- Java 17  
- Spring Boot 3 (Web, Data JPA, Security, Validation)  
- PostgreSQL  
- JPA / Hibernate  
- JWT (jjwt)  
- Lombok  

---

## Principais funcionalidades

- Autenticação e login com **JWT**
  - Login via `/api/auth/login`
  - Senhas criptografadas com BCrypt
- Gestão de usuários:
  - ADMIN e TECNICO (via entidade `Role`)
  - CRUD de usuários
- Gestão de clientes:
  - CRUD de clientes
- Gestão de Ordem de Serviço:
  - Criação de OS (ADMIN)
  - Atribuição de técnico
  - Atualização de status, prioridade e descrição
  - Regras de acesso:
    - ADMIN vê e altera todas as OS
    - TECNICO só vê e altera as OS atribuídas a ele
- Filtro de OS do dia:
  - Endpoint `/api/os/hoje`
  - Retorna somente as OS do **dia atual**
  - Ordenação por:
    1. Status (ABERTO → EM_ANDAMENTO → CONCLUIDO → CANCELADO)
    2. Horário (`dataAgendada`)

---

## Modelagem (resumo)

### Entidades principais

- `Cliente`
- `Usuario`
- `Role`
- `OrdemServico`

### Enums

- `StatusOS`
  - `ABERTO`, `EM_ANDAMENTO`, `CONCLUIDO`, `CANCELADO`
- `Prioridade`
  - `BAIXA`, `MEDIA`, `ALTA`, `URGENTE`

---

## Requisitos

- JDK 17+
- Maven 3+
- PostgreSQL

---

## Configuração

As configurações de banco e servidor estão em:

`src/main/resources/application.properties`

Exemplo (ajuste conforme seu ambiente):

```properties
spring.application.name=ApiTechOne

server.port=8080

spring.datasource.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE>?sslmode=require
spring.datasource.username=<USUARIO>
spring.datasource.password=<SENHA>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
