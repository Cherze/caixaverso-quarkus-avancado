package caixa.verso.repository;

import caixa.verso.model.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public Produto findByNome(String nome){
        return find("nome", nome).firstResult();
    }
    public List<Produto> findByPreco(double preco){
        return find("preco", preco).stream().toList();
    }
}
