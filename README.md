📘 ApiTechOne – Sistema de Gestão de Ordens de Serviço 

A ApiTechOne é uma API REST desenvolvida em Java Spring Boot 3, projetada para gerenciar clientes, usuários, equipes técnicas e ordens de serviço (OS) de maneira moderna, segura e escalável. ela também fornece autenticação JWT, controle de permissões e integrações práticas para uso com um aplicativo móvel ou web.

🚀 Tecnologias Utilizadas

Java 17

Spring Boot 3

Spring Security + JWT

Spring Data JPA / Hibernate

PostgreSQL (Neon Database)

Maven

Render (Deploy via Docker)

🌐 URLs da API

Local: http://localhost:8080

Produção (Render): https://tech-on-api.onrender.com

🔐 Autenticação (JWT)

A autenticação é feita via:

POST /api/auth/login
Body:
{
  "email": "admin@gmail.com",
  "senha": "1234"
}

Resposta:
{
  "nome": "Administrador",
  "email": "admin@gmail.com",
  "role": "ADMIN",
  "token": "Bearer xxxxx..."
}


Para acessar rotas protegidas:

Authorization: Bearer <TOKEN>

👥 Perfis de Usuário (Roles)
Role	Permissões
ADMIN	Criar/editar usuários, clientes e OS. Ver TODAS as OS. Atribuir técnico.
TECNICO	Ver apenas suas próprias OS. Alterar status, prioridade e descrição.
🧩 Entidades Principais
1. Clientes

nome

telefone

email

endereço

ativo

2. Usuários

nome

email

senha (criptografada)

role (ADMIN/TECNICO)

3. Ordem de Serviço

título

descrição

cliente

técnico

prioridade (ENUM)

status (ENUM)

dataAgendada

agendada

🧭 Endpoints da API
📌 Clientes
Método	Rota	Descrição
POST	/api/clientes	Criar cliente
GET	/api/clientes	Listar todos
GET	/api/clientes/{id}	Buscar por ID
PUT	/api/clientes/{id}	Atualizar
DELETE	/api/clientes/{id}	Remover
📌 Usuários
Método	Rota	Descrição
POST	/api/usuarios	Criar usuário
GET	/api/usuarios	Listar usuários
GET	/api/usuarios/{id}	Buscar por ID
PUT	/api/usuarios/{id}	Atualizar
DELETE	/api/usuarios/{id}	Remover
📌 Ordens de Serviço (OS)
Método	Rota	Descrição
POST	/api/os	Criar OS (ADMIN)
GET	/api/os	Listar (ADMIN → todas, TECNICO → apenas as suas)
GET	/api/os/{id}	Buscar OS
PUT	/api/os/{id}	Atualizar OS
PUT	/api/os/{id}/atribuir/{tecnicoId}	Atribuir técnico
GET	/api/os/hoje	OS agendadas para o dia atual
🗂️ Filtro Especial: OS do Dia (Home do Técnico)
GET /api/os/hoje


Regras:

ADMIN → vê todas

TECNICO → vê apenas as suas

Ordenação automática:

OS em aberto primeiro

depois OS concluídas

dentro de cada grupo → ordena por horário

🤖 Automação de Testes

Este projeto possui um script Node.js que:

Faz login automaticamente

Cria clientes em lote

Cria ordens de serviço

Prepara ambiente de demonstração rapidamente

🐳 Deploy (Render + Docker)

A aplicação é empacotada em um container com:

Java Temurin 17

Build com Maven

Execução automática do .jar gerado em /target

📦 Como Rodar Localmente
1. Clonar repositório:
git clone https://github.com/TonhaoIsrael/Tech-On-api

2. Rodar:
./mvnw spring-boot:run

📄 Scripts Úteis
Criar build:
./mvnw clean package -DskipTests

Rodar JAR:
java -jar target/ApiTechOne-0.0.1-SNAPSHOT.jar

🧱 Arquitetura do Sistema
Front-End 
        ↓
     ApiTechOne (Spring Boot)
        ↓
PostgreSQL Database (Neon)

🎓 Sobre o Projeto 

Este sistema foi desenvolvido para demonstrar:

uso de autenticação JWT

controle de permissões (RBAC)

CRUD completo

integração real com banco em nuvem

deploy profissional

organização modular de serviços, controllers e entidades

automação de testes via script externo

📜 Licença

Projeto acadêmico. Uso livre para demonstração e fins educativos.
