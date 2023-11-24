package br.com.fiap.gs.service;

import br.com.fiap.gs.dao.MonitoramentoUsuarioDao;
import br.com.fiap.gs.model.MonitoramentoUsuario;
import br.com.fiap.gs.model.Usuario;

import java.sql.Connection;
import java.util.List;

public class MonitoramentoUsuarioService {

    private MonitoramentoUsuarioDao monitoramentoUsuarioDao;

    public MonitoramentoUsuarioService(Connection connection) {
        this.monitoramentoUsuarioDao = new MonitoramentoUsuarioDao(connection);
    }

    public void cadastrarMonitoramentoUsuario(MonitoramentoUsuario monitoramento) {
        monitoramentoUsuarioDao.inserirMonitoramentoUsuario(monitoramento);
    }

    public void atualizarMonitoramentoUsuario(int id, MonitoramentoUsuario monitoramento) {
        monitoramento.setIdDependenciaUser(id);
        monitoramentoUsuarioDao.atualizarMonitoramentoUsuario(monitoramento);
    }

    public void excluirMonitoramentoUsuario(int id) {
        monitoramentoUsuarioDao.excluirMonitoramentoUsuario(id);
    }

    public MonitoramentoUsuario buscarMonitoramentoPorId(int id) {
        return monitoramentoUsuarioDao.pesquisarMonitoramentoUPorId(id);
    }

    public List<MonitoramentoUsuario> listarMonitoramentosUsuarios() {
        return monitoramentoUsuarioDao.listarMonitoramentosUsuarios();
    }

    public void analisarPadroesEmocionaisConsumoNicotina(int idUsuario) {
        Usuario usuario = new Usuario(idUsuario, null, null, null, null);
        monitoramentoUsuarioDao.analisarPadroesEmocionaisConsumoNicotina(usuario);
    }

    public List<String> gerarRelatoriosPersonalizados(int idUsuario) {
        Usuario usuario = new Usuario(idUsuario, null, null, null, null);
        return monitoramentoUsuarioDao.gerarRelatoriosPersonalizados(usuario);
    }
}
