package caixa.verso.service;

import caixa.verso.dto.ProdutoDto;
import caixa.verso.dto.mapper.ProdutoMapper;
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

        return ProdutoMapper.toDto(produtoRepository.findById(id));
    }



}
