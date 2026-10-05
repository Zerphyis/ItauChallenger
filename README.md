#  Itaú Backend Challenge 

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162.svg)](https://junit.org/junit5/)
[![Mockito](https://img.shields.io/badge/Mockito-Testing-yellowgreen.svg)](https://site.mockito.org/)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-3.x-6DB33F.svg)](https://spring.io/projects/spring-data-jpa)
---


##  Visão Geral

O objetivo principal deste projeto é lidar com transações de alta frequência com baixíssima latência e sem dependência de bancos de dados relacionais ou NoSQL, mantendo o estado 100% em memória.

### Principais Desafios Técnicos Solucionados:
1. **Thread-Safety**: Garantir que requisições de gravação (`POST`) e leitura de estatísticas (`GET`) simultâneas não causem *race conditions* ou inconsistência de dados.
2. **Cálculo de Estatísticas em Tempo Real**: Processar agregados (`sum`, `avg`, `min`, `max`, `count`) em uma janela de tempo dinâmica de **60 segundos**.
3. **Tratamento Resiliente de Exceções**: Retornar os códigos de status HTTP corretos de acordo com os requisitos rigorosos da especificação do desafio (`201`, `200`, `400`, `422`).

---

## Arquitetura & Decisões de Engenharia

O projeto foi construído seguindo os princípios de **Clean Architecture** e **SOLID**, garantindo baixo acoplamento, alta coesão e facilidade de manutenção e testabilidade.

```
┌─────────────────────────────────────────────────────────┐
│              Presentation Layer (Controllers)           │
│  - Rest Controllers, DTOs, Bean Validations, Handlers   │
└───────────────────────────┬─────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────┐
│                 Domain & Service Layer                  │
│  - Business Rules, Time Window Validation, Aggregations │
└───────────────────────────┬─────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────┐
│              In-Memory Repository Layer                 │
│  - Thread-Safe Data Store (Concurrent Collections)      │
└─────────────────────────────────────────────────────────┘
```


---

##  Stack Tecnológica

* **Linguagem**: Java 17 (LTS)
* **Framework**: Spring Boot 3.x (Spring Web, Spring Validation)
* **Produtividade**: Lombok
* **Testes Unitários & Integração**: JUnit 5, Mockito, AssertJ, Spring Boot Test
* **Build Tool**: Apache Maven

---

##  Regras de Negócio & Matriz de Resposta

| Cenário / Regra de Negócio | Condição | Status HTTP Retornado |
| :--- | :--- | :---: |
| **Transação Válida** | $valor \ge 0$ AND $dataHora \le agora$ AND $dataHora \ge (agora - 60s)$ | `201 Created` |
| **Transação Antiga** | $dataHora < (agora - 60s)$ | `422 Unprocessable Entity` |
| **Transação no Futuro** | $dataHora > agora$ | `422 Unprocessable Entity` |
| **Valor Inválido** | $valor < 0$ | `422 Unprocessable Entity` |
| **Payload Malformado** | JSON inválido, campos ausentes ou formato ISO-8601 incorreto | `400 Bad Request` |
| **Limpeza de Dados** | Execução de deleção das transações em memória | `200 OK` |
| **Consulta de Estatísticas** | Retorno dos agregados dos últimos 60 segundos | `200 OK` |

---

##  Documentação de Endpoints

### 1. Criar Transação

Recebe uma nova transação e realiza as validações temporais e de valor.

- **Endpoint**: `POST /transacao`
- **Content-Type**: `application/json`

#### Exemplo de Payload:
```json
{
  "valor": 89.99,
  "dataHora": "2026-10-05T08:45:10.000-03:00"
}
```

#### Chamada via cURL:
```bash
curl -X POST http://localhost:8080/transacao \
  -H "Content-Type: application/json" \
  -d '{
    "valor": 89.99,
    "dataHora": "2026-10-05T08:45:10.000-03:00"
  }'
```

#### Respostas Possíveis:
- `201 Created`: Transação aceita com sucesso (corpo vazio).
- `422 Unprocessable Entity`: Violou regras de negócio (data futura, >60s ou valor negativo).
- `400 Bad Request`: Requisição malformada.

---

### 2. Deletar Transações

Limpa do sistema todas as transações registradas na memória.

- **Endpoint**: `DELETE /transacao`

#### Chamada via cURL:
```bash
curl -X DELETE http://localhost:8080/transacao
```

#### Resposta:
- `200 OK`: Transações apagadas com sucesso (corpo vazio).

---

### 3. Obter Estatísticas

Calcula e retorna os valores estatísticos das transações realizadas nos **últimos 60 segundos**.

- **Endpoint**: `GET /estatistica`
- **Accept**: `application/json`

#### Chamada via cURL:
```bash
curl -X GET http://localhost:8080/estatistica
```

#### Exemplo de Resposta (`200 OK`):
```json
{
  "count": 1,
  "sum": 89.99,
  "avg": 89.99,
  "min": 89.99,
  "max": 89.99
}
```

> **Nota**: Se não houver transações ocorridas nos últimos 60 segundos, todos os campos retornam com valor $0$ / $0.0$.

---

##  Como Executar

### Pré-requisitos
- **Java JDK 17** ou superior
- **Git**

### Passo a Passo

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/Zerphyis/ItauChallenger.git
   cd ItauChallenger
   ```

2. **Compilar a aplicação**:
   ```bash
   ./mvnw clean package
   ```

3. **Executar a aplicação**:
   ```bash
   ./mvnw spring-boot:run
   ```
   A API estará acessível em `http://localhost:8080`.

---

## 🧪 Estratégia de Testes

O projeto conta com uma suíte de testes focada em garantir a corretude das regras de negócio e a estabilidade da aplicação:

- **Testes Unitários**: Validação isolada dos serviços, regras de data/hora e cálculo estatístico.
- **Testes de Borda (*Edge Cases*)**: Validação de cenários limite como exatos 60 segundos atrás, datas no milissegundo futuro, e listas sem transações.
- **Testes de Integração**: Testes dos controllers utilizando `MockMvc` para validar os contratos de API e códigos de retorno HTTP.

Para rodar todos os testes:
```bash
./mvnw test
```

---


- **Desafio Original**: [feltex/desafio-itau-backend](https://github.com/feltex/desafio-itau-backend)
