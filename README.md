<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0f172a,50:1e3a5f,100:0ea5e9&height=200&section=header&text=Relatório%20de%20Vendas%20API&fontSize=40&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=Solução%20em%20Java%20·%20backend-challenges&descAlignY=58&descSize=16&descColor=94a3b8"/>

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge)](https://projectlombok.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-22c55e?style=for-the-badge)](./LICENSE)

</div>

---

## 📖 Sobre

Solução para o desafio [Relatório de Vendas](https://github.com/Francisco-Montalvao/backend-challenges) do **backend-challenges** — API REST de um PDV (Ponto de Venda) que cobre o cadastro de vendedores e vendas, além de endpoints analíticos que usam agregações SQL para gerar relatórios de performance por período.

---

## 🧠 Decisões de design

**`record` para todos os DTOs**

DTOs implementados como `record` do Java — imutáveis, sem boilerplate e perfeitos para representar dados de entrada e saída da API.

**Validação na entrada, nunca no banco**

Bean Validation nos DTOs de request garante que dados inválidos são barrados antes de chegar ao service. O repository nunca recebe um dado inconsistente.

**Separação de responsabilidades**

O projeto tem dois services distintos com propósitos diferentes: `VendedorService` e `VendaService` cuidam do CRUD, enquanto o `RelatorioService` é exclusivo para as consultas analíticas — mantendo as responsabilidades claras e o código fácil de manter.

**Relatórios com agregação no banco**

Os endpoints de relatório usam `@Query` com `nativeQuery = true` e funções SQL (`SUM`, `COUNT`, `GROUP BY`, `ORDER BY`) — o banco faz o trabalho pesado, sem trazer registros desnecessários para a memória da aplicação.

**Interface de projeção para queries nativas**

Para mapear os resultados das queries agregadas, o projeto usa interfaces de projeção do Spring Data JPA — a forma oficial de mapear resultados de `nativeQuery` sem precisar de entidades completas ou `Object[]`.

**Exceções customizadas com status dinâmico**

Uma única classe base `RecursoNaoEncontradoException` recebe o `HttpStatus` como parâmetro no construtor. Um único handler no `GlobalExceptionHandler` cobre todos os erros de negócio da aplicação — sem duplicação.

**Interfaces de Projeção para Queries Nativas**

O Spring Data JPA não consegue mapear automaticamente o resultado de uma `nativeQuery` com agregação para um `record` ou uma classe comum. A solução foi usar **interfaces de projeção** — interfaces com getters cujos nomes batem com os aliases definidos na query SQL.

- **Como funciona:** O Spring gera a implementação dessas interfaces em tempo de execução. Você define o contrato, ele cria o objeto.
- **O resultado:** Acesso tipado e legível aos dados — `resultado.getTotalVendido()` em vez de `(BigDecimal) resultado[0]`.
- **Por que importa:** É a forma oficial recomendada pelo Spring para esse cenário, usada amplamente em projetos de produção com queries analíticas complexas.

**Erros padronizados**

Todas as respostas de erro seguem o mesmo formato com `timestamp`, `status`, `mensagem` e, quando aplicável, um array `erros` com os campos inválidos — facilitando o consumo pelo frontend.

```json
{
  "timestamp": "2026-06-01T17:00:00",
  "status": 400,
  "mensagem": "Erro de validação em campos",
  "erros": [
    { "campo": "email", "mensagem": "Email inválido" }
  ]
}
```

---

## 🔌 Endpoints

### Vendedores

| Método | Rota | Descrição | Sucesso | Erro |
|---|---|---|---|---|
| `POST` | `/api/v1/vendedores` | Cadastra um vendedor | `201` | `400` / `409` |
| `GET` | `/api/v1/vendedores` | Lista todos | `200` | — |
| `GET` | `/api/v1/vendedores/{id}` | Busca por ID | `200` | `404` |
| `PUT` | `/api/v1/vendedores/{id}` | Atualiza dados de contato | `200` | `400` / `404` |
| `DELETE` | `/api/v1/vendedores/{id}` | Remove | `204` | `404` |

### Vendas

| Método | Rota | Descrição | Sucesso | Erro |
|---|---|---|---|---|
| `POST` | `/api/v1/vendas` | Cadastra uma venda | `201` | `400` / `404` |
| `GET` | `/api/v1/vendas` | Lista todas | `200` | — |
| `GET` | `/api/v1/vendas/{id}` | Busca por ID | `200` | `404` |
| `PUT` | `/api/v1/vendas/{id}` | Atualiza o valor total | `200` | `400` / `404` |
| `DELETE` | `/api/v1/vendas/{id}` | Remove | `204` | `404` |

### Relatórios

Os endpoints de relatório aceitam os query params `data_inicio` e `data_fim` (formato `YYYY-MM-DD`). Ambos são obrigatórios.

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/v1/relatorios/vendas/total` | Total vendido e quantidade no período |
| `GET` | `/api/v1/relatorios/vendas/por-vendedor` | Ranking de vendedores no período |
| `GET` | `/api/v1/relatorios/vendas/diario` | Total agrupado por dia no período |

---

## ✅ Exemplos de uso

### Cadastrar vendedor

```bash
curl -X POST http://localhost:8080/api/v1/vendedores \
  -H "Content-Type: application/json" \
  -d '{ "nome": "Carla Souza", "email": "carla@pdv.com", "telefone": "38997225058" }'
```

### Relatório — Total no período

```bash
curl "http://localhost:8080/api/v1/relatorios/vendas/total?data_inicio=2026-01-01&data_fim=2026-01-31"
```

```json
{
  "totalVendido": 48750.00,
  "quantidadeDeVendas": 312,
  "periodo": {
    "inicio": "2026-01-01",
    "fim": "2026-01-31"
  }
}
```

### Relatório — Ranking por vendedor

```bash
curl "http://localhost:8080/api/v1/relatorios/vendas/por-vendedor?data_inicio=2026-01-01&data_fim=2026-01-31"
```

```json
{
  "periodo": { "inicio": "2026-01-01", "fim": "2026-01-31" },
  "vendedores": [
    { "posicao": 1, "id": 1, "nome": "Carla Souza", "totalVendido": 6140.00, "quantidadeDeVendas": 8 },
    { "posicao": 2, "id": 5, "nome": "Ana Lima", "totalVendido": 5420.50, "quantidadeDeVendas": 8 }
  ]
}
```

### Relatório — Diário

```bash
curl "http://localhost:8080/api/v1/relatorios/vendas/diario?data_inicio=2026-01-01&data_fim=2026-01-03"
```

```json
{
  "periodo": { "inicio": "2026-01-01", "fim": "2026-01-03" },
  "dias": [
    { "data": "2026-01-01", "totalVendido": 5200.00, "quantidadeVendas": 34 },
    { "data": "2026-01-02", "totalVendido": 4875.50, "quantidadeVendas": 29 }
  ]
}
```

---

## ▶️ Como rodar

### Pré-requisitos

- Java 21+
- PostgreSQL rodando localmente

### Configuração do banco

```sql
CREATE DATABASE relatorio;
```

Configure o arquivo `src/main/resources/application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/relatorio
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Popular o banco com dados de teste

```bash
psql -U seu_usuario -d relatorio -f import.sql
```

### Rodar a aplicação

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

---

## 📂 Estrutura do projeto

```
src/main/java/com/franciscomontalvao/relatoriodevendas/
├── controller/      # Recebe requisições e devolve respostas HTTP
├── service/         # Regras de negócio (VendedorService, VendaService, RelatorioService)
├── repository/      # Acesso ao banco via Spring Data JPA
├── model/           # Entidades JPA (Vendedor, Venda)
├── dto/             # Records de entrada (request) e saída (response)
├── projection/      # Interfaces de projeção para queries nativas com agregação
├── mapper/          # Conversão entre entidade e DTO
└── exception/       # Exceções customizadas e handler global
```

---

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0ea5e9,50:1e3a5f,100:0f172a&height=100&section=footer"/>

🧑‍💻 Desenvolvido por [Francisco Montalvao](https://github.com/Francisco-Montalvao)

</div>