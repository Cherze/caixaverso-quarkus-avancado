package caixa.verso.resource;

import caixa.verso.dto.ProdutoDto;
import caixa.verso.model.Produto;
import caixa.verso.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ProdutoResource {

    public final ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @Transactional
    public Response addProduto(ProdutoDto produtoDto){
        Produto produto = produtoService.create(produtoDto);
        return Response.status(Response.Status.CREATED).entity(produto).build();
    }

    @GET
    public Response getProduto() {
        List<ProdutoDto> produtos = produtoService.getAll();
        return Response.status(Response.Status.OK).entity(produtos).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id){
        return Response.status(Response.Status.OK).entity(produtoService.getById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateProduto(@PathParam("id") long id, ProdutoDto produtoDto){
        produtoService.update(id,produtoDto);
        return Response.status(Response.Status.OK).build();
    }

}
