# UniReport API

UniReport é um sistema de backend desenvolvido em Java com Spring Boot para gerenciamento de ocorrências de manutenção em um ambiente universitário ou institucional. O sistema permite o registro, consulta e atualização de ocorrências.

Este projeto inicialmente foi desenvolvido com Node.js, que serviu como meu projeto de conclusão para o curso de Análise e Desenvolvimento de Sistemas (ADS). A versão em Java com Spring Boot é uma reimaginação e evolução da ideia original, focada em explorar as capacidades do ecossistema Spring e aprimorar a arquitetura da aplicação.

---

## 🎯 Objetivo Geral

Desenvolver um sistema de gerenciamento de ocorrências onde os usuários da comunidade acadêmica (alunos, professores e funcionários) possam reportar problemas encontrados no campus que necessitam de manutenção e acompanhar o trâmite dentro da instituição. Dessa forma, os funcionários do setor responsável terão um maior controle sobre as demandas, otimizando o repasse para a área de resolução e garantindo maior eficácia na conclusão e gestão da ocorrência, proporcionando um ambiente mais funcional e agradável.

---

## 📝 Descrição do Sistema

O sistema de gerenciamento de ocorrências UniReport é uma plataforma para reportar, listar e organizar problemas de manutenção encontrados no campus universitário.

* **Usuários Comuns (Alunos, Professores, Visitantes):** Podem reportar problemas ou soluções e acompanhar o andamento das ocorrências.
* **Administradores do Sistema:** Recebem as demandas dos usuários, fornecem feedback sobre os procedimentos e soluções, e podem emitir relatórios para auxiliar na gestão diária.

O sistema visa otimizar a comunicação e a resolução de problemas de manutenção.

---

## ✨ Funcionalidades Principais

### Gerenciamento de Usuários
* Registro de novos usuários com papéis (roles): `ADMIN`, `USER`.
* Autenticação de usuários via email e senha, retornando um token JWT.
* Consulta de usuários (protegido, requer autenticação).

### Gerenciamento de Ocorrências
* Criação de novas ocorrências associadas a uma localização, incluindo descrição do problema e imagens (URLs).
* Consulta de todas as ocorrências ou de uma ocorrência específica por ID.
* Atualização do status e resolução de ocorrências (protegido, requer autenticação e registra qual usuário atualizou).

### Gerenciamento de Localizações
* Criação de novas localizações (bloco, sala, QR Code).
* Consulta de todas as localizações ou de uma localização específica por ID, incluindo um sumário das ocorrências associadas.

### Segurança
* Autenticação baseada em Token JWT.
* Autorização baseada em papéis (`roles`) para acesso a endpoints específicos.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.4.3** (incluindo Spring Web, Spring Data JPA, Spring Security)
* **Maven** (Gerenciador de dependências)
* **JSON Web Tokens (JWT)** para autenticação (via biblioteca `com.auth0/java-jwt`)
* **H2 Database** (Banco de dados em memória para perfil de teste)
* **Hibernate** (Implementação JPA)
* **Bean Validation** (Validação de dados de entrada)

---

## 📡 Endpoints da API (Exemplos)

A seguir, alguns dos principais endpoints disponíveis.

### Autenticação

* `POST /auth/login`
    * **Descrição**: Autentica um usuário e retorna um token JWT.
    * **Corpo da Requisição (Exemplo)**:
        ```json
        {
            "email": "admin@unireport.com",
            "password": "password123"
        }
        ```

### Ocorrências

* `GET /ocorrencias`
    * **Descrição**: Retorna todas as ocorrências (requer autenticação).
* `GET /ocorrencias/{id}`
    * **Descrição**: Retorna uma ocorrência específica pelo ID (público, conforme configuração de segurança).
* `POST /ocorrencias`
    * **Descrição**: Cria uma nova ocorrência (público, conforme configuração de segurança).
    * **Corpo da Requisição (Exemplo)**:
        ```json
        {
            "localizacao": { "id": 1 },
            "problemas": "Descrição detalhada do problema encontrado.",
            "imagens": ["endereço_da_imagem"]
        }
        ```
* `PUT /ocorrencias/{id}`
    * **Descrição**: Atualiza o status e/ou resolução de uma ocorrência (requer autenticação).
    * **Corpo da Requisição (Exemplo)**:
        ```json
        {
            "status": true,
            "resolucao": "Problema resolvido pela equipe de manutenção."
        }
        ```

### Localizações

* `GET /localizacao`
    * **Descrição**: Retorna todas as localizações (requer autenticação).
* `GET /localizacao/{id}`
    * **Descrição**: Retorna uma localização específica pelo ID (requer autenticação).
* `POST /localizacao`
    * **Descrição**: Cria uma nova localização (requer autenticação).
    * **Corpo da Requisição (Exemplo)**:
        ```json
        {
            "bloco": "C",
            "sala": "101"
        }
        ```

### Usuários

* `GET /users`
    * **Descrição**: Retorna todos os usuários (requer autenticação).
* `GET /users/{id}`
    * **Descrição**: Retorna um usuário específico pelo ID (requer autenticação).

**Nota**: Para endpoints que requerem autenticação, inclua o token JWT no header `Authorization`:
`Authorization: Bearer SEU_TOKEN_JWT`

---

## 🏛️ Estrutura do Projeto

O projeto segue uma estrutura padrão para aplicações Spring Boot:

* `src/main/java/com/mrad/UniReport/`
    * `config/`: Configurações da aplicação (ex: `TestConfig.java` para dados iniciais).
    * `entities/`: Entidades JPA que modelam o domínio (User, Ocorrencia, Localizacao) e seus DTOs.
    * `repositories/`: Interfaces Spring Data JPA para acesso ao banco de dados.
    * `resources/`: Controllers REST que expõem os endpoints da API.
    * `security/`: Classes relacionadas à configuração de segurança e manipulação de JWT.
    * `services/`: Camada de serviço contendo a lógica de negócios.
    * `UniReportApplication.java`: Classe principal da aplicação Spring Boot.
* `src/main/resources/`
    * `application.properties`: Configurações principais da aplicação.
    * `application-test.properties`: Configurações específicas para o perfil `test`.
* `src/test/java/`: Testes da aplicação.

---
