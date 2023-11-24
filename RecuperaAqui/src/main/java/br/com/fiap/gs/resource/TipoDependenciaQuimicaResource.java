package br.com.fiap.gs.resource;

import br.com.fiap.gs.dao.MonitoramentoUsuarioDao;
import br.com.fiap.gs.dao.TipoDependenciaQuimicaDao;
import br.com.fiap.gs.model.TipoDependenciaQuimica;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.util.List;

@Path("/tipo-dependencia")
public class TipoDependenciaQuimicaResource {

    private TipoDependenciaQuimicaDao tipoDependenciaQuimicaDao;

    public TipoDependenciaQuimicaResource(Connection connection) {
        this.tipoDependenciaQuimicaDao = new TipoDependenciaQuimicaDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarTipoDependenciaQuimica(TipoDependenciaQuimica tipoDependenciaQuimica) {
        tipoDependenciaQuimicaDao.inserirTipoDependenciaQuimica(tipoDependenciaQuimica);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarTipoDependenciaQuimica(TipoDependenciaQuimica tipoDependenciaQuimica) {
        tipoDependenciaQuimicaDao.atualizarTipoDependenciaQuimica(tipoDependenciaQuimica);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirTipoDependenciaQuimica(@PathParam("id") int idTipoDependenciaQuimica) {
        tipoDependenciaQuimicaDao.excluirTipoDependenciaQuimica(idTipoDependenciaQuimica);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTiposDependenciaQuimica() {
        List<TipoDependenciaQuimica> tiposDependenciaQuimica = tipoDependenciaQuimicaDao.listarTiposDependenciaQuimica();
        return Response.ok(tiposDependenciaQuimica).build();
    }
}
