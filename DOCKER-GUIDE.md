# Docker Guide - To-Do App Clean Architecture

Este projeto demonstra Clean Architecture com **duas implementações de infraestrutura diferentes** compartilhando o mesmo core de negócio:

- **Spring Boot + PostgreSQL**
- **Quarkus + MySQL**

## Escolhendo a Implementação

Use Docker Compose profiles para escolher qual stack rodar. **Apenas uma implementação pode rodar por vez**.

---

## Stack 1: Spring Boot + PostgreSQL

### Serviços incluídos:
- **backend-spring**: Spring Boot 3.2.5 (porta 8080)
- **postgres**: PostgreSQL 16 (porta 5432)
- **pgadmin**: Interface de administração PostgreSQL (porta 5050)
- **frontend**: Angular app (porta 80)

### Como rodar:

```bash
# Opção 1: Usando o profile diretamente
docker-compose --profile spring up

# Opção 2: Usando o arquivo .env específico (recomendado)
docker-compose --profile spring --env-file .env.spring up

# Rodar em background
docker-compose --profile spring --env-file .env.spring up -d

# Ver logs
docker-compose --profile spring logs -f

# Parar os serviços
docker-compose --profile spring down

# Parar e remover volumes (limpa dados)
docker-compose --profile spring down -v
```

### Acessar:
- **Frontend**: http://localhost:80
- **Backend API**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health
- **pgAdmin**: http://localhost:5050
  - Email: admin@admin.com
  - Password: admin

---

## Stack 2: Quarkus + MySQL

### Serviços incluídos:
- **backend-quarkus**: Quarkus 3.30.5 (porta 8080)
- **mysql**: MySQL 8.0 (porta 3306)
- **adminer**: Interface de administração MySQL (porta 8081)
- **frontend**: Angular app (porta 80)

### Como rodar:

```bash
# Opção 1: Usando o profile diretamente
docker-compose --profile quarkus up

# Opção 2: Usando o arquivo .env específico (recomendado)
docker-compose --profile quarkus --env-file .env.quarkus up

# Rodar em background
docker-compose --profile quarkus --env-file .env.quarkus up -d

# Ver logs
docker-compose --profile quarkus logs -f

# Parar os serviços
docker-compose --profile quarkus down

# Parar e remover volumes (limpa dados)
docker-compose --profile quarkus down -v
```

### Acessar:
- **Frontend**: http://localhost:80
- **Backend API**: http://localhost:8080
- **Health Check**: http://localhost:8080/q/health
- **Adminer**: http://localhost:8081
  - System: MySQL
  - Server: mysql
  - Username: todouser
  - Password: todopassword
  - Database: tododb

---

## Alternando Entre Stacks

**IMPORTANTE**: Você precisa parar completamente uma stack antes de iniciar a outra, pois ambas usam as mesmas portas (8080 e 80).

### Trocar de Spring para Quarkus:

```bash
# 1. Parar Spring Boot stack
docker-compose --profile spring down

# 2. Iniciar Quarkus stack
docker-compose --profile quarkus --env-file .env.quarkus up -d
```

### Trocar de Quarkus para Spring:

```bash
# 1. Parar Quarkus stack
docker-compose --profile quarkus down

# 2. Iniciar Spring Boot stack
docker-compose --profile spring --env-file .env.spring up -d
```

---

## Configuração de Variáveis de Ambiente

### Arquivos disponíveis:

- **`.env.example`**: Template com todas as variáveis disponíveis
- **`.env.spring`**: Configuração pronta para Spring Boot + PostgreSQL
- **`.env.quarkus`**: Configuração pronta para Quarkus + MySQL
- **`.env`**: Seu arquivo local (git-ignored) - copie de um dos exemplos acima

### Criar seu próprio .env:

```bash
# Para Spring Boot
cp .env.spring .env

# Para Quarkus
cp .env.quarkus .env
```

---

## Comandos Úteis

### Ver serviços rodando:
```bash
docker-compose ps
```

### Ver logs de um serviço específico:
```bash
# Spring Boot
docker-compose --profile spring logs -f backend-spring

# Quarkus
docker-compose --profile quarkus logs -f backend-quarkus
```

### Rebuild das imagens:
```bash
# Spring Boot
docker-compose --profile spring build --no-cache
docker-compose --profile spring up -d

# Quarkus
docker-compose --profile quarkus build --no-cache
docker-compose --profile quarkus up -d
```

### Entrar no container:
```bash
# Spring Boot backend
docker exec -it todo-backend-spring sh

# Quarkus backend
docker exec -it todo-backend-quarkus sh

# PostgreSQL
docker exec -it todo-postgres psql -U postgres -d tododb

# MySQL
docker exec -it todo-mysql mysql -u todouser -p tododb
```

### Limpar tudo (cuidado: remove volumes e dados):
```bash
# Spring Boot
docker-compose --profile spring down -v --remove-orphans

# Quarkus
docker-compose --profile quarkus down -v --remove-orphans
```

---

## Troubleshooting

### Porta já em uso:
```bash
# Verificar o que está usando a porta 8080
netstat -ano | findstr :8080  # Windows
lsof -i :8080                  # Linux/Mac

# Ou altere a porta no arquivo .env:
BACKEND_PORT=8081
```

### Container não inicia:
```bash
# Ver logs detalhados
docker-compose --profile spring logs backend-spring
docker-compose --profile quarkus logs backend-quarkus

# Verificar saúde dos containers
docker-compose --profile spring ps
```

### Banco de dados não conecta:
```bash
# Verificar se o banco está saudável
docker-compose --profile spring exec postgres pg_isready
docker-compose --profile quarkus exec mysql mysqladmin ping

# Ver logs do banco
docker-compose --profile spring logs postgres
docker-compose --profile quarkus logs mysql
```

### Limpar cache do Docker:
```bash
docker system prune -a --volumes
```

---

## Arquitetura dos Profiles

```yaml
services:
  # SPRING PROFILE
  postgres:        profiles: ["spring"]
  pgadmin:         profiles: ["spring"]
  backend-spring:  profiles: ["spring"]

  # QUARKUS PROFILE
  mysql:           profiles: ["quarkus"]
  adminer:         profiles: ["quarkus"]
  backend-quarkus: profiles: ["quarkus"]

  # SHARED (ambos profiles)
  frontend:        profiles: ["spring", "quarkus"]
```

---

## Comparação das Stacks

| Aspecto | Spring Boot | Quarkus |
|---------|-------------|---------|
| **Database** | PostgreSQL 16 | MySQL 8.0 |
| **Admin UI** | pgAdmin (porta 5050) | Adminer (porta 8081) |
| **Health Check** | `/actuator/health` | `/q/health` |
| **Startup Time** | ~30-60s | ~20-40s |
| **Memory Usage** | ~350MB | ~250MB |
| **Framework** | Spring Boot 3.2.5 | Quarkus 3.30.5 |

---

## Desenvolvimento Local

Para desenvolvimento, você pode querer rodar apenas o banco de dados no Docker e o backend localmente:

### Spring Boot local + PostgreSQL no Docker:
```bash
# Iniciar apenas o PostgreSQL
docker-compose --profile spring up postgres -d

# Rodar Spring Boot localmente
cd to-do-app-backend/to-do-app-backend
../../mvnw spring-boot:run -pl to-do-app-spring
```

### Quarkus local + MySQL no Docker:
```bash
# Iniciar apenas o MySQL
docker-compose --profile quarkus up mysql -d

# Rodar Quarkus localmente em dev mode
cd to-do-app-backend/to-do-app-backend
../../mvnw quarkus:dev -pl to-do-app-quarkus
```

---

## Próximos Passos

1. Escolha uma stack para testar
2. Rode com `docker-compose --profile <spring|quarkus> up`
3. Acesse o frontend em http://localhost:80
4. Teste a API em http://localhost:8080
5. Compare o desempenho e comportamento de ambas as implementações

Ambas as stacks implementam a **mesma API REST** e compartilham o **mesmo core de negócio**, provando os princípios da Clean Architecture!
