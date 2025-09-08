package caixa.verso.exception.mapper;

import caixa.verso.exception.ProdutoNaoEncontradoException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProdutoNaoEncontradoHandler implements ExceptionMapper<ProdutoNaoEncontradoException> {

    @Override
    public Response toResponse(ProdutoNaoEncontradoException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
