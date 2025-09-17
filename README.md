# Projeto Gemini-Agenda 🤖✨

## Descrição

Este é um projeto de API REST construída com **Spring Boot** que utiliza a inteligência artificial do **Google Gemini** para criar e organizar uma agenda de tarefas de forma inteligente.

A API recebe um período de datas, uma descrição da disponibilidade de trabalho e uma lista de tarefas com suas respectivas prioridades e complexidades, e retorna um cronograma diário otimizado.

---

## Tecnologias Utilizadas

-   **Backend:**
    -   Java 21
    -   Spring Boot 3
    -   Maven
-   **Inteligência Artificial:**
    -   Google Gemini API
-   **Planejamento Futuro:**
    -   Banco de Dados: PostgreSQL com Docker
    -   Frontend: Angular

---

## Como Executar o Projeto

Siga os passos abaixo para configurar e executar a aplicação localmente.

### Pré-requisitos

-   Java (JDK) 21 ou superior
-   Apache Maven 3.8 ou superior

### Passos para Configuração

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/ProjetoGemini.git](https://github.com/seu-usuario/ProjetoGemini.git)
    cd ProjetoGemini
    ```

2.  **⚠️ Configuração da Chave de API (Passo Obrigatório)**

    Este projeto precisa de uma chave de API do Google Gemini para funcionar. O arquivo de configuração com a chave **não** é enviado para o repositório por segurança. Você precisa criá-lo a partir do modelo de exemplo:

    -   **Copie o arquivo de exemplo:** No terminal, na raiz do projeto, execute o comando:
        ```bash
        cp src/main/resources/application.properties.example src/main/resources/application.properties
        ```
    -   **Adicione sua chave:** Abra o novo arquivo `src/main/resources/application.properties` e substitua o valor `COLOQUE_SUA_CHAVE_DE_API_AQUI` pela sua chave de API real do Gemini.

3.  **Execute a Aplicação:**
    Use o Maven para compilar e iniciar o servidor Spring Boot.
    ```bash
    mvn spring-boot:run
    ```
    A API estará rodando em `http://localhost:8084`.

---

## Endpoint da API

### Organizar Agenda

-   **URL:** `/api/agenda`
-   **Método:** `POST`
-   **Corpo da Requisição (Body):**

    ```json
    {
        "dataInicio": "17/09/2025",
        "dataFim": "25/09/2025",
        "descricao": "Horário de trabalho: Segundas a sextas das 09:00h as 18:00h com intervalo de almoço entre 14:30h e 15:30h",
        "tarefas": [
            {
                "nome": "Desenvolvimento de API com integração com OpenAI",
                "urgencia": "Alta",
                "tempoEmHoras": 12,
                "complexidade": "Alta"
            },
            {
                "nome": "Backup de banco de dados SqlServer",
                "urgencia": "Media",
                "tempoEmHoras": 6,
                "complexidade": "Media"
            }
        ]
    }
    ```
