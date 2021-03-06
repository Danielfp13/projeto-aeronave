# Sobre o projeto
Projeto Aeronave foi um teste proposto pela Sonda IT, ele consiste em um crud de Aeronaves, pesquisa por mais de um campo e relatório, no back-end foi desenvolvido uma API com Spring Boot, e no front-ent foi utilizado o angular 11 com angular material.

## Layout web
![Web 1](https://github.com/Danielfp13/projeto-aeronave/blob/main/assents/imagens/cadastro.jpg)

![Web 2](https://github.com/Danielfp13/projeto-aeronave/blob/main/assents/imagens/relatorio.jpg)

![Web 2](https://github.com/Danielfp13/projeto-aeronave/blob/main/assents/imagens/listagem.jpg)

## Arquitetura utilizada
No desenvolvimento da aplicação foi utiliza a arquitetura mvc, onde o sistema foi divido em camadas.
A aplicação angular se comunica com os controladores rest na camada de controller, ele envia as requisições para a camada de serviço que é responsável pelas regras de negócio ela  faz uso da camada de repository responsável pelo acesso ao banco de dados. 
A camada de domínio é responsável por fazer o mapeamento objeto relacional com o banco de dados.
A camada dto(data transfer object) é um padrão que foi utilizado para criar objetos simples, trafegar dados customizados pela rede, e fazer as validações nos verbos HTTP POST e PUT usando o bean validation.

![Arquitetura 1](https://github.com/Danielfp13/projeto-aeronave/blob/main/assents/imagens/arquitetura.jpg)

## EndPoints
### Esse endponits são os endpoints da api
![Arquitetura 1](https://github.com/Danielfp13/projeto-aeronave/blob/main/assents/imagens/endpoint.jpg)

### Ele podem ser testados no postman com a collection que esta no link abaixo
https://github.com/Danielfp13/projeto-aeronave/tree/main/Collection-para-teste-no-postman

## Modelo conceitual
![Modelo Conceitual](https://github.com/Danielfp13/projeto-aeronave/blob/main/assents/imagens/classe.jpg)

## Modelo de dados
![Modelo Conceitual](https://github.com/Danielfp13/projeto-aeronave/blob/main/assents/imagens/modelo-de-dados.jpg)

# Tecnologias utilizadas
## Back end

- Java 11
- Spring Boot 2.4.10
- Spring Data JPA
- Flyway
- Lombok
- Swagger
- Maven
## Front end

- HTML / CSS / TypeScript
- Angular 11
- Angular Material
## Banco de dados

- Postgresql 13

# Como executar o projeto

## Criar banco de dados aeronave

 criar tabela aeronave
 https://github.com/Danielfp13/projeto-aeronave/tree/main/Banco-Dados
 
## Back end

Pré-requisitos: Java 11

```bash
# clonar repositório
 git clone https://github.com/Danielfp13/projeto-aeronave
```
## Abrir back-end na IDE

 -Abrir STS (Spring Tool Suite) ou outra IDE
 vá em  file -> import -> existing maven projects >next
 -Escolha a pasta back-end-> finish
 
## Configurar banco de dados

#na pasta src/main/resources
modifique o arquivo application-dev.properties com as configurações do seu banco de dados
-spring.datasource.url=jdbc:postgresql://localhost:5432/aeronave
-spring.datasource.username=postgres
-spring.datasource.password=123

# Executar o projeto
Clique com o botão direito do mouse no projeto-> Run as -> Spring Boot App.

# Front-end 
Pré-requisitos: npm / angular cli 11

```bash
## Entrar na pasta front-end
cd front-end

## instalar dependências
npm install

## Excutar o projeto
ng serve --open

```

# Autor
Daniel Fernando Pereira
https://www.linkedin.com/in/daniel-pereira-24757a161/

