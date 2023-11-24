package br.com.fiap.gs.model;

public class TipoDependenciaQuimica {
    private int idTipoDependenciaQuimica;
    private String descricaoTipoDependenciaQuimica;

    public TipoDependenciaQuimica(int idTipoDependenciaQuimica, String descricaoTipoDependenciaQuimica) {
        this.idTipoDependenciaQuimica = idTipoDependenciaQuimica;
        this.descricaoTipoDependenciaQuimica = descricaoTipoDependenciaQuimica;
    }

	public int getIdTipoDependenciaQuimica() {
		return idTipoDependenciaQuimica;
	}

	public void setIdTipoDependenciaQuimica(int idTipoDependenciaQuimica) {
		this.idTipoDependenciaQuimica = idTipoDependenciaQuimica;
	}

	public String getDescricaoTipoDependenciaQuimica() {
		return descricaoTipoDependenciaQuimica;
	}

	public void setDescricaoTipoDependenciaQuimica(String descricaoTipoDependenciaQuimica) {
		this.descricaoTipoDependenciaQuimica = descricaoTipoDependenciaQuimica;
	}

    
}

