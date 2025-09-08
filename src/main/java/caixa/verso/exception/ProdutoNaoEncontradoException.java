package caixa.verso.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(String msg){
        super(msg);
    }
}
