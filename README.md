# Forum Spring: API REST para Sistema de Fórum

O **Forum Spring** é uma aplicação Java desenvolvida com **Spring Boot** que funciona como uma API REST completa para gerenciamento de um sistema de fórum. O sistema oferece funcionalidades de autenticação JWT, gestão de cursos educacionais e tópicos de discussão, proporcionando uma plataforma robusta para comunidades educacionais e de aprendizado.

---

## Funcionalidades Principais

- **Autenticação Segura**: Sistema de login com tokens JWT para acesso protegido aos recursos.
- **Gestão de Cursos**: CRUD completo para criação, listagem, atualização e exclusão de cursos.
- **Sistema de Tópicos**: Criação e gerenciamento de tópicos de discussão vinculados a cursos específicos.
- **Soft Delete**: Exclusão lógica de tópicos mantendo histórico no banco de dados.
- **Paginação Inteligente**: Listagem paginada de cursos e tópicos com ordenação personalizada.
- **Validação Robusta**: Verificação de duplicatas e validação de dados de entrada.
- **Segurança Avançada**: Proteção de rotas com Spring Security e filtros customizados.
- **Informações Detalhadas**: Gestão completa de dados incluindo:
  - Cursos com nome e categoria
  - Tópicos com título, mensagem e data de criação
  - Relacionamento entre tópicos e cursos
  - Status de atividade dos tópicos
- **API RESTful**: Endpoints padronizados seguindo convenções REST.

---

## Tecnologias Utilizadas

- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.5.4
- **Gerenciamento de Dependências**: Maven
- **Banco de Dados**: MySQL
- **ORM**: Spring Data JPA / Hibernate
- **Migração de Dados**: Flyway
- **Segurança**: Spring Security 6 com JWT
- **Validação**: Bean Validation (Hibernate Validator)
- **Utilitários**: Lombok para redução de código boilerplate
- **Arquitetura**: MVC com separação de camadas (Controller, Service, Repository, Domain)

---

## Arquitetura da API

### Endpoints Principais

#### Autenticação
- **POST** `/login` - Autenticação de usuários e geração de token JWT

#### Gestão de Cursos
- **POST** `/courses` - Criar novo curso
- **GET** `/courses` - Listar cursos (paginado)
- **GET** `/courses/{id}` - Obter detalhes de um curso
- **PUT** `/courses/{id}` - Atualizar curso existente
- **DELETE** `/courses/{id}` - Excluir curso

#### Gestão de Tópicos
- **POST** `/topics` - Criar novo tópico
- **GET** `/topics` - Listar tópicos ativos (paginado)
- **GET** `/topics/{id}` - Obter detalhes de um tópico
- **PUT** `/topics/{id}` - Atualizar tópico existente
- **DELETE** `/topics/{id}` - Excluir tópico (soft delete)

### Formato de Autenticação
```json
{
  "email": "usuario@email.com",
  "password": "senha123"
}
```

### Resposta do Login
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

## Estrutura do Projeto

```
src/main/java/com/henr1que/forumspring/
├── ForumSpringApplication.java         # Classe principal Spring Boot
├── controller/
│   ├── AuthenticationController.java  # Controller de autenticação
│   ├── CourseController.java           # Controller de cursos
│   └── TopicController.java            # Controller de tópicos
├── domain/
│   ├── User.java                       # Entidade de usuários
│   ├── Course.java                     # Entidade de cursos
│   └── Topic.java                      # Entidade de tópicos
├── dto/
│   ├── authentication/
│   │   └── AuthenticationData.java     # DTO para autenticação
│   ├── course/
│   │   ├── CoursePostDTO.java          # DTO para criação de cursos
│   │   └── CourseUpdateDTO.java        # DTO para atualização de cursos
│   └── topic/
│       ├── TopicPostDTO.java           # DTO para criação de tópicos
│       ├── TopicUpdateDTO.java         # DTO para atualização de tópicos
│       └── TopicDetailsDTO.java        # DTO para detalhes de tópicos
├── infra/security/
│   ├── SecurityConfigurations.java     # Configurações de segurança
│   ├── SecurityFilter.java             # Filtro de segurança JWT
│   ├── TokenService.java               # Serviço de geração de tokens
│   └── DetailsTokenJWT.java            # DTO para resposta de token
├── repository/
│   ├── UserRepository.java             # Repository de usuários
│   ├── CourseRepository.java           # Repository de cursos
│   └── TopicRepository.java            # Repository de tópicos
└── service/
    ├── AuthenticationService.java      # Serviço de autenticação
    ├── CourseService.java              # Lógica de negócio de cursos
    └── TopicService.java               # Lógica de negócio de tópicos
```

---

## Como Funciona

### Fluxo de Autenticação
1. **Login**: Usuário fornece email e senha
2. **Validação**: Sistema verifica credenciais no banco
3. **Token JWT**: Geração de token com validade de 2 horas
4. **Autorização**: Token deve ser enviado no header `Authorization: Bearer {token}`

### Fluxo de Gerenciamento
1. **Autenticação**: Validação do token JWT em todas as rotas protegidas
2. **Validação**: Verificação de dados de entrada e regras de negócio
3. **Persistência**: Operações no banco de dados MySQL
4. **Resposta**: Retorno dos dados formatados via DTOs

### Regras de Negócio
- Cursos não podem ter nomes duplicados
- Tópicos não podem ter títulos ou mensagens duplicadas
- Tópicos devem estar vinculados a cursos existentes
- Exclusão de tópicos é lógica (soft delete)
- Paginação padrão de 10 itens por página

---

## Configuração e Execução

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6+
- MySQL 8.0+ instalado e configurado
- Conexão com internet para download de dependências

### Configuração do Banco de Dados
1. Crie um banco MySQL chamado conforme configuração
2. Configure as variáveis de ambiente:
   ```bash
   export DB_HOST=localhost:3306
   export DB_NAME=forum_spring
   export DB_USER=seu_usuario
   export DB_PASSWORD=sua_senha
   export JWT_SECRET=minha_chave_secreta_super_segura
   ```

### Execução
```bash
# Clone o repositório
git clone <url-do-repositorio>
cd forum-spring

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

---

## Modelo de Dados

### Entidade User
```java
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    
    // Implementação UserDetails para Spring Security
}
```

### Entidade Course
```java
@Entity
@Table(name = "courses")
public class Course {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String category;
    
    @OneToMany(mappedBy = "course")
    private List<Topic> topics;
}
```

### Entidade Topic
```java
@Entity
@Table(name = "topics")
public class Topic {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String message;
    private LocalDateTime created_at;
    private boolean status;
    
    @ManyToOne
    private Course course;
}
```

### Relacionamentos
- **Um para Muitos**: Um curso pode ter vários tópicos
- **Muitos para Um**: Vários tópicos pertencem a um curso
- **Cascade**: Operações em cascata para manter integridade

---

## Funcionalidades Detalhadas

### 1. Sistema de Autenticação
- Login com email e senha
- Geração de token JWT com expiração
- Validação automática de tokens em rotas protegidas
- Implementação de UserDetails do Spring Security

### 2. Gestão de Cursos
- Criação com validação de nome único
- Listagem paginada com ordenação por nome
- Atualização parcial de dados
- Exclusão física do banco de dados
- Busca por ID com tratamento de erros

### 3. Gestão de Tópicos
- Criação vinculada a cursos existentes
- Validação de título e mensagem únicos
- Listagem apenas de tópicos ativos
- Soft delete mantendo histórico
- Timestamp automático de criação

### 4. Validações e Tratamento de Erros
- Bean Validation em DTOs
- Verificação de duplicatas
- Tratamento de entidades não encontradas
- Responses HTTP padronizados

---

## Segurança

### Configurações Implementadas
- **CSRF Desabilitado**: Para APIs REST stateless
- **Session Stateless**: Não mantém sessão no servidor
- **JWT Filter**: Validação automática de tokens
- **Password Encoding**: BCrypt para hash de senhas
- **Route Protection**: Apenas `/login` é público

### Exemplo de Configuração
```java
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/login").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
```

---

## Migrações do Banco

### V1: Criação das Tabelas
```sql
CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE courses(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE topics(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topics_course_id FOREIGN KEY(course_id) REFERENCES courses(id)
);
```

---

## Dependências Principais

| Dependência | Versão | Propósito |
|-------------|--------|-----------|
| Spring Boot Starter Web | 3.5.4 | Framework web e REST |
| Spring Boot Starter Data JPA | 3.5.4 | ORM e acesso a dados |
| Spring Boot Starter Security | 3.5.4 | Segurança e autenticação |
| Spring Boot Starter Validation | 3.5.4 | Validação de dados |
| MySQL Connector J | Runtime | Driver do MySQL |
| Flyway Core | 3.5.4 | Migração de banco de dados |
| Flyway MySQL | 3.5.4 | Suporte específico para MySQL |
| Auth0 Java JWT | 4.5.0 | Geração e validação de tokens JWT |
| Lombok | Optional | Redução de boilerplate |

---

## Exemplos de Uso

### Autenticação
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"email": "user@email.com", "password": "senha123"}'
```

### Criar Curso
```bash
curl -X POST http://localhost:8080/courses \
  -H "Authorization: Bearer {seu_token}" \
  -H "Content-Type: application/json" \
  -d '{"name": "Spring Boot", "category": "Desenvolvimento"}'
```

### Criar Tópico
```bash
curl -X POST http://localhost:8080/topics \
  -H "Authorization: Bearer {seu_token}" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Como configurar Spring Security?",
    "message": "Estou com dúvidas sobre a configuração...",
    "courseId": 1
  }'
```

### Listar Tópicos
```bash
curl -X GET "http://localhost:8080/topics?page=0&size=5&sort=title" \
  -H "Authorization: Bearer {seu_token}"
```

---

## Tratamento de Erros

### Cenários Cobertos
- **401 Unauthorized**: Token inválido ou expirado
- **403 Forbidden**: Acesso negado a recurso protegido
- **404 Not Found**: Entidade não encontrada
- **400 Bad Request**: Dados de entrada inválidos
- **409 Conflict**: Violação de regra de duplicata

### Exemplo de Resposta de Erro
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Course not found",
  "path": "/courses/999"
}
```

---

## Queries Customizadas

### Busca de Tópicos Ativos
```java
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAllByStatusTrue(Pageable pageable);
    boolean existsByTitle(String title);
    boolean existsByMessage(String message);
}
```

### Busca de Curso por Nome
```java
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
}
```

### Busca de Usuário por Email
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
```

---

## Padrões e Arquitetura

### Clean Architecture
- **Controller**: Camada de apresentação HTTP
- **Service**: Lógica de negócio e regras
- **Repository**: Acesso a dados e persistência
- **Domain**: Entidades e modelos de domínio
- **DTO**: Transferência de dados entre camadas

### Padrões Utilizados
- **Repository Pattern**: Abstração do acesso a dados
- **Data Transfer Object**: Separação entre entidades e DTOs
- **Dependency Injection**: Injeção via Spring Boot
- **Command Pattern**: Estruturação de operações CRUD
- **Filter Pattern**: Filtros de segurança customizados
---

## Desenvolvido por

**Henrique Oliveira dos Santos**  
[LinkedIn](https://www.linkedin.com/in/dev-henriqueo-santos/)

---
