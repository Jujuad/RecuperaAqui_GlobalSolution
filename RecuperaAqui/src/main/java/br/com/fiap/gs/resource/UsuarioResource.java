package br.com.fiap.gs.resource;

import br.com.fiap.gs.dao.TipoDependenciaQuimicaDao;
import br.com.fiap.gs.dao.UsuarioDao;
import br.com.fiap.gs.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {

    private UsuarioDao usuarioDao;

    public UsuarioResource(Connection connection) {
        this.usuarioDao = new UsuarioDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(Usuario usuario) {
        usuarioDao.inserirUsuario(usuario);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(Usuario usuario) {
        usuarioDao.atualizarUsuario(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirUsuario(@PathParam("id") int idUsuario) {
        usuarioDao.excluirUsuario(idUsuario);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarUsuarioPorId(@PathParam("id") int idUsuario) {
        Usuario usuario = usuarioDao.pesquisarUsuarioPorId(idUsuario);
        return Response.ok(usuario).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        return Response.ok(usuarios).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarLogin(Usuario usuario) {
        Usuario usuarioLogado = usuarioDao.realizarLogin(usuario.getEmailUsuario(), usuario.getSenhaUsuario());

        if (usuarioLogado != null) {
            return Response.ok(usuarioLogado).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
