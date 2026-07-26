# ServeRest Automation
Projeto de automação de testes de API utilizando Java e RestAssured.

## Objetivo
Este projeto foi desenvolvido com fins de estudo para praticar automação de testes em APIs REST utilizando boas práticas de organização de código.

API utilizada:
https://serverest.dev/

## Tecnologias Utilizadas
- Java 17
- RestAssured
- Maven
- JUnit 5

## Estrutura do Projeto
O projeto está organizado da seguinte forma:
```
src
└── main
    └── java                    
    
    test
        └── java
            └── br
                └── com 
                    └── ranieri
                        └── base
                            ├── BaseTest.java
                        └── model
                        └── payload
                        └── services    
                            └── UsuarioService.java
                        └── usuarios
                            └── funcional
                                ├── CadastrarUsuarioAdminSucessoTest.java
                                ├── ListarUsuarioSucessoTest.java  
                                ├── CadastrarUsuarioComumSucessoTest.java
                                ├── ExcluirUsuarioSucessoTest.java
                            └── negativo    
                                ├── CadastrarUsuarioNegativoTest.java    
                                ├── ListarUsuarioErroTest.java   
                        └── utils                          
                            └── DataGenerator.java
```                            
                           
## Como Executar os Testes
Em desenvolvimento.

## Casos de Teste
Em desenvolvimento.