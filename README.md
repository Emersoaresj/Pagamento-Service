# Pagamento Service

Serviço Java baseado em Spring Boot para simular o processamento de pagamentos.

---

## 🏗️ Arquitetura

O projeto adota uma arquitetura simples, focada em um mock na API para recebimento de requisições de pagamento.

- **API**: Endpoint REST para receber e processar pagamentos.
- **DTO**: Objetos de transferência de dados.

---

## 📁 Estrutura de Pastas
```
src/main/java/com/fiap/postech/pagamento_service/
├── api/
│   └── PagamentoMockController.java
├── dto/
│   ├── PagamentoRequest.java
│   └── PagamentoResponse.java
└── PagamentoServiceApplication.java

```

---

## 🧩 Principais Classes

- **PagamentoMockController**: Controller que expõe o endpoint para processar pagamentos.
- **PagamentoRequest**: DTO que representa a requisição de pagamento.
- **PagamentoResponse**: DTO que representa a resposta do processamento de pagamento.

---

## ⚙️ Configuração

O arquivo `application.yml` define:
- Conexão com PostgreSQL (ajustável por variáveis de ambiente).
- Flyway para migrações automáticas do banco de dados.

---

## ▶️ Executando o Projeto

1. Certifique-se de ter o Java e o Maven instalados.
2. Execute o projeto com: `mvn spring-boot:run`
3. A aplicação estará disponível na porta configurada (padrão: 8080).
