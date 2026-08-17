# 🚴‍♂️ Aplicação de Food Delivery - Fluxo de Pedidos e Avaliações

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED.svg)](https://www.docker.com/)

Aplicação completa de **Food Delivery** desenvolvida em **Java / Spring Boot** focada no gerenciamento estrito do fluxo de status de pedidos e avaliações por clientes.

---

## 📌 Funcionalidades Principais

- 🏬 **Cadastro e Gestão de Restaurantes**
- 👤 **Cadastro e Gestão de Clientes**
- 🛒 **Criação de Pedidos** (Status inicial: `CRIADO`)
- 🟢🔴 **Aceite ou Recusa de Pedidos pelo Restaurante**
- 🚚 **Confirmação de Entrega pelo Restaurante**
- ⭐ **Avaliação do Pedido pelo Cliente** (Nota de 1 a 5)
- 🖥️ **Interface Web Demonstrativa Interativa** (Inclusa em `/`)

---

## 🚦 Regras de Negócio e Máquina de Estados

O pedido segue obrigatoriamente a seguinte sequência de status:

```
[CRIADO] ───► [ACEITO] ───► [ENTREGUE] ───► [AVALIADO]
   │
   └───► [RECUSADO]
```

- **Restaurante Autorizado:** Um restaurante só pode aceitar, recusar ou entregar pedidos destinados a ele.
- **Transição Estrita:**
  - Pedidos recusados **não podem** ser entregues ou avaliados.
  - Apenas pedidos **aceitos** podem ser entregues.
  - Um pedido só pode ser avaliado após estar no status **`ENTREGUE`**.
- **Cliente Autorizado:** A avaliação só pode ser feita pelo cliente criador do pedido.
- **Validação de Nota:** A nota da avaliação deve ser um valor inteiro entre **1 e 5**.

---

## 🚀 Como Executar a Aplicação

### Pré-requisitos
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/) instalados na sua máquina.

### Passo Único
Na raiz do projeto, execute o comando abaixo no terminal:

```bash
docker-compose up --build
```

A aplicação estará acessível em:
- 🌐 **Interface Web Demonstrativa:** `http://localhost:8080`
- 📑 **Documentação OpenAPI / Swagger UI:** `http://localhost:8080/swagger-ui.html`

---

## 📑 Documentação dos Endpoints REST

### 🏬 Restaurantes (`/v1/restaurantes`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/v1/restaurantes` | Cadastra um novo restaurante (`name` via JSON Body ou Query Param) |
| `GET` | `/v1/restaurantes` | Lista todos os restaurantes cadastrados |
| `GET` | `/v1/restaurantes/{id}` | Busca um restaurante por ID |
| `DELETE` | `/v1/restaurantes/{id}` | Remove um restaurante por ID |

### 👤 Clientes (`/v1/clientes`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/v1/clientes` | Cadastra um novo cliente (`name`, `email` via JSON Body ou Query Param) |
| `GET` | `/v1/clientes` | Lista todos os clientes cadastrados |
| `GET` | `/v1/clientes/{id}` | Busca um cliente por ID |
| `DELETE` | `/v1/clientes/{id}` | Remove um cliente por ID |

### 🛒 Pedidos (`/v1/pedidos`)
| Método | Endpoint | Parâmetros | Descrição |
| :--- | :--- | :--- | :--- |
| `POST` | `/v1/pedidos` | `idRestaurante`, `idCliente` | Cria um pedido com status inicial `CRIADO` |
| `GET` | `/v1/pedidos` | - | Lista todos os pedidos |
| `GET` | `/v1/pedidos/{id}` | - | Busca um pedido por ID |
| `PATCH` | `/v1/pedidos/aceitar/{id}` | `idRestaurante` | Aceita um pedido (`CRIADO` → `ACEITO`) |
| `PATCH` | `/v1/pedidos/recusar/{id}` | `idRestaurante` | Recusa um pedido (`CRIADO` → `RECUSADO`) |
| `PATCH` | `/v1/pedidos/entregar/{id}` | `idRestaurante` | Confirma entrega (`ACEITO` → `ENTREGUE`) |
| `POST` | `/v1/pedidos/avaliar/{id}` | `idCliente`, `notaPedido` | Avalia pedido (`ENTREGUE` → `AVALIADO`, nota 1 a 5) |

---

## 📖 Arquitetura e Solução Técnica

Para mais detalhes sobre a arquitetura, modelagem de banco de dados (ERD) e diagrama de estados, consulte o documento:
👉 [Desenho da Solução / ARQUITETURA.md](file:///c:/Users/clash/Desktop/Food-Delivery/docs/ARQUITETURA.md)
