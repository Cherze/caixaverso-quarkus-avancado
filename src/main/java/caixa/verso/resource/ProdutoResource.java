package caixa.verso.resource;

//import caixa.verso.cache.KeyGenerator;
import caixa.verso.dto.ProdutoDto;
import caixa.verso.model.Produto;
import caixa.verso.service.ProdutoService;
import caixa.verso.service.TokenService;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/produtos")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ProdutoResource {

    public final ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @Transactional
    @RolesAllowed({"admin"})
    @CacheInvalidateAll(cacheName = "cache-produto")
    public Response addProduto(ProdutoDto produtoDto){
        Produto produto = produtoService.create(produtoDto);
        return Response.status(Response.Status.CREATED).entity(produto).build();
    }

    @GET
    @RolesAllowed({"user"})
    public Response getProduto(){
        List<ProdutoDto> produtos = produtoService.getAll();
        return Response.status(Response.Status.OK).entity(produtos).build();

    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user"})
    //@CacheResult(cacheName = "cache-produto")
    public Response getById(@PathParam("id") long id){
        ProdutoDto produto = produtoService.getById(id);
        return Response.status(Response.Status.OK).entity(produto).build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"admin"})
    @CacheInvalidateAll(cacheName = "cache-produto")
    public Response updateProduto(@PathParam("id") long id, ProdutoDto produtoDto){
        Produto produtoAtualizado = produtoService.update(id,produtoDto);
        return Response.status(Response.Status.OK).entity(produtoAtualizado).build();

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"admin"})
    @CacheInvalidateAll(cacheName = "cache-produto")
    public Response deleteProduto(@PathParam("id") long id){
         produtoService.delete(id);
         return Response.status(Response.Status.NO_CONTENT).build();

    }

}
