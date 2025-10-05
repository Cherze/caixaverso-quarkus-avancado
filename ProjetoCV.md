# Exercício 3

## Descrição

Além do CRUD de produtos já implementado no **Exercício 1**, você deverá adicionar **cache** para melhorar a performance da aplicação.  

O cache deve ser utilizado no endpoint de **buscar produto por ID**, e sempre que houver alguma alteração em um produto (criação, atualização ou exclusão), o cache correspondente deve ser invalidado.

---

## Requisitos Funcionais

### 1. Buscar Produto por ID com Cache

- **Método HTTP:** `GET`
- **Path:** `/produtos/{id}`
- O resultado da busca deve ser armazenado em cache para acessos futuros ao mesmo produto.
- **Status esperado:**
  - `200 OK` em caso de sucesso.
  - `404 Not Found` se o produto não existir.

### 2. Invalidar Cache ao Alterar Produto

Sempre que ocorrer uma das operações abaixo, o cache do produto correspondente deve ser removido/invalidado:

- **Atualizar Produto (`PUT /produtos/{id}`)** → invalida cache do produto atualizado.  
- **Excluir Produto (`DELETE /produtos/{id}`)** → invalida cache do produto removido.  

---

## Critérios de Avaliação (Exercício 3)

- Uso correto do **cache** no endpoint de busca de produto por ID.
- Invalidação correta do cache nas operações de alteração.
- Manutenção do uso correto de **status HTTP** e **métodos HTTP** já definidos no Exercício 1.
- Organização e clareza do código.  

## Bônus

Documentação: https://quarkus.io/guides/kafka

Quando a aplicação criar uma conta, enviar uma mensagem utilizando `quarkus-smallrye-kafka`.

A mensagem deve ter os seguintes atributos:

```yaml
nome: String
descricao: String
preco: BigDecimal
id: Long
dataCriacao: Instant
```

A aplicação que vai consumir pode ser a mesma que vai produzir.
