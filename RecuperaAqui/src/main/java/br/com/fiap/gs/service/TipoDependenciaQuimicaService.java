package br.com.fiap.gs.service;

import br.com.fiap.gs.dao.TipoDependenciaQuimicaDao;
import br.com.fiap.gs.model.TipoDependenciaQuimica;

import java.sql.Connection;
import java.util.List;

public class TipoDependenciaQuimicaService {

    private TipoDependenciaQuimicaDao tipoDependenciaQuimicaDao;

    public TipoDependenciaQuimicaService(Connection connection) {
        this.tipoDependenciaQuimicaDao = new TipoDependenciaQuimicaDao(connection);
    }

    public void cadastrarTipoDependenciaQuimica(TipoDependenciaQuimica tipoDependenciaQuimica) {
        tipoDependenciaQuimicaDao.inserirTipoDependenciaQuimica(tipoDependenciaQuimica);
    }

    public void atualizarTipoDependenciaQuimica(TipoDependenciaQuimica tipoDependenciaQuimica) {
        tipoDependenciaQuimicaDao.atualizarTipoDependenciaQuimica(tipoDependenciaQuimica);
    }

    public void excluirTipoDependenciaQuimica(int idTipoDependenciaQuimica) {
        tipoDependenciaQuimicaDao.excluirTipoDependenciaQuimica(idTipoDependenciaQuimica);
    }

    public List<TipoDependenciaQuimica> listarTiposDependenciaQuimica() {
        return tipoDependenciaQuimicaDao.listarTiposDependenciaQuimica();
    }
}
