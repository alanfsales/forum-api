# Fórum API (FórumHub)
Este projeto foi desenvolvido por mim, como desafio parte do programa ONE - Oracle Next Education, uma parceria entre a Alura e a Oracle. <br/>
Trata-se de uma API REST para um fórum de discussão, onde usuários podem trocar conhecimentos, 
criar tópicos para dúvidas e colaborar com dúvidas de outras pessoas.

### Descrição do desafio
O FórumHub foi projetado para ser um espaço de discussão, com funcionalidades que permite gerenciar tópicos de forma organizada e segura. 
Este sistema poderia ser aplicado em ambientes reais, como plataformas de aprendizado, suporte técnico ou comunidades online.
No desenvolvimento, implementei todas as funcionalidades, incluindo CRUDs para tópicos, usuários, cursos e respostas.

### Funcionalidades
- Autenticação com geração e validação de tokens JWT.
- Criação, visualização, atualização e exclusão de:
    - Usuários
    - Cursos
    - Tópicos
    - Respostas

### Tecnologias utilizadas
- **Java 17:** Linguagem de programação principal do projeto.
- **Maven:** Gerenciamento de dependências e construção do projeto.
- **Spring Boot:** Configuração e estruturação do projeto com base no framework Spring.
- **Spring Data JPA:** Simplificação das operações com banco de dados relacional.
- **Spring Security e Auth0:** Implementação de autenticação segura e geração de tokens JWT.
- **Bcrypt:** Criptografia de senhas dos usuários para maior segurança.
- **SpringDoc e Swagger:** Documentação das rotas da API para fácil integração.
- **MySQL:** Persistência de dados em banco de dados relacional.
- **Flyway Migration:** Gerenciamento e versionamento de migrações de banco de dados.
- **Lombok:** Redução de código boilerplate.

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
> Atenção: No projeto existem migrations do flyway que além de criar as tabelas cadastra alguns dados no banco pra conseguir iniciar os teste.

### Próximos passos
- Telas Básicas (HTML, CSS, JS)




