package caixa.verso.service;

import caixa.verso.dto.ProdutoDto;
import caixa.verso.dto.mapper.ProdutoMapper;
import caixa.verso.exception.ValidacaoProdutoException;
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
        this.validaProdutoDto(produtoDto);
        Produto produto = ProdutoMapper.toEntity(produtoDto);
        produtoRepository.persist(produto);
        return produto;
    }

    public ProdutoDto getById(long id) {
        Produto produto = produtoRepository.findById(id);
        this.isNull(produto);
        return ProdutoMapper.toDto(produto);
    }


    public Produto update(long id, ProdutoDto produtoDto) {
        this.validaProdutoDto(produtoDto);
        Produto produto = produtoRepository.findById(id);
        this.isNull(produto);
        return ProdutoMapper.updateProduto(produto, produtoDto);
    }

    public void delete(long id) {

        if(!produtoRepository.deleteById(id)){
            throw new ProdutoNaoEncontradoException("Produto não encontrado.");
        }
    }

    public void validaProdutoDto(ProdutoDto produtoDto){
        if (produtoDto.getPreco()<=0){
            throw new ValidacaoProdutoException("Preço precisa ser maior que zero.");
        } else if (produtoDto.getNome()==null || produtoDto.getNome().isBlank()) {
            throw new ValidacaoProdutoException("Nome não pode ser vazio.");
        }
    }

    public void isNull(Produto produto){
        if (produto==null){
            throw new ProdutoNaoEncontradoException("Produto não encontrado.");
        }
    }

}
