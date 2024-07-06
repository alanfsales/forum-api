# Fórum API (FórumHub)
Este projeto é um desafio proposto no programa ONE - Oracle Next Education. Um programa de ensino realizado em parceria da Alura com a Oracle.

### Descrição do desafio
O objetivo é implementar uma API REST de um fórum. Um espaço onde podemos trocar conhecimentos, criar tópicos para solucionar nossas dúvidas e também utilizar para tirar as dúvidas de outras pessoas.</br>
Vamos nos concentrar especificamente nos tópicos. 

- Implementar rotas seguindo as melhores práticas do modelo REST;
- Validações realizadas segundo as regras de negócio;
- Implementação de uma base de dados relacional para a persistência da informação;
- Serviço de autenticação/autorização para restringir o acesso à informação.

### Funcionalidades
- Autenticação: gerar e validar um token JWT
- Criar um novo tópico;
- Mostrar todos os tópicos criados;
- Mostrar um tópico específico;
- Atualizar um tópico;
- Excluir um tópico.

### Tecnologias utilizadas
- Java 17
- Maven
- Spring Boot
- Spring Data JPA
- Spring Security (Autenticação )
- SpringDoc (Documentação com Swagger)
- Banco de dados Mysql
- Flyway Migration
- Auth0
- Lombok

### Resultado

![Documentação](https://github.com/alanfsales/assets/blob/main/F%C3%B3rum%20API/documenta%C3%A7%C3%A3o.png)

![Login](https://github.com/alanfsales/assets/blob/main/F%C3%B3rum%20API/login.png)

![Autenticação](https://github.com/alanfsales/assets/blob/main/F%C3%B3rum%20API/autentica%C3%A7%C3%A3o.png)

![Cadastro](https://github.com/alanfsales/assets/blob/main/F%C3%B3rum%20API/cadastro%20de%20t%C3%B3pico.png)

![Pacotes](https://github.com/alanfsales/assets/blob/main/F%C3%B3rum%20API/pacotes.png)

### Instalação
Para executar a aplicação localmente, siga estas etapas:
1. Clone este repositório.
2. Certifique-se de ter a JDK do Java 17 ou superior instalado.
3. Certifique-se de ter o MySQL 8 ou superior instalado.
4. Importe o projeto utilizando o Maven em uma IDE de sua preferência. 
5. Configure o MySQL atualizando as configurações no arquivo application.properties.
6. Execute a classe ForumApiApplication.java.
7. Entre na documentação acessando http://localhost:8080/swagger-ui/index.html no navegador.
8. Gere um token no autenticacao-controller /login passando no corpo da requisição o seguinte json:</br>
    {
    "email": "teste@email.com",
    "senha": "123456"
    }</br>
> Atenção: No projeto existem migrations do flyway que além de criar as tabelas cadastra alguns dados do banco pra conseguir iniciar os teste.




