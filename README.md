# Produto-API

CHERZE CARVALHO FREITAS

Fabio Monteiro Crispim

Marcos Guilherme Grandi

Raphael dos Santos Pinto

## Aplicação

Você pode executar com o script Wrapper maven ou Quarkus CLI
.
```shell script
./mvnw quarkus:dev
 quarkus dev
```

> **_NOTE:_**  Utilizar Insomnia ou Postman na URL <http://localhost:8080/produtos/>.

## Características

CRUD REST completo para cadastro, pesquisa, listagem, alteração e exclusão de produtos no seguinte formato JSON:
```shell script
{
  "nome":"produto1",
  "descricao":"descricao1"
  "preco":100
}
```

Acesso OIDC com Keycloak:
    -> Usuário com role admin (padrão alice e senha alice)
    -> Usuário com role user (padrão bob e senha bob)

Busca de produto por ID em cache.

Mensageria com KAFKA ao cadastrar um produto no banco. Printa no terminal um log contendo :

"Produto cadastrado com sucesso: {id, nome, descricao, preco, e instante de criação}"