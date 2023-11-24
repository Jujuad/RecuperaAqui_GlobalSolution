package br.com.fiap.gs.resource;

import br.com.fiap.gs.dao.MonitoramentoUsuarioDao;
import br.com.fiap.gs.model.MonitoramentoUsuario;
import br.com.fiap.gs.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/monitoramentoUsuario")
public class MonitoramentoUsuarioResource {

    private MonitoramentoUsuarioDao monitoramentoUsuarioDao;

    public MonitoramentoUsuarioResource(Connection connection) {
        this.monitoramentoUsuarioDao = new MonitoramentoUsuarioDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirMonitoramentoUsuario(MonitoramentoUsuario monitoramento, @Context UriInfo uri) {
        monitoramentoUsuarioDao.inserirMonitoramentoUsuario(monitoramento);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(monitoramento.getIdDependenciaUser()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarMonitoramentoUsuario(@PathParam("id") int id, MonitoramentoUsuario monitoramento) {
        monitoramento.setIdDependenciaUser(id);
        monitoramentoUsuarioDao.atualizarMonitoramentoUsuario(monitoramento);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirMonitoramentoUsuario(@PathParam("id") int id) {
        monitoramentoUsuarioDao.excluirMonitoramentoUsuario(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarMonitoramentoUsuarioPorId(@PathParam("id") int id) {
        MonitoramentoUsuario monitoramento = monitoramentoUsuarioDao.pesquisarMonitoramentoUPorId(id);
        if (monitoramento != null) {
            return Response.ok(monitoramento).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMonitoramentosUsuarios() {
        List<MonitoramentoUsuario> monitoramentos = monitoramentoUsuarioDao.listarMonitoramentosUsuarios();
        return Response.ok(monitoramentos).build();
    }

    @GET
    @Path("/analisarPadroesEmocionais/{idUsuario}")
    public Response analisarPadroesEmocionaisConsumoNicotina(@PathParam("idUsuario") int idUsuario) {
        Usuario usuario = new Usuario(idUsuario, null, null, null, null);  
        usuario.setIdUsuario(idUsuario);
        monitoramentoUsuarioDao.analisarPadroesEmocionaisConsumoNicotina(usuario);
        return Response.ok().build();
    }

    @GET
    @Path("/gerarRelatoriosPersonalizados/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gerarRelatoriosPersonalizados(@PathParam("idUsuario") int idUsuario) {
        Usuario usuario = new Usuario(idUsuario, null, null, null, null);  
        usuario.setIdUsuario(idUsuario);
        List<String> relatorios = monitoramentoUsuarioDao.gerarRelatoriosPersonalizados(usuario);
        return Response.ok(relatorios).build();
    }
}
