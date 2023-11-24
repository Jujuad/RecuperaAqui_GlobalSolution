package br.com.fiap.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.gs.model.TipoDependenciaQuimica;

public class TipoDependenciaQuimicaDao {
    private Connection connection;

   
    public TipoDependenciaQuimicaDao(Connection connection) {
        this.connection = connection;
    }

    
    public void inserirTipoDependenciaQuimica(TipoDependenciaQuimica tipoDependencia) {
        String sql = "INSERT INTO TIPO_DEPENDENCIA_QUIMICA (descricao_TIPO_DEPENDENCIA_QUIMICA) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoDependencia.getDescricaoTipoDependenciaQuimica());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    
    public void atualizarTipoDependenciaQuimica(TipoDependenciaQuimica tipoDependencia) {
        String sql = "UPDATE TIPO_DEPENDENCIA_QUIMICA SET descricao_TIPO_DEPENDENCIA_QUIMICA = ? WHERE id_TIPO_DEPENDENCIA_QUIMICA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoDependencia.getDescricaoTipoDependenciaQuimica());
            stmt.setInt(2, tipoDependencia.getIdTipoDependenciaQuimica());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

 
    public void excluirTipoDependenciaQuimica(int idTipoDependenciaQuimica) {
        String sql = "DELETE FROM TIPO_DEPENDENCIA_QUIMICA WHERE id_TIPO_DEPENDENCIA_QUIMICA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTipoDependenciaQuimica);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    
    public List<TipoDependenciaQuimica> listarTiposDependenciaQuimica() {
        List<TipoDependenciaQuimica> tiposDependencia = new ArrayList<>();
        String sql = "SELECT * FROM TIPO_DEPENDENCIA_QUIMICA";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TipoDependenciaQuimica tipoDependencia = new TipoDependenciaQuimica(
                        rs.getInt("id_TIPO_DEPENDENCIA_QUIMICA"),
                        rs.getString("descricao_TIPO_DEPENDENCIA_QUIMICA")
                );

                tiposDependencia.add(tipoDependencia);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return tiposDependencia;
    }

    
}
