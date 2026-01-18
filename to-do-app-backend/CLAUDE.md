# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a To-Do application backend built with Java 21, following Clean Architecture principles. The project demonstrates Clean Architecture by providing **two different infrastructure implementations** (Spring Boot + PostgreSQL and Quarkus + MySQL) sharing the same domain core. This proves that the business logic is completely independent of frameworks and databases.

The project is structured as a multi-module Maven project with clear separation between domain logic and infrastructure concerns.

## Module Architecture

The project implements Clean Architecture with one core module and two alternative infrastructure modules:

### to-do-app-core
The core module contains pure business logic with no framework dependencies:
- **Domain Entities**: `Task` entity with value objects (`Title`, `Description`) that enforce business rules
- **Use Cases**: Business logic interfaces (e.g., `ICreateTaskUseCase`, `IGetTaskUseCase`) and implementations
- **Ports**: Repository interfaces that define contracts for data access (e.g., `ITaskRepository`)
- **No Spring dependencies**: This module is framework-agnostic

### to-do-app-spring (Infrastructure - Spring Boot)
The Spring Boot infrastructure module with PostgreSQL:
- **Framework**: Spring Boot 3.2.5
- **Database**: PostgreSQL 16
- **Adapters**: `TaskRepositoryImpl` implements `ITaskRepository`
- **REST**: Spring MVC controllers with `@RestController`
- **DTOs**: Request/Response records for API contract
- **Mappers**: Static mapper classes for conversions
- **Persistence**: JPA entities (`TaskDbEntity`) and Spring Data repositories
- **Configuration**: `@Configuration` beans for use case wiring (`TaskConfig`)
- **Main**: Spring Boot application entry point (`ToDoAppInfraApplication`)

### to-do-app-quarkus (Infrastructure - Quarkus)
The Quarkus infrastructure module with MySQL:
- **Framework**: Quarkus 3.30.5
- **Database**: MySQL 8.0
- **Adapters**: `TaskRepositoryAdapter` implements `ITaskRepository`
- **REST**: JAX-RS resources with `@Path`
- **DTOs**: Identical Request/Response records (duplicated for module independence)
- **Mappers**: Static mapper classes (same pattern as Spring)
- **Persistence**: JPA entities (`TaskEntity`) with EntityManager + JPQL
- **Configuration**: CDI `@Produces` methods for use case wiring (`TaskProducer`)
- **Main**: Quarkus application (no explicit main class needed)

## Dependency Flow

The architecture follows the Dependency Rule:
1. Core module has NO dependencies on infrastructure
2. Infrastructure module depends on core module
3. Use cases are wired via Spring configuration beans in `TaskConfig`
4. Controllers inject use case interfaces, not implementations

## Build & Run Commands

### Build the project
```bash
./mvnw clean install
```

### Build without tests
```bash
./mvnw clean install -DskipTests
```

### Run the application
```bash
cd to-do-app-backend/to-do-app-infra
../../mvnw spring-boot:run
```

### Run tests
```bash
./mvnw test
```

### Run tests for a specific module
```bash
cd to-do-app-backend/to-do-app-core
../../mvnw test
```

### Build Docker image
```bash
cd to-do-app-backend
docker build -t todo-app-backend .
```

### Run with Docker
```bash
docker run -p 8080:8080 \
  -e DB_HOST=localhost \
  -e DB_PORT=5432 \
  -e DB_NAME=tododb \
  -e DB_USER=postgres \
  -e DB_PASSWORD=postgres \
  todo-app-backend
```

## Database Configuration

The application uses PostgreSQL with the following default configuration (can be overridden via environment variables):
- **Database**: PostgreSQL
- **Default URL**: `jdbc:postgresql://localhost:5432/tododb`
- **Default credentials**: postgres/postgres
- **DDL mode**: update (configurable via `DDL_AUTO` env var)

Environment variables for database configuration:
- `DB_HOST`: Database host (default: localhost)
- `DB_PORT`: Database port (default: 5432)
- `DB_NAME`: Database name (default: tododb)
- `DB_USER`: Database user (default: postgres)
- `DB_PASSWORD`: Database password (default: postgres)

## Domain Model Rules

### Value Objects
- **Title**: Cannot be null/blank, max 100 characters
- **Description**: Cannot be null, max 500 characters
- Value objects validate input in their constructors

### Task Entity
- Always created with status `BACKLOG`
- Uses UUID for identification
- Title and Description are required at construction

## Adding New Features

When adding new functionality, follow the Clean Architecture pattern:

1. **Start in Core Module** (`to-do-app-core`):
   - Define domain entities/value objects in `domain/entities` or `domain/vo`
   - Create use case interface in `usecases/I[Feature]UseCase.java`
   - Implement use case in `usecases/impl/[Feature]UseCaseImpl.java`
   - Define repository port in `ports/I[Feature]Repository.java` if data access is needed

2. **Implement in Infra Module** (`to-do-app-infra`):
   - Create JPA entity in `task/persistence/entities/[Feature]DbEntity.java`
   - Create Spring Data repository in `task/persistence/repositories/ISpringData[Feature]Repository.java`
   - Implement port adapter in `task/persistence/adapters/[Feature]RepositoryImpl.java`
   - Create DTOs in `task/rest/dto/input` and `task/rest/dto/output`
   - Create mappers for conversions in `task/mappers/[Feature]Mapper.java`
   - Add controller endpoint in `task/rest/controllers/impl/TaskController.java`
   - Wire use case bean in `task/configuration/TaskConfig.java`

3. **Mapper Pattern**:
   - Use static mapper classes (not Spring beans) for conversions
   - Naming: `TaskRequestMapper.toTask()`, `TaskResponseMapper.toDto()`, `TaskMapper.toTaskDbEntity()`

## API Endpoints

Base path: `/api/task`

Current endpoints:
- `POST /api/task/create`: Create a new task
- `GET /api/task/{id}`: Get task by ID

Health check: `/actuator/health`

## Quarkus Module (to-do-app-quarkus)

### Run Quarkus in Dev Mode
```bash
cd to-do-app-backend/to-do-app-quarkus
../../mvnw quarkus:dev
```

### Build Quarkus
```bash
cd to-do-app-backend/to-do-app-quarkus
../../mvnw clean package
```

### Run Quarkus JAR
```bash
cd to-do-app-backend/to-do-app-quarkus
java -jar target/quarkus-app/quarkus-run.jar
```

### Quarkus Database Configuration

MySQL with the following default configuration (can be overridden via environment variables):
- **Database**: MySQL 8.0
- **Default URL**: `jdbc:mysql://localhost:3306/tododb`
- **Default credentials**: root/password
- **DDL mode**: update (configurable via `DDL_AUTO` env var)

Environment variables:
- `DB_HOST`: Database host (default: localhost)
- `DB_PORT`: Database port (default: 3306)
- `DB_NAME`: Database name (default: tododb)
- `DB_USER`: Database user (default: root)
- `DB_PASSWORD`: Database password (default: password)

## Switching Between Implementations

Use Docker Compose profiles to choose which implementation to run:

### Run Spring Boot + PostgreSQL
```bash
cd to-do-app-backend
docker-compose --profile spring up
```

### Run Quarkus + MySQL
```bash
cd to-do-app-backend
docker-compose --profile quarkus up
```

### Stop and Clean Up
```bash
docker-compose down -v
```

**Both implementations**:
- Expose the same REST API on port 8080
- Use identical domain logic from `to-do-app-core`
- Support the same API endpoints and contracts

## Framework Comparison

| Aspect | Spring Boot | Quarkus |
|--------|------------|---------|
| **Database** | PostgreSQL | MySQL |
| **DI** | `@Component`, `@Bean` | `@ApplicationScoped`, `@Produces` |
| **REST** | Spring MVC (`@RestController`) | JAX-RS (`@Path`) |
| **Path Variables** | `@PathVariable` | `@PathParam` |
| **Response Type** | `ResponseEntity<T>` | `Response` |
| **Exception Handling** | `@RestControllerAdvice` | `@Provider` + `ExceptionMapper` |
| **Persistence** | Spring Data JPA | EntityManager + JPQL |
| **Auditing** | `@EnableJpaAuditing` | `@PrePersist`/`@PreUpdate` |

## Working Directory

The Maven wrapper (`mvnw`) is located at `to-do-app-backend/mvnw`. When running Maven commands, ensure you're in the correct directory or use relative paths.
