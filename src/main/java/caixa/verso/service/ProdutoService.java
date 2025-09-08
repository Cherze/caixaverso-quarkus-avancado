package caixa.verso.service;

import caixa.verso.dto.ProdutoDto;
import caixa.verso.dto.mapper.ProdutoMapper;
import caixa.verso.exception.ProdutoNaoEncontradoException;
import caixa.verso.model.Produto;
import caixa.verso.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped

public class ProdutoService {

    public final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDto> getAll() {
        return produtoRepository.findAll().list().stream().map(ProdutoMapper::toDto).toList();
    }

    public Produto create(ProdutoDto produtoDto){
        Produto produto = ProdutoMapper.toEntity(produtoDto);
        produtoRepository.persist(produto);
        return produto;
    }

    public ProdutoDto getById(long id) {
        Produto produto = produtoRepository.findById(id);
        if(produto==null){
            throw new ProdutoNaoEncontradoException("Produto não encontrado!");
        }
        return ProdutoMapper.toDto(produto);
    }


    public void update(long id, ProdutoDto produtoDto) {
        Produto produto = produtoRepository.findById(id);

        if (produto==null){
            throw new ProdutoNaoEncontradoException("Produto não encontrado!");
        }
        ProdutoMapper.updateProduto(produto, produtoDto);
    }

    public void delete(long id) {

        if (produtoRepository.findById(id)==null){
            throw new ProdutoNaoEncontradoException("Produto não encontrado!");
        }
        produtoRepository.deleteById(id);
    }
}
