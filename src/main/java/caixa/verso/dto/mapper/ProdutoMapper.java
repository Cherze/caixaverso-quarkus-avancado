package caixa.verso.dto.mapper;

import caixa.verso.dto.ProdutoDto;
import caixa.verso.model.Produto;

public class ProdutoMapper {

    public static Produto toEntity(ProdutoDto produtoDto){
        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        return produto;
    }

    public static ProdutoDto toDto(Produto produto){
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setNome(produto.getNome());
        produtoDto.setDescricao(produto.getDescricao());
        produtoDto.setPreco(produto.getPreco());
        return produtoDto;
    }

    public static Produto updateProduto(Produto produto, ProdutoDto produtoDto){
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        return produto;
    }

}
