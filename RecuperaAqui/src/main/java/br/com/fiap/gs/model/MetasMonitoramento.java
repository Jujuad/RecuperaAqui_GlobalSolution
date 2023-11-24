package br.com.fiap.gs.model;

import java.sql.Timestamp;

public class MetasMonitoramento {
    private int idMetaMonitoramento;
    private int qtdeSubstanciaMeta;
    private Timestamp dataInicioMeta;
    private Timestamp dataFimMeta;
    private int idUsuario;

    public MetasMonitoramento(int idMetaMonitoramento, int qtdeSubstanciaMeta, Timestamp dataInicioMeta, Timestamp dataFimMeta, int idUsuario) {
        this.idMetaMonitoramento = idMetaMonitoramento;
        this.qtdeSubstanciaMeta = qtdeSubstanciaMeta;
        this.dataInicioMeta = dataInicioMeta;
        this.dataFimMeta = dataFimMeta;
        this.idUsuario = idUsuario;
    }

	public int getIdMetaMonitoramento() {
		return idMetaMonitoramento;
	}

	public void setIdMetaMonitoramento(int idMetaMonitoramento) {
		this.idMetaMonitoramento = idMetaMonitoramento;
	}

	public int getQtdeSubstanciaMeta() {
		return qtdeSubstanciaMeta;
	}

	public void setQtdeSubstanciaMeta(int qtdeSubstanciaMeta) {
		this.qtdeSubstanciaMeta = qtdeSubstanciaMeta;
	}

	public Timestamp getDataInicioMeta() {
		return dataInicioMeta;
	}

	public void setDataInicioMeta(Timestamp dataInicioMeta) {
		this.dataInicioMeta = dataInicioMeta;
	}

	public Timestamp getDataFimMeta() {
		return dataFimMeta;
	}

	public void setDataFimMeta(Timestamp dataFimMeta) {
		this.dataFimMeta = dataFimMeta;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

    
}

