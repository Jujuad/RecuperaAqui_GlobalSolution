package br.com.fiap.gs.service;

import br.com.fiap.gs.dao.MetasMonitoramentoDao;
import br.com.fiap.gs.model.MetasMonitoramento;
import br.com.fiap.gs.model.Usuario;

import java.sql.Connection;
import java.util.List;

public class MetasMonitoramentoService {

    private MetasMonitoramentoDao metasMonitoramentoDao;

    public MetasMonitoramentoService(Connection connection) {
        this.metasMonitoramentoDao = new MetasMonitoramentoDao(connection);
    }

    public void cadastrarMetaMonitoramento(MetasMonitoramento meta, Usuario usuario) {
        metasMonitoramentoDao.inserirMetaMonitoramento(meta);

    }

    public void atualizarMetaMonitoramento(int id, MetasMonitoramento meta) {

        meta.setIdMetaMonitoramento(id);
        metasMonitoramentoDao.atualizarMetaMonitoramento(meta);
    }

    public void excluirMetaMonitoramento(int id) {
        metasMonitoramentoDao.excluirMetaMonitoramento(id);
    }

    public MetasMonitoramento buscarMetaPorId(int id) {
        return (MetasMonitoramento) metasMonitoramentoDao.pesquisarMetasPorUsuario(id);
    }

    public List<MetasMonitoramento> listarMetasPorUsuario(int idUsuario) {
        return metasMonitoramentoDao.pesquisarMetasPorUsuario(idUsuario);
    }

    private void enviarNotificacoesPersonalizadas(Usuario usuario) {
        String mensagem = "Olá " + usuario.getNomeUsuario() + ",\n\n"
                + "Você recebeu uma notificação personalizada do RecuperaAqui.\n"
                + "Mensagem: Sua meta foi registrada com sucesso!\n\n"
                + "Atenciosamente, equipe RecuperaAqui\n";

        // Implementação da lógica de envio de notificações personalizadas será inclusa posteriormente
        enviarNotificacaoPorMeioEscolhido(usuario, mensagem);
    }

    private void enviarNotificacaoPorMeioEscolhido(Usuario usuario, String mensagem) {
        
        System.out.println("Enviando notificação para: " + usuario.getEmailUsuario());
        System.out.println("Mensagem: " + mensagem);
    }
}
