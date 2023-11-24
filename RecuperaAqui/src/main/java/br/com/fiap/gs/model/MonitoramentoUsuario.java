package br.com.fiap.gs.model;



import java.sql.Timestamp;

public class MonitoramentoUsuario {
    private int idDependenciaUser;
    private String qtdConsumoDiarioAtual;
    private Timestamp dataInicioMonitoramento;
    private Timestamp dataFimMonitoramento;
    private int idUsuario;
    private int idTipoDependenciaQuimica;

    public MonitoramentoUsuario(int idDependenciaUser, String qtdConsumoDiarioAtual, Timestamp dataInicioMonitoramento, Timestamp dataFimMonitoramento, int idUsuario, int idTipoDependenciaQuimica) {
        this.idDependenciaUser = idDependenciaUser;
        this.qtdConsumoDiarioAtual = qtdConsumoDiarioAtual;
        this.dataInicioMonitoramento = dataInicioMonitoramento;
        this.dataFimMonitoramento = dataFimMonitoramento;
        this.idUsuario = idUsuario;
        this.idTipoDependenciaQuimica = idTipoDependenciaQuimica;
    }

	public int getIdDependenciaUser() {
		return idDependenciaUser;
	}

	public void setIdDependenciaUser(int idDependenciaUser) {
		this.idDependenciaUser = idDependenciaUser;
	}

	public String getQtdConsumoDiarioAtual() {
		return qtdConsumoDiarioAtual;
	}

	public void setQtdConsumoDiarioAtual(String qtdConsumoDiarioAtual) {
		this.qtdConsumoDiarioAtual = qtdConsumoDiarioAtual;
	}

	public Timestamp getDataInicioMonitoramento() {
		return dataInicioMonitoramento;
	}

	public void setDataInicioMonitoramento(Timestamp dataInicioMonitoramento) {
		this.dataInicioMonitoramento = dataInicioMonitoramento;
	}

	public Timestamp getDataFimMonitoramento() {
		return dataFimMonitoramento;
	}

	public void setDataFimMonitoramento(Timestamp dataFimMonitoramento) {
		this.dataFimMonitoramento = dataFimMonitoramento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdTipoDependenciaQuimica() {
		return idTipoDependenciaQuimica;
	}

	public void setIdTipoDependenciaQuimica(int idTipoDependenciaQuimica) {
		this.idTipoDependenciaQuimica = idTipoDependenciaQuimica;
	}

    
}
