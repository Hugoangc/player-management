#  Player Management (Gerenciador de Jogadores)

Sistema de cadastro de jogadores desenvolvido com **Spring Boot**, focado na manipulação de dados externos e arquitetura de serviços. A aplicação permite registrar usuários e atribuir automaticamente um **codinome de herói** (Vingadores ou Liga da Justiça), consumindo APIs externas com formatos distintos (JSON e XML).

##  Funcionalidades

* **Cadastro de Jogadores:** Interface gráfica para inserção de nome, e-mail e telefone.
* **Alocação Automática de Codinomes:**
    * Ao escolher um grupo, o sistema busca uma lista de codinomes disponíveis em uma fonte externa.
    * **Vingadores:** Consome API em formato **JSON**.
    * **Liga da Justiça:** Consome API em formato **XML**.
* **Validação de Disponibilidade:** O sistema garante que o codinome sorteado ainda não esteja em uso no banco de dados local.
* **Listagem de Jogadores:** Visualização tabular de todos os cadastrados.
* **Persistência:** Dados salvos em banco de dados em memória (H2).

##  Tecnologias Utilizadas

* **Java 17+** (Uso de Records e Switch Expressions)
* **Spring Boot 3**
* **Spring MVC & Thymeleaf** (Frontend server-side)
* **Spring JDBC Client** (Para persistência SQL nativa e fluida)
* **Spring RestClient** (Para consumo de APIs externas)
* **Jackson** (Para desserialização de JSON e XML)
* **H2 Database** (Banco em memória)
* **Bootstrap 4.3** (Estilização)

##  Arquitetura e Design Patterns

Este projeto implementa conceitos importantes de engenharia de software:

### 1. Factory Pattern
Para desacoplar a lógica de negócio da fonte de dados dos codinomes, foi criada a `CodenameRepositoryFactory`. Ela decide em tempo de execução qual repositório instanciar (Vingadores ou Liga da Justiça) com base na escolha do usuário.

### 2. Tratamento de Formatos Heterogêneos
O sistema demonstra robustez ao lidar com diferentes formatos de resposta:
* **JSON:** Mapeado via `ObjectMapper` padrão.
* **XML:** Mapeado via `XmlMapper` e anotações específicas como `@JacksonXmlRootElement`.

### 3. Validação Customizada
O sistema trata exceções de negócio, como a falta de codinomes disponíveis (`CodenameGroupUnavailableException`), retornando feedback visual ao usuário no formulário.

##  Testes

O projeto conta com testes de integração (`PlayerManagementApplicationIT`) que validam o fluxo completo:
1.  Envio do formulário (POST).
2.  Redirecionamento.
3.  Verificação se o dado foi persistido e exibido na listagem.
