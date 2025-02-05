# API de Remessas Cambiais

Esta é uma API desenvolvida em **Spring Boot** para gerenciar carteiras e variação cambial de usuários e realizar remessas cambiais. A API permite que usuários cadastrados possuam saldos em **BRL (Real)** e **USD (Dólar)**, realizando operações de conversão e transferência conforme regras definidas.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Feign Client**
- **H2 Database** (para desenvolvimento)
- **Lombok**
- **JUnit 5 & Mockito**
- **Docker & Docker Compose**
- **Maven**

## Configuração do Projeto
### Pré-requisitos
- Java 17 instalado
- Docker instalado

## Como Rodar o Projeto
1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/remessa-cambial.git
   ```
2. Entre no diretório do projeto:
   ```sh
   cd remessa-cambial
   ```
3. Execute o Build e Deploy com Docker Compose:
   ```sh
   docker-compose up --build
   ```
   - Esse comando fará o seguinte:

   - Construirá o projeto e gerar o arquivo JAR.

   - Construirá a imagem Docker da aplicação e build do maven.

   - Iniciará os contêineres usando Docker Compose.


4. A API estará acessível em http://localhost:8080.

5. Para monitorar os logs da aplicação:
   ```sh
   docker logs -f spring-app-remessa
   ```
6. Para parar e remover os containers:
   ```sh
   docker-compose down
   ```

---

## Endpoints da API

### Carteira Controller

#### Criar Carteira
Cria uma nova carteira para um usuário.

**Requisição:**
```http
POST /carteiras
```

**Corpo da Requisição (JSON):**
```json
{
  "nomeCompleto": "João da Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "tipoCarteira": 1,
  "documento": "123.456.789-00",
  "saldoBRL": 1000.00,
  "saldoUSD": 200.00
}
```

**Respostas:**
- **201 Created**: Carteira criada com sucesso.
  ```json
  {
    "id": 1,
    "nomeCompleto": "João da Silva",
    "email": "joao@email.com",
    "tipoCarteira": 1,
    "documento": "123.456.789-00",
    "saldoBRL": 1000.00,
    "saldoUSD": 200.00
  }
  ```
- **400 Bad Request**: Erro de validação nos campos enviados.

#### Buscar Todas as Carteiras
Obtém todas as carteiras cadastradas na API.

**Requisição:**
```http
GET /carteiras
```

**Respostas:**
- **200 OK**: Lista de carteiras cadastradas.
  ```json
  [
    {
      "id": 1,
      "nomeCompleto": "João da Silva",
      "email": "joao@email.com",
      "tipoCarteira": 1,
      "documento": "123.456.789-00",
      "saldoBRL": 1000.00,
      "saldoUSD": 200.00
    },
    {
      "id": 2,
      "nomeCompleto": "Maria Souza",
      "email": "maria@email.com",
      "tipoCarteira": 2,
      "documento": "987.654.321-00",
      "saldoBRL": 5000.00,
      "saldoUSD": 800.00
    }
  ]
  ```
- **204 No Content**: Nenhuma carteira cadastrada.

---

# API de Remessa - Endpoint para Transferência de Valores

Este endpoint faz parte da API de remessas de valores, permitindo a transferência de valores de uma carteira em Real (BRL) para uma carteira em Dólar (USD).

## Endpoint: `POST /remessa`

### Descrição
Este endpoint realiza a remessa de um valor em Real (BRL) para uma carteira de destino em Dólar (USD), através de um processo de conversão de moeda. A transação é realizada entre dois usuários, identificados pelo `pagador` e o `recebedor`.

### Requisição

#### URL
**Requisição:**
```http
POST /carteiras
```
#### Corpo da Requisição (Request Body)
A requisição deve conter um JSON com as seguintes informações:

```json
{
  "pagador": 1,
  "recebedor": 2,
  "vlrTransferencia": 100.00
}

```
### Parâmetros

| Campo            | Tipo       | Descrição                                                    |
|------------------|------------|--------------------------------------------------------------|
| `pagador`        | Long       | ID do usuário pagador (quem está enviando a remessa)         |
| `recebedor`      | Long       | ID do usuário recebedor (quem vai receber o valor convertido)|
| `vlrTransferencia` | BigDecimal | Valor em Real (BRL) a ser transferido e convertido para Dólar (USD) |

### Resposta

Se a requisição for bem-sucedida, o servidor retorna um JSON com os detalhes da transação criada, incluindo informações sobre o câmbio aplicado.

#### Exemplo de Resposta

```json
{
  "id": 123,
  "pagador": {
    "id": 1,
    "nome": "João Silva",
    "saldoBRL": 1000.00,
    "saldoUSD": 200.00
  },
  "recebedor": {
    "id": 2,
    "nome": "Maria Oliveira",
    "saldoBRL": 500.00,
    "saldoUSD": 150.00
  },
  "vlrTransferenciaBRL": 100.00,
  "dhTransferencia": "2025-02-04T15:30:00",
  "cambio": {
    "id": 1,
    "vlrCambioBRLParaUSD": 5.2,
    "cotacaoMoedaHoje": 5.15,
    "variacaoCambialAbsoluta": 0.05,
    "variacaoCambialPercentual": 0.98,
    "dataCotacao": "2025-02-04"
  }
}
```

## Testes
O projeto possui testes unitários para os services de Carteira, utilizando **JUnit 5 e Mockito**.

Os testes validam:
- Cadastro de carteiras
- Busca de carteiras no banco
- Validações de entrada
- Regras de negócio

---

**Autor:** Hanri Santiago

