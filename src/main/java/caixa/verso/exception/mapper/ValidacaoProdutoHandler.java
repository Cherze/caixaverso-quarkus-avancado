package caixa.verso.exception.mapper;

import caixa.verso.exception.ValidacaoProdutoException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidacaoProdutoHandler implements ExceptionMapper<ValidacaoProdutoException> {
    @Override
    public Response toResponse(ValidacaoProdutoException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
