# Projeto Gemini-Agenda 🤖✨

## Descrição

Este é um projeto de API REST construída com **Spring Boot** que utiliza a inteligência artificial do **Google Gemini** para criar e organizar uma agenda de tarefas de forma inteligente.

A API recebe um conjunto de tarefas e retorna um cronograma otimizado, que é então **persistido em um banco de dados PostgreSQL** para consultas futuras.

---

## Tecnologias Utilizadas

-   **Backend:**
    -   Java 21
    -   Spring Boot 3
    -   Spring Data JPA (Hibernate)
    -   Maven
-   **Inteligência Artificial:**
    -   Google Gemini API
-   **Banco de Dados:**
    -   PostgreSQL
    -   Docker & Docker Compose

---

## Como Executar o Projeto

Siga os passos abaixo para configurar e executar a aplicação e sua infraestrutura localmente.

### Pré-requisitos

-   Java (JDK) 21 ou superior
-   Apache Maven 3.8 ou superior
-   Docker e Docker Compose

### Passos para Configuração

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/ProjetoGemini.git](https://github.com/seu-usuario/ProjetoGemini.git)
    cd ProjetoGemini
    ```

2.  **Inicie o Banco de Dados com Docker:**
    O projeto inclui um arquivo `docker-compose.yml` para subir um container PostgreSQL com todas as configurações necessárias. Execute o comando na raiz do projeto:
    ```bash
    docker-compose up -d
    ```
    Isso iniciará o banco de dados em segundo plano na porta `5435`.

3.  **⚠️ Configure as Variáveis de Ambiente (Passo Obrigatório)**

    Este projeto precisa de uma chave de API do Google Gemini para funcionar. Crie seu arquivo de configuração local a partir do modelo de exemplo:

    -   **Copie o arquivo de exemplo:**
        ```bash
        cp src/main/resources/application.properties.example src/main/resources/application.properties
        ```
    -   **Adicione sua chave:** Abra o novo arquivo `src/main/resources/application.properties` e substitua o placeholder `COLOQUE_SUA_CHAVE_DE_API_DO_GEMINI_AQUI` pela sua chave real. As credenciais do banco já estão corretas para o ambiente Docker.

4.  **Execute a Aplicação:**
    Use o Maven para compilar e iniciar o servidor Spring Boot.
    ```bash
    mvn spring-boot:run
    ```
    A API estará rodando em `http://localhost:8084`.

---

## Endpoints da API

### 1. Criar e Organizar Agenda

Salva uma nova agenda no banco de dados após processá-la com a IA.

-   **URL:** `/api/agenda`
-   **Método:** `POST`
-   **Corpo da Requisição (Body):**
    ```json
    {
        "dataInicio": "17/09/2025",
        "dataFim": "25/09/2025",
        "descricao": "...",
        "tarefas": [ { "nome": "...", ... } ]
    }
    ```

### 2. Buscar Agendas Salvas

Busca agendas no banco de dados onde o resultado gerado pela IA contém uma palavra-chave específica.

-   **URL:** `/api/agenda/buscar`
-   **Método:** `GET`
-   **Parâmetros de Query:**
    -   `palavraChave` (obrigatório): O termo que você deseja buscar no resultado da agenda.
-   **Exemplo de Uso:**
    ```
    http://localhost:8084/api/agenda/buscar?palavraChave=Angular
    ```
-   **Resposta de Sucesso:** Um array JSON contendo as agendas encontradas.