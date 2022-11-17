# Ecommerce

## Sobre o projeto

Ecommerce é uma aplicação web voltada para o comércio de venda de produtos. O projeto possui integração com a api viacep da qual é informado um cep válido e retornado um endereço. Além disso, foi implementado o CRUD de Customers e cadastro/consulta de Endereços.


## Tecnologias utilizadas

- [Java 17]
- [Spring 2.7.5]
- [MySql]
- [H2]
- [Postman]


## Outros 

- Consumo da api viacep através de WebClient
- CRUD completo de Customers
- Gerenciamento de exceções com ControllerAdvice
- Validação de campos com Validation
- Anotação customizada para validação se o email do cliente já existe 


## Como executar

- clonar o repositório
- instalar o mysql e criar o usuário: root e senha: 123
- criar um banco de dados com nome: ecommerce (não é obrigatório, pois foi configurado no arquivo application-dev.properties para fazer a criação automática da base de dados e das tabelas)
- ao iniciar a aplicação, o serviço estará rodando através do tomcat na porta 8080
- importar a collection "Challenge Ecommerce" no postman que esta localizada na pasta postman na raiz do projeto
- pronto, agora é só consumir os endpoints.


## Endpoints

As collections estão bem definidas em duas pastas
<br/>
   - CEP: para consumir a api viacep e retornar um endereço -> GET http://localhost:8080/cep/{codigoCep}
    
   - Customer: para CRUD de customers e outros recursos envolvendo seus endereços.
      - Insert address by email -> 
            POST http://localhost:8080/customers/{email}/addresses
      
      - Find address by email -> 
            GET http://localhost:8080/customers/{email}/addresses
      
      - Find all customer paged -> 
            GET http://localhost:8080/customers
      
      - Find by id -> 
            GET http://localhost:8080/customers/{id}
      
      - Insert -> 
            POST http://localhost:8080/customers
      
      - Update -> 
            UPDATE http://localhost:8080/customers/{id}
      
      - Delete -> 
            DELETE  http://localhost:8080/customers/{id}

## Autor
Alan Pacheco
