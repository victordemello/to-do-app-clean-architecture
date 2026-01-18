<div align="center">

<!-- Animated Header -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=6,11,20&height=180&section=header&text=To-Do%20App&fontSize=42&fontColor=fff&animation=twinkling&fontAlignY=32&desc=Clean%20Architecture%20%7C%20DDD%20%7C%20Hexagonal&descAlignY=52&descSize=18" width="100%"/>

<!-- Language Toggle -->
<a href="#português-">
  <img src="https://img.shields.io/badge/🇧🇷_Português-009739?style=for-the-badge" alt="Português"/>
</a>
<a href="#english-">
  <img src="https://img.shields.io/badge/🇺🇸_English-3C3B6E?style=for-the-badge" alt="English"/>
</a>

<br/><br/>

<!-- Animated Typing -->
<a href="https://git.io/typing-svg">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=22&pause=1000&color=6366F1&center=true&vCenter=true&multiline=true&repeat=true&width=600&height=100&lines=Enterprise-Grade+Task+Management;Clean+Architecture+%2B+DDD+%2B+Hexagonal;Spring+Boot+%7C+Quarkus+%7C+Angular" alt="Typing SVG" />
</a>
<br/><br/>
<br/>

<!-- Tech Badges -->
<p>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Quarkus-3.30.5-4695EB?style=for-the-badge&logo=quarkus&logoColor=white" alt="Quarkus"/>
  <img src="https://img.shields.io/badge/Angular-21-DD0031?style=for-the-badge&logo=angular&logoColor=white" alt="Angular"/>
  <img src="https://img.shields.io/badge/TypeScript-5.9-3178C6?style=for-the-badge&logo=typescript&logoColor=white" alt="TypeScript"/>
</p>

<p>
  <img src="https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/>
  <img src="https://img.shields.io/badge/Nginx-1.25-009639?style=for-the-badge&logo=nginx&logoColor=white" alt="Nginx"/>
</p>

<!-- Status Badges -->
<p>
  <img src="https://img.shields.io/badge/status-active-success?style=flat-square" alt="Status"/>
  <img src="https://img.shields.io/badge/license-MIT-blue?style=flat-square" alt="License"/>
  <img src="https://img.shields.io/badge/PRs-welcome-brightgreen?style=flat-square" alt="PRs Welcome"/>
  <img src="https://img.shields.io/badge/contributions-welcome-orange?style=flat-square" alt="Contributions"/>
</p>

</div>

---

<!-- TABLE OF CONTENTS -->
<details open>
<summary><b>📑 Índice / Table of Contents</b></summary>

| 🇧🇷 Português | 🇺🇸 English |
|:-------------:|:-----------:|
| [Sobre o Projeto](#-sobre-o-projeto) | [About the Project](#-about-the-project) |
| [Arquitetura](#%EF%B8%8F-arquitetura) | [Architecture](#%EF%B8%8F-architecture) |
| [Tecnologias](#-tecnologias) | [Technologies](#-technologies) |
| [Funcionalidades](#-funcionalidades) | [Features](#-features) |
| [Estrutura do Projeto](#-estrutura-do-projeto) | [Project Structure](#-project-structure) |
| [Início Rápido](#-início-rápido) | [Quick Start](#-quick-start) |
| [Documentação da API](#-documentação-da-api) | [API Documentation](#-api-documentation) |
| [Testes](#-testes) | [Testing](#-testing) |
| [Roadmap](#-roadmap) | [Roadmap](#-roadmap-1) |
| [Contribuindo](#-contribuindo) | [Contributing](#-contributing) |
| [Autor](#-autor) | [Author](#-author) |

</details>

---

<br/>

<!-- ==================== PORTUGUÊS ==================== -->

<div align="center">
  <img src="https://flagcdn.com/w80/br.png" width="50" alt="Brasil"/>

  # Português 🇧🇷
</div>

## 📋 Sobre o Projeto

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

> 🎯 **To-Do App** é uma aplicação completa de gerenciamento de tarefas que demonstra a implementação profissional de **Clean Architecture**, **Domain-Driven Design (DDD)** e **Arquitetura Hexagonal** com containerização Docker completa.

<br/>

### ✨ Destaques

<table>
<tr>
<td width="50%">

🏗️ **Arquitetura Enterprise-Grade**
- Clean Architecture com camadas bem definidas
- Hexagonal Architecture (Ports & Adapters)
- Domain-Driven Design completo
- Princípios SOLID aplicados

</td>
<td width="50%">

🔄 **Dual-Stack Backend**
- Spring Boot 3.2.5 + PostgreSQL
- Quarkus 3.30.5 + MySQL
- Mesmo domínio, diferentes frameworks
- Prova de independência de framework

</td>
</tr>
<tr>
<td width="50%">

🎨 **Frontend Moderno**
- Angular 21 com Standalone Components
- Angular Signals para reatividade
- PrimeNG para UI rica
- Kanban Board interativo

</td>
<td width="50%">

🐳 **DevOps Ready**
- Docker Compose com profiles
- Multi-stage builds otimizados
- Health checks automáticos
- Orquestração de serviços

</td>
</tr>
</table>

<br/>

## 🏛️ Arquitetura

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 🎯 Visão Geral da Arquitetura

```mermaid
flowchart TB
    subgraph FRONTEND["🌐 FRONTEND - Angular 21"]
        direction LR
        UI["📱 Kanban Board UI"]
        COMP["🎨 Components + Services"]
        SIG["⚡ Angular Signals"]
    end

    subgraph INFRA["INFRA - Adapters"]
        direction LR
        subgraph SPRING["🍃 Spring Boot"]
            SC["🎮 REST Controllers"]
            SD["📝 DTOs"]
            SR["📦 JPA Repos"]
        end
        subgraph QUARKUS["⚡ Quarkus"]
            QR["🎮 JAX-RS Resources"]
            QD["📝 DTOs"]
            QP["📦 Panache Repos"]
        end
    end

    subgraph CORE["CORE - Domain"]
        direction LR
        subgraph USECASES["📋 Use Cases"]
            UC1["CreateTaskUseCase"]
            UC2["GetTaskUseCase"]
            UC3["UpdateTaskUseCase"]
            UC4["DeleteTaskUseCase"]
            UC5["ListTasksUseCase"]
        end
        subgraph DOMAIN["🏛️ Domain"]
            ENT["📌 Entity: Task"]
            VO["💎 Value Objects: Title, Description"]
            ENUM["🔄 Enum: TaskStatus"]
            PORT["🚪 Port: ITaskRepository"]
        end
    end

    subgraph DB["💾 DATABASES"]
        direction LR
        PG["🐘 PostgreSQL 16<br/>(Spring Boot)"]
        MY["🐬 MySQL 8.0<br/>(Quarkus)"]
    end

    FRONTEND -->|"HTTP/REST"| INFRA
    INFRA -->|"Ports (Interfaces)"| CORE
    CORE -->|"Repository Implementations"| DB

    style FRONTEND fill:#4f46e5,stroke:#4338ca,color:#fff
    style INFRA fill:#059669,stroke:#047857,color:#fff
    style CORE fill:#d97706,stroke:#b45309,color:#fff
    style DB fill:#7c3aed,stroke:#6d28d9,color:#fff
    style SPRING fill:#6db33f,stroke:#5a9e2f,color:#fff
    style QUARKUS fill:#4695eb,stroke:#3b82f6,color:#fff
```

<br/>

### 🔄 Fluxo de Status das Tasks

```mermaid
stateDiagram-v2
    direction TB

    [*] --> BACKLOG : Criar Task

    state "📋 BACKLOG" as BACKLOG
    state "🔄 IN_PROGRESS" as IN_PROGRESS
    state "✅ DONE" as DONE
    state "❌ CANCELED" as CANCELED

    BACKLOG --> IN_PROGRESS : Iniciar
    BACKLOG --> CANCELED : Cancelar

    IN_PROGRESS --> DONE : Concluir
    IN_PROGRESS --> CANCELED : Cancelar

    DONE --> [*]
    CANCELED --> [*]

    note right of BACKLOG
        Estado inicial
        quando a task é criada
    end note

    note right of DONE
        Estado final
        task completada com sucesso
    end note

    note left of CANCELED
        Estado final
        task foi cancelada
    end note
```

<table>
<tr>
<td>

**✅ Transições Permitidas**
- `BACKLOG` → `IN_PROGRESS`
- `BACKLOG` → `CANCELED`
- `IN_PROGRESS` → `DONE`
- `IN_PROGRESS` → `CANCELED`

</td>
<td>

**❌ Estados Finais (sem transição)**
- `DONE`
- `CANCELED`

</td>
</tr>
</table>

<br/>

## 🛠 Tecnologias

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

<div align="center">

### Backend

| Tecnologia | Versão | Descrição |
|:----------:|:------:|:----------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="30"/> | 21 LTS | Linguagem principal |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="30"/> | 3.2.5 | Framework backend (Stack 1) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/quarkus/quarkus-original.svg" width="30"/> | 3.30.5 | Framework backend (Stack 2) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" width="30"/> | 16 | Banco de dados (Spring) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="30"/> | 8.0 | Banco de dados (Quarkus) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/hibernate/hibernate-original.svg" width="30"/> | - | ORM/JPA |

### Frontend

| Tecnologia | Versão | Descrição |
|:----------:|:------:|:----------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angular/angular-original.svg" width="30"/> | 21 | Framework frontend |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg" width="30"/> | 5.9 | Linguagem tipada |
| <img src="https://www.primefaces.org/wp-content/uploads/2016/10/primeng.png" width="30"/> | 21 | Biblioteca de componentes |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/sass/sass-original.svg" width="30"/> | - | Pré-processador CSS |

### DevOps & Infraestrutura

| Tecnologia | Versão | Descrição |
|:----------:|:------:|:----------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" width="30"/> | - | Containerização |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nginx/nginx-original.svg" width="30"/> | 1.25 | Proxy reverso |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="30"/> | 3 | Build tool (Backend) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/npm/npm-original-wordmark.svg" width="30"/> | 10.9 | Package manager (Frontend) |

</div>

<br/>

## ✨ Funcionalidades

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

<div align="center">

| Funcionalidade | Descrição | Status |
|:--------------|:----------|:------:|
| 📝 **Criar Tarefas** | Adicione tarefas com título e descrição | ✅ |
| 📋 **Listar Tarefas** | Visualize todas as tarefas organizadas por status | ✅ |
| ✏️ **Editar Tarefas** | Atualize título, descrição e status | ✅ |
| 🗑️ **Excluir Tarefas** | Soft delete com status CANCELED | ✅ |
| 🎯 **Kanban Board** | Interface drag-and-drop interativa | ✅ |
| 🔄 **Drag & Drop** | Mova tarefas entre colunas de status | ✅ |
| 📱 **Responsivo** | Layout adaptável para diferentes telas | ✅ |
| 🔔 **Notificações** | Toast messages para feedback de ações | ✅ |
| 📚 **API Docs** | Swagger/OpenAPI integrado | ✅ |
| 🏥 **Health Checks** | Monitoramento de saúde dos serviços | ✅ |
| 🔐 **Autenticação** | Spring Security + JWT | 🚧 |
| 📊 **Dashboard** | Métricas e analytics | 📋 |

</div>

**Legenda:** ✅ Concluído | 🚧 Em Desenvolvimento | 📋 Planejado

<br/>

## 📁 Estrutura do Projeto

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

```
📦 to-do-app-clean-arch
 ┣ 📂 to-do-app-backend
 ┃ ┗ 📂 to-do-app-backend
 ┃   ┣ 📂 to-do-app-core             # 💎 Domínio (Framework-Agnostic)
 ┃   ┃ ┗ 📂 src/main/java
 ┃   ┃   ┗ 📂 com/mello/todoappcore/task
 ┃   ┃     ┣ 📂 domain
 ┃   ┃     ┃ ┣ 📂 entities           # 📌 Task Entity
 ┃   ┃     ┃ ┣ 📂 vo                 # 💎 Value Objects (Title, Description)
 ┃   ┃     ┃ ┗ 📂 enums              # 🔄 TaskStatus
 ┃   ┃     ┣ 📂 ports                # 🚪 ITaskRepository (Outbound Port)
 ┃   ┃     ┣ 📂 usecases             # 📋 Use Case Interfaces (Inbound Ports)
 ┃   ┃     ┃ ┗ 📂 impl               # 🔧 Use Case Implementations
 ┃   ┃     ┗ 📂 exceptions           # ⚠️ Domain Exceptions
 ┃   ┃
 ┃   ┣ 📂 to-do-app-infra-spring     # 🍃 Spring Boot Adapter
 ┃   ┃ ┗ 📂 src/main/java
 ┃   ┃   ┗ 📂 com/mello/todoappinfra
 ┃   ┃     ┣ 📂 configuration        # ⚙️ Spring Config, CORS, OpenAPI
 ┃   ┃     ┗ 📂 task
 ┃   ┃       ┣ 📂 rest/controllers   # 🎮 REST Controllers
 ┃   ┃       ┣ 📂 rest/dto           # 📝 Request/Response DTOs
 ┃   ┃       ┣ 📂 persistence        # 💾 JPA Entities & Repositories
 ┃   ┃       ┗ 📂 mappers            # 🔄 Domain ↔ DTO Converters
 ┃   ┃
 ┃   ┗ 📂 to-do-app-infra-quarkus    # ⚡ Quarkus Adapter
 ┃     ┗ 📂 src/main/java
 ┃       ┗ 📂 com/mello/todoappquarkus
 ┃         ┣ 📂 resources            # 🎮 JAX-RS Resources
 ┃         ┣ 📂 persistence          # 💾 JPA Entities & Repositories
 ┃         ┣ 📂 producers            # 🏭 CDI Producers
 ┃         ┗ 📂 mappers              # 🔄 Converters
 ┃
 ┣ 📂 to-do-app-web                  # 🌐 Frontend Angular
 ┃ ┣ 📂 src/app
 ┃ ┃ ┣ 📂 components                 # 🧩 UI Components
 ┃ ┃ ┃ ┣ 📂 header                   # 🔝 Header Component
 ┃ ┃ ┃ ┣ 📂 task-card                # 🎴 Task Card
 ┃ ┃ ┃ ┣ 📂 task-column              # 📊 Kanban Column
 ┃ ┃ ┃ ┗ 📂 task-form-dialog         # 📝 Create/Edit Dialog
 ┃ ┃ ┣ 📂 pages/home                 # 🏠 Main Page (Kanban Board)
 ┃ ┃ ┣ 📂 services                   # 🔌 API Services
 ┃ ┃ ┗ 📂 models                     # 📋 TypeScript Types
 ┃ ┣ 📜 nginx.conf                   # 🔧 Nginx Configuration
 ┃ ┗ 📜 Dockerfile                   # 🐳 Frontend Docker Build
 ┃
 ┣ 📜 docker-compose.yml             # 🐳 Service Orchestration
 ┣ 📜 .env.spring                    # ⚙️ Spring Environment
 ┣ 📜 .env.quarkus                   # ⚙️ Quarkus Environment
 ┗ 📜 README.md                      # 📖 This file!
```

<br/>

## 🚀 Início Rápido

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 📋 Pré-requisitos

<table>
<tr>
<td>

**🐳 Docker (Recomendado)**
- Docker Desktop 4.x+
- Docker Compose 2.x+

</td>
<td>

**💻 Desenvolvimento Local**
- Java 21 (JDK)
- Node.js 20+
- Maven 3.9+

</td>
</tr>
</table>

<br/>

### 🐳 Execução com Docker (Recomendado)

<details open>
<summary><b>🍃 Stack Spring Boot + PostgreSQL</b></summary>

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/to-do-app-clean-arch.git
cd to-do-app-clean-arch

# Inicie os serviços
docker-compose --profile spring --env-file .env.spring up -d

# Acompanhe os logs
docker-compose --profile spring logs -f

# Pare os serviços
docker-compose --profile spring down
```

**🌐 URLs:**
| Serviço | URL |
|---------|-----|
| 📱 Frontend | http://localhost |
| 🔌 Backend API | http://localhost:8080 |
| 📚 Swagger UI | http://localhost:8080/swagger-ui.html |
| 🏥 Health Check | http://localhost:8080/actuator/health |
| 🐘 pgAdmin | http://localhost:5050 |

</details>

<details>
<summary><b>⚡ Stack Quarkus + MySQL</b></summary>

```bash
# Inicie os serviços
docker-compose --profile quarkus --env-file .env.quarkus up -d

# Acompanhe os logs
docker-compose --profile quarkus logs -f

# Pare os serviços
docker-compose --profile quarkus down
```

**🌐 URLs:**
| Serviço | URL |
|---------|-----|
| 📱 Frontend | http://localhost |
| 🔌 Backend API | http://localhost:8080 |
| 🏥 Health Check | http://localhost:8080/q/health |
| 🐬 Adminer | http://localhost:8081 |

</details>

<br/>

### 💻 Execução Local (Desenvolvimento)

<details>
<summary><b>🔧 Backend (Spring Boot)</b></summary>

```bash
# Entre no diretório do backend
cd to-do-app-backend/to-do-app-backend

# Compile o projeto
./mvnw clean install

# Inicie o PostgreSQL (via Docker)
docker-compose --profile spring up postgres -d

# Execute o backend
./mvnw spring-boot:run -pl to-do-app-infra-spring
```

</details>

<details>
<summary><b>🎨 Frontend (Angular)</b></summary>

```bash
# Entre no diretório do frontend
cd to-do-app-web

# Instale as dependências
npm install

# Execute em modo desenvolvimento
npm start
```

Acesse: http://localhost:4200

</details>

<br/>

## 📖 Documentação da API

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 📝 Endpoints

| Método | Endpoint | Descrição | Request Body |
|:------:|:---------|:----------|:-------------|
| `POST` | `/api/task/create` | Criar nova tarefa | `{ "title": "string", "description": "string" }` |
| `GET` | `/api/task/{id}` | Buscar tarefa por ID | - |
| `GET` | `/api/task/all` | Listar todas as tarefas | - |
| `PUT` | `/api/task/{id}` | Atualizar tarefa | `{ "title": "string", "description": "string", "status": "enum" }` |
| `DELETE` | `/api/task/{id}` | Excluir tarefa (soft delete) | - |

<br/>

### 📊 Exemplos de Requisições

<details>
<summary><b>➕ Criar Tarefa</b></summary>

```bash
curl -X POST http://localhost:8080/api/task/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Implementar autenticação",
    "description": "Adicionar Spring Security com JWT"
  }'
```

**Response (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Implementar autenticação",
  "description": "Adicionar Spring Security com JWT",
  "status": "BACKLOG",
  "createdAt": "2025-01-17T10:30:00Z"
}
```

</details>

<details>
<summary><b>🔄 Atualizar Status</b></summary>

```bash
curl -X PUT http://localhost:8080/api/task/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Implementar autenticação",
    "description": "Adicionar Spring Security com JWT",
    "status": "IN_PROGRESS"
  }'
```

</details>

<br/>

## 🧪 Testes

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 🔬 Backend Tests

```bash
# Executar todos os testes
./mvnw test

# Executar apenas testes do core (domínio)
./mvnw test -pl to-do-app-core

# Executar com cobertura (se configurado)
./mvnw test jacoco:report
```

**Tipos de Testes:**
- ✅ **Unit Tests**: Entidade Task, Value Objects, Use Cases
- ✅ **Integration Tests**: REST Controllers, Repositories
- ✅ **Fake Repository**: Implementação em memória para testes isolados

### 🎨 Frontend Tests

```bash
cd to-do-app-web

# Executar testes unitários
npm test

# Executar com coverage
npm run test:coverage
```

<br/>

## 🗺 Roadmap

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

```mermaid
timeline
    title Roadmap do Projeto

    section Fase 1 - Core ✅
        MVP : CRUD de Tasks
             : Clean Architecture
             : Dual-Stack Backend
             : Docker Compose

    section Fase 2 - Segurança 🚧
        Autenticação : Spring Security
                     : JWT Tokens
                     : Role-Based Access

    section Fase 3 - Observabilidade 📋
        Monitoring : Prometheus
                  : Grafana Dashboards
                  : Distributed Tracing

    section Fase 4 - Cloud 📋
        Kubernetes : Helm Charts
                  : CI/CD Pipelines
                  : Cloud Deployment
```

<br/>

### 📋 Próximas Funcionalidades

- [ ] 🔐 **Autenticação & Autorização** - Spring Security + JWT
- [ ] 📊 **Dashboard Analytics** - Métricas de produtividade
- [ ] 🔔 **Notificações Push** - WebSocket integration
- [ ] 📁 **Categorias/Tags** - Organização de tarefas
- [ ] 🔍 **Busca Avançada** - Filtros e ordenação
- [ ] 📱 **PWA** - Suporte offline
- [ ] 🌍 **i18n** - Internacionalização
- [ ] 📈 **Prometheus + Grafana** - Observabilidade
- [ ] ☸️ **Kubernetes** - Deploy em cluster

<br/>

## 🤝 Contribuindo

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

Contribuições são **muito bem-vindas**! 🎉

1. Faça um Fork do projeto
2. Crie sua Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

<br/>

## 👤 Autor

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

<div align="center">
  <a href="https://github.com/victordemello">
    <img src="https://img.shields.io/badge/Victor_Mello-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/>
  </a>
  <a href="https://www.linkedin.com/in/mellodevictor/">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a>
</div>

<br/>

---

<br/>

<!-- ==================== ENGLISH ==================== -->

<div align="center">
  <img src="https://flagcdn.com/w80/us.png" width="50" alt="USA"/>

  # English 🇺🇸
</div>

## 📋 About the Project

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

> 🎯 **To-Do App** is a complete task management application that demonstrates professional implementation of **Clean Architecture**, **Domain-Driven Design (DDD)**, and **Hexagonal Architecture** with full Docker containerization.

<br/>

### ✨ Highlights

<table>
<tr>
<td width="50%">

🏗️ **Enterprise-Grade Architecture**
- Clean Architecture with well-defined layers
- Hexagonal Architecture (Ports & Adapters)
- Complete Domain-Driven Design
- SOLID principles applied

</td>
<td width="50%">

🔄 **Dual-Stack Backend**
- Spring Boot 3.2.5 + PostgreSQL
- Quarkus 3.30.5 + MySQL
- Same domain, different frameworks
- Proof of framework independence

</td>
</tr>
<tr>
<td width="50%">

🎨 **Modern Frontend**
- Angular 21 with Standalone Components
- Angular Signals for reactivity
- PrimeNG for rich UI
- Interactive Kanban Board

</td>
<td width="50%">

🐳 **DevOps Ready**
- Docker Compose with profiles
- Optimized multi-stage builds
- Automatic health checks
- Service orchestration

</td>
</tr>
</table>

<br/>

## 🏛️ Architecture

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 🎯 Architecture Overview

```mermaid
flowchart TB
    subgraph FRONTEND["FRONTEND - Angular 21"]
        direction LR
        UI["📱 Kanban Board UI"]
        COMP["🎨 Components + Services"]
        SIG["⚡ Angular Signals"]
    end

    subgraph INFRA["INFRA - Adapters"]
        direction LR
        subgraph SPRING["🍃 Spring Boot"]
            SC["🎮 REST Controllers"]
            SD["📝 DTOs"]
            SR["📦 JPA Repos"]
        end
        subgraph QUARKUS["⚡ Quarkus"]
            QR["🎮 JAX-RS Resources"]
            QD["📝 DTOs"]
            QP["📦 Panache Repos"]
        end
    end

    subgraph CORE["CORE - Domain"]
        direction LR
        subgraph USECASES["📋 Use Cases"]
            UC1["CreateTaskUseCase"]
            UC2["GetTaskUseCase"]
            UC3["UpdateTaskUseCase"]
            UC4["DeleteTaskUseCase"]
            UC5["ListTasksUseCase"]
        end
        subgraph DOMAIN["🏛️ Domain"]
            ENT["📌 Entity: Task"]
            VO["💎 Value Objects: Title, Description"]
            ENUM["🔄 Enum: TaskStatus"]
            PORT["🚪 Port: ITaskRepository"]
        end
    end

    subgraph DB["💾 DATABASES"]
        direction LR
        PG["🐘 PostgreSQL 16<br/>(Spring Boot)"]
        MY["🐬 MySQL 8.0<br/>(Quarkus)"]
    end

    FRONTEND -->|"HTTP/REST"| INFRA
    INFRA -->|"Ports (Interfaces)"| CORE
    CORE -->|"Repository Implementations"| DB

    style FRONTEND fill:#4f46e5,stroke:#4338ca,color:#fff
    style INFRA fill:#059669,stroke:#047857,color:#fff
    style CORE fill:#d97706,stroke:#b45309,color:#fff
    style DB fill:#7c3aed,stroke:#6d28d9,color:#fff
    style SPRING fill:#6db33f,stroke:#5a9e2f,color:#fff
    style QUARKUS fill:#4695eb,stroke:#3b82f6,color:#fff
```

<br/>

### 🔄 Task Status Flow

```mermaid
stateDiagram-v2
    direction TB

    [*] --> BACKLOG : Create Task

    state "📋 BACKLOG" as BACKLOG
    state "🔄 IN_PROGRESS" as IN_PROGRESS
    state "✅ DONE" as DONE
    state "❌ CANCELED" as CANCELED

    BACKLOG --> IN_PROGRESS : Start
    BACKLOG --> CANCELED : Cancel

    IN_PROGRESS --> DONE : Complete
    IN_PROGRESS --> CANCELED : Cancel

    DONE --> [*]
    CANCELED --> [*]

    note right of BACKLOG
        Initial state
        when task is created
    end note

    note right of DONE
        Final state
        task completed successfully
    end note

    note left of CANCELED
        Final state
        task was canceled
    end note
```

<table>
<tr>
<td>

**✅ Allowed Transitions**
- `BACKLOG` → `IN_PROGRESS`
- `BACKLOG` → `CANCELED`
- `IN_PROGRESS` → `DONE`
- `IN_PROGRESS` → `CANCELED`

</td>
<td>

**❌ Final States (no transitions)**
- `DONE`
- `CANCELED`

</td>
</tr>
</table>

<br/>

## 🛠 Technologies

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

<div align="center">

### Backend

| Technology | Version | Description |
|:----------:|:-------:|:------------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="30"/> | 21 LTS | Main language |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="30"/> | 3.2.5 | Backend framework (Stack 1) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/quarkus/quarkus-original.svg" width="30"/> | 3.30.5 | Backend framework (Stack 2) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" width="30"/> | 16 | Database (Spring) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="30"/> | 8.0 | Database (Quarkus) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/hibernate/hibernate-original.svg" width="30"/> | - | ORM/JPA |

### Frontend

| Technology | Version | Description |
|:----------:|:-------:|:------------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angular/angular-original.svg" width="30"/> | 21 | Frontend framework |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg" width="30"/> | 5.9 | Typed language |
| <img src="https://www.primefaces.org/wp-content/uploads/2016/10/primeng.png" width="30"/> | 21 | Component library |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/sass/sass-original.svg" width="30"/> | - | CSS preprocessor |

### DevOps & Infrastructure

| Technology | Version | Description |
|:----------:|:-------:|:------------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" width="30"/> | - | Containerization |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nginx/nginx-original.svg" width="30"/> | 1.25 | Reverse proxy |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="30"/> | 3 | Build tool (Backend) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/npm/npm-original-wordmark.svg" width="30"/> | 10.9 | Package manager (Frontend) |

</div>

<br/>

## ✨ Features

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

<div align="center">

| Feature | Description | Status |
|:--------|:------------|:------:|
| 📝 **Create Tasks** | Add tasks with title and description | ✅ |
| 📋 **List Tasks** | View all tasks organized by status | ✅ |
| ✏️ **Edit Tasks** | Update title, description and status | ✅ |
| 🗑️ **Delete Tasks** | Soft delete with CANCELED status | ✅ |
| 🎯 **Kanban Board** | Interactive drag-and-drop interface | ✅ |
| 🔄 **Drag & Drop** | Move tasks between status columns | ✅ |
| 📱 **Responsive** | Adaptive layout for different screens | ✅ |
| 🔔 **Notifications** | Toast messages for action feedback | ✅ |
| 📚 **API Docs** | Integrated Swagger/OpenAPI | ✅ |
| 🏥 **Health Checks** | Service health monitoring | ✅ |
| 🔐 **Authentication** | Spring Security + JWT | 🚧 |
| 📊 **Dashboard** | Metrics and analytics | 📋 |

</div>

**Legend:** ✅ Completed | 🚧 In Development | 📋 Planned

<br/>

## 📁 Project Structure

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

```
📦 to-do-app-clean-arch
 ┣ 📂 to-do-app-backend
 ┃ ┗ 📂 to-do-app-backend
 ┃   ┣ 📂 to-do-app-core             # 💎 Domain (Framework-Agnostic)
 ┃   ┃ ┗ 📂 src/main/java
 ┃   ┃   ┗ 📂 com/mello/todoappcore/task
 ┃   ┃     ┣ 📂 domain
 ┃   ┃     ┃ ┣ 📂 entities           # 📌 Task Entity
 ┃   ┃     ┃ ┣ 📂 vo                 # 💎 Value Objects (Title, Description)
 ┃   ┃     ┃ ┗ 📂 enums              # 🔄 TaskStatus
 ┃   ┃     ┣ 📂 ports                # 🚪 ITaskRepository (Outbound Port)
 ┃   ┃     ┣ 📂 usecases             # 📋 Use Case Interfaces (Inbound Ports)
 ┃   ┃     ┃ ┗ 📂 impl               # 🔧 Use Case Implementations
 ┃   ┃     ┗ 📂 exceptions           # ⚠️ Domain Exceptions
 ┃   ┃
 ┃   ┣ 📂 to-do-app-infra-spring     # 🍃 Spring Boot Adapter
 ┃   ┃ ┗ 📂 src/main/java
 ┃   ┃   ┗ 📂 com/mello/todoappinfra
 ┃   ┃     ┣ 📂 configuration        # ⚙️ Spring Config, CORS, OpenAPI
 ┃   ┃     ┗ 📂 task
 ┃   ┃       ┣ 📂 rest/controllers   # 🎮 REST Controllers
 ┃   ┃       ┣ 📂 rest/dto           # 📝 Request/Response DTOs
 ┃   ┃       ┣ 📂 persistence        # 💾 JPA Entities & Repositories
 ┃   ┃       ┗ 📂 mappers            # 🔄 Domain ↔ DTO Converters
 ┃   ┃
 ┃   ┗ 📂 to-do-app-infra-quarkus    # ⚡ Quarkus Adapter
 ┃     ┗ 📂 src/main/java
 ┃       ┗ 📂 com/mello/todoappquarkus
 ┃         ┣ 📂 resources            # 🎮 JAX-RS Resources
 ┃         ┣ 📂 persistence          # 💾 JPA Entities & Repositories
 ┃         ┣ 📂 producers            # 🏭 CDI Producers
 ┃         ┗ 📂 mappers              # 🔄 Converters
 ┃
 ┣ 📂 to-do-app-web                  # 🌐 Frontend Angular
 ┃ ┣ 📂 src/app
 ┃ ┃ ┣ 📂 components                 # 🧩 UI Components
 ┃ ┃ ┃ ┣ 📂 header                   # 🔝 Header Component
 ┃ ┃ ┃ ┣ 📂 task-card                # 🎴 Task Card
 ┃ ┃ ┃ ┣ 📂 task-column              # 📊 Kanban Column
 ┃ ┃ ┃ ┗ 📂 task-form-dialog         # 📝 Create/Edit Dialog
 ┃ ┃ ┣ 📂 pages/home                 # 🏠 Main Page (Kanban Board)
 ┃ ┃ ┣ 📂 services                   # 🔌 API Services
 ┃ ┃ ┗ 📂 models                     # 📋 TypeScript Types
 ┃ ┣ 📜 nginx.conf                   # 🔧 Nginx Configuration
 ┃ ┗ 📜 Dockerfile                   # 🐳 Frontend Docker Build
 ┃
 ┣ 📜 docker-compose.yml             # 🐳 Service Orchestration
 ┣ 📜 .env.spring                    # ⚙️ Spring Environment
 ┣ 📜 .env.quarkus                   # ⚙️ Quarkus Environment
 ┗ 📜 README.md                      # 📖 This file!
```

<br/>

## 🚀 Quick Start

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 📋 Prerequisites

<table>
<tr>
<td>

**🐳 Docker (Recommended)**
- Docker Desktop 4.x+
- Docker Compose 2.x+

</td>
<td>

**💻 Local Development**
- Java 21 (JDK)
- Node.js 20+
- Maven 3.9+

</td>
</tr>
</table>

<br/>

### 🐳 Running with Docker (Recommended)

<details open>
<summary><b>🍃 Spring Boot + PostgreSQL Stack</b></summary>

```bash
# Clone the repository
git clone https://github.com/your-user/to-do-app-clean-arch.git
cd to-do-app-clean-arch

# Start services
docker-compose --profile spring --env-file .env.spring up -d

# Follow logs
docker-compose --profile spring logs -f

# Stop services
docker-compose --profile spring down
```

**🌐 URLs:**
| Service | URL |
|---------|-----|
| 📱 Frontend | http://localhost |
| 🔌 Backend API | http://localhost:8080 |
| 📚 Swagger UI | http://localhost:8080/swagger-ui.html |
| 🏥 Health Check | http://localhost:8080/actuator/health |
| 🐘 pgAdmin | http://localhost:5050 |

</details>

<details>
<summary><b>⚡ Quarkus + MySQL Stack</b></summary>

```bash
# Start services
docker-compose --profile quarkus --env-file .env.quarkus up -d

# Follow logs
docker-compose --profile quarkus logs -f

# Stop services
docker-compose --profile quarkus down
```

**🌐 URLs:**
| Service | URL |
|---------|-----|
| 📱 Frontend | http://localhost |
| 🔌 Backend API | http://localhost:8080 |
| 🏥 Health Check | http://localhost:8080/q/health |
| 🐬 Adminer | http://localhost:8081 |

</details>

<br/>

### 💻 Local Execution (Development)

<details>
<summary><b>🔧 Backend (Spring Boot)</b></summary>

```bash
# Enter backend directory
cd to-do-app-backend/to-do-app-backend

# Build the project
./mvnw clean install

# Start PostgreSQL (via Docker)
docker-compose --profile spring up postgres -d

# Run backend
./mvnw spring-boot:run -pl to-do-app-infra-spring
```

</details>

<details>
<summary><b>🎨 Frontend (Angular)</b></summary>

```bash
# Enter frontend directory
cd to-do-app-web

# Install dependencies
npm install

# Run in development mode
npm start
```

Access: http://localhost:4200

</details>

<br/>

## 📖 API Documentation

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 📝 Endpoints

| Method | Endpoint | Description | Request Body |
|:------:|:---------|:------------|:-------------|
| `POST` | `/api/task/create` | Create new task | `{ "title": "string", "description": "string" }` |
| `GET` | `/api/task/{id}` | Get task by ID | - |
| `GET` | `/api/task/all` | List all tasks | - |
| `PUT` | `/api/task/{id}` | Update task | `{ "title": "string", "description": "string", "status": "enum" }` |
| `DELETE` | `/api/task/{id}` | Delete task (soft delete) | - |

<br/>

### 📊 Request Examples

<details>
<summary><b>➕ Create Task</b></summary>

```bash
curl -X POST http://localhost:8080/api/task/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Implement authentication",
    "description": "Add Spring Security with JWT"
  }'
```

**Response (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Implement authentication",
  "description": "Add Spring Security with JWT",
  "status": "BACKLOG",
  "createdAt": "2025-01-17T10:30:00Z"
}
```

</details>

<details>
<summary><b>🔄 Update Status</b></summary>

```bash
curl -X PUT http://localhost:8080/api/task/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Implement authentication",
    "description": "Add Spring Security with JWT",
    "status": "IN_PROGRESS"
  }'
```

</details>

<br/>

## 🧪 Testing

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

### 🔬 Backend Tests

```bash
# Run all tests
./mvnw test

# Run only core (domain) tests
./mvnw test -pl to-do-app-core

# Run with coverage (if configured)
./mvnw test jacoco:report
```

**Test Types:**
- ✅ **Unit Tests**: Task Entity, Value Objects, Use Cases
- ✅ **Integration Tests**: REST Controllers, Repositories
- ✅ **Fake Repository**: In-memory implementation for isolated tests

### 🎨 Frontend Tests

```bash
cd to-do-app-web

# Run unit tests
npm test

# Run with coverage
npm run test:coverage
```

<br/>

## 🗺 Roadmap

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

```mermaid
timeline
    title Project Roadmap

    section Phase 1 - Core ✅
        MVP : Task CRUD
             : Clean Architecture
             : Dual-Stack Backend
             : Docker Compose

    section Phase 2 - Security 🚧
        Authentication : Spring Security
                      : JWT Tokens
                      : Role-Based Access

    section Phase 3 - Observability 📋
        Monitoring : Prometheus
                  : Grafana Dashboards
                  : Distributed Tracing

    section Phase 4 - Cloud 📋
        Kubernetes : Helm Charts
                  : CI/CD Pipelines
                  : Cloud Deployment
```

<br/>

### 📋 Upcoming Features

- [ ] 🔐 **Authentication & Authorization** - Spring Security + JWT
- [ ] 📊 **Dashboard Analytics** - Productivity metrics
- [ ] 🔔 **Push Notifications** - WebSocket integration
- [ ] 📁 **Categories/Tags** - Task organization
- [ ] 🔍 **Advanced Search** - Filters and sorting
- [ ] 📱 **PWA** - Offline support
- [ ] 🌍 **i18n** - Internationalization
- [ ] 📈 **Prometheus + Grafana** - Observability
- [ ] ☸️ **Kubernetes** - Cluster deployment

<br/>

## 🤝 Contributing

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

Contributions are **very welcome**! 🎉

1. Fork the project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<br/>

## 👤 Author

<div align="center">
  <img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>
</div>

<br/>

<div align="center">
  <a href="https://github.com/victormello">
    <img src="https://img.shields.io/badge/Victor_Mello-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/>
  </a>
  <a href="https://linkedin.com/in/victormello">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a>
</div>

<br/>

---

<br/>

<!-- Footer -->
<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=6,11,20&height=100&section=footer" width="100%"/>
</div>

<div align="center">
  <sub>Built with ❤️ by <a href="https://github.com/victormello">Victor Mello</a></sub>
  <br/>
  <sub>⭐ Star this repo if you find it useful!</sub>
</div>
