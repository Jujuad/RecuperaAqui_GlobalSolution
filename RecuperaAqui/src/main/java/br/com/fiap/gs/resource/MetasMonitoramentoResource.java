package br.com.fiap.gs.resource;

import br.com.fiap.gs.dao.MetasMonitoramentoDao;
import br.com.fiap.gs.model.MetasMonitoramento;
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

@Path("/metas-monitoramento")
public class MetasMonitoramentoResource {

    private MetasMonitoramentoDao metasMonitoramentoDao;

    public MetasMonitoramentoResource(Connection connection) {
        this.metasMonitoramentoDao = new MetasMonitoramentoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirMetaMonitoramento(MetasMonitoramento meta, @Context UriInfo uri) {
        metasMonitoramentoDao.inserirMetaMonitoramento(meta);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(meta.getIdMetaMonitoramento()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarMetaMonitoramento(@PathParam("id") int id, MetasMonitoramento meta) {
        meta.setIdMetaMonitoramento(id);
        metasMonitoramentoDao.atualizarMetaMonitoramento(meta);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirMetaMonitoramento(@PathParam("id") int id) {
        metasMonitoramentoDao.excluirMetaMonitoramento(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarMetaPorId(@PathParam("id") int id) {
        MetasMonitoramento meta = (MetasMonitoramento) metasMonitoramentoDao.pesquisarMetasPorUsuario(id);
        if (meta != null) {
            return Response.ok(meta).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMetasPorUsuario(@QueryParam("idUsuario") int idUsuario) {
        List<MetasMonitoramento> metas = metasMonitoramentoDao.pesquisarMetasPorUsuario(idUsuario);
        return Response.ok(metas).build();
    }
    
 // Função para enviar notificações personalizadas
    private void enviarNotificacoesPersonalizadas(Usuario usuario) {
        String mensagem = "Olá " + usuario.getNomeUsuario() + ",\n\n"
                + "Você recebeu uma notificação personalizada do RecuperaAqui.\n"
                + "Mensagem: Sua meta foi registrada com sucesso!\n\n"
                + "Atenciosamente, equipe RecuperaAqui\n";

        // Implementação da lógica de envio de notificações personalizadas será inclusa posteriormente
        enviarNotificacaoPorMeioEscolhido(usuario, mensagem);
    }

    // Função genérica para enviar notificações
    private void enviarNotificacaoPorMeioEscolhido(Usuario usuario, String mensagem) {
        // Implementação da lógica para enviar notificações por meio escolhido
        System.out.println("Enviando notificação para: " + usuario.getEmailUsuario());
        System.out.println("Mensagem: " + mensagem);
    }
}
