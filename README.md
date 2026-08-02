# ServeRest Automation

Projeto de automação de testes de API utilizando Java e RestAssured, desenvolvido com foco em boas práticas de organização, reutilização de código e testes independentes de dados previamente existentes na base.

API utilizada:
https://serverest.dev/

---

## Objetivo

Este projeto foi desenvolvido com fins de estudo para praticar automação de testes em APIs REST utilizando:

- Organização em camadas
- Reutilização de código
- DTOs para serialização de requisições
- Services para preparação dos cenários
- Geração dinâmica de dados de teste
- Testes independentes da base de dados

---

## Tecnologias Utilizadas

- Java 17
- Maven
- RestAssured
- JUnit 4
- Jackson
- JavaFaker

---

## Estrutura do Projeto

```
src
├── main
│   └── java
│
└── test
    └── java
        └── br
            └── com
                └── ranieri
                    ├── base
                    │   └── BaseTest.java
                    │
                    ├── dto
                    │   └── Usuario.java
                    │
                    ├── services
                    │   └── UsuarioService.java
                    │
                    ├── usuarios
                    │   ├── funcional
                    │   └── negativo
                    │
                    ├── login
                    │   ├── funcional
                    │   └── negativo
                    │
                    └── utils
                        └── DataGenerator.java
```

---

## Funcionalidades Automatizadas

### Usuários

- Cadastro de usuário comum
- Cadastro de administrador
- Consulta de usuário
- Atualização de usuário
- Exclusão de usuário
- Cadastro via PUT
- Validações negativas

### Login

- Login com usuário comum
- Login com administrador
- Login com email inválido
- Login com senha inválida
- Login com email e senha inválidos

---

## Como Executar

```bash
mvn test
```

Executar uma classe específica:

```bash
mvn test -Dtest=NomeDaClasse
```

---

## Status

🚧 Projeto em desenvolvimento.

Novos endpoints da ServeRest serão automatizados conforme evolução dos estudos.