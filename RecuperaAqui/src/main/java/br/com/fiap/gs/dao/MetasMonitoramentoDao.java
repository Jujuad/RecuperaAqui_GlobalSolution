package br.com.fiap.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.gs.model.MetasMonitoramento;
import br.com.fiap.gs.model.Usuario;


public class MetasMonitoramentoDao {
    private Connection connection;

    
    public MetasMonitoramentoDao(Connection connection) {
        this.connection = connection;
    }

    
    public void inserirMetaMonitoramento(MetasMonitoramento meta) {
        String sql = "INSERT INTO METAS_MONITORAMENTO (qtde_substancia_meta, data_inicio_meta, data_fim_meta, id_usuario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, meta.getQtdeSubstanciaMeta());
            stmt.setTimestamp(2, meta.getDataInicioMeta());
            stmt.setTimestamp(3, meta.getDataFimMeta());
            stmt.setInt(4, meta.getIdUsuario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    
    public void atualizarMetaMonitoramento(MetasMonitoramento meta) {
        String sql = "UPDATE METAS_MONITORAMENTO SET qtde_substancia_meta = ?, data_inicio_meta = ?, data_fim_meta = ? WHERE id_meta_monitoramento = ? AND id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, meta.getQtdeSubstanciaMeta());
            stmt.setTimestamp(2, meta.getDataInicioMeta());
            stmt.setTimestamp(3, meta.getDataFimMeta());
            stmt.setInt(4, meta.getIdMetaMonitoramento());
            stmt.setInt(5, meta.getIdUsuario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    
    public void excluirMetaMonitoramento(int idMetaMonitoramento) {
        String sql = "DELETE FROM METAS_MONITORAMENTO WHERE id_meta_monitoramento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMetaMonitoramento);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    
    public List<MetasMonitoramento> pesquisarMetasPorUsuario(int idUsuario) {
        List<MetasMonitoramento> metas = new ArrayList<>();
        String sql = "SELECT * FROM METAS_MONITORAMENTO WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MetasMonitoramento meta = new MetasMonitoramento(
                        rs.getInt("id_meta_monitoramento"),
                        rs.getInt("qtde_substancia_meta"),
                        rs.getTimestamp("data_inicio_meta"),
                        rs.getTimestamp("data_fim_meta"),
                        rs.getInt("id_usuario")
                );

                metas.add(meta);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return metas;
    }

    // Função para enviar notificações personalizadas
    public void enviarNotificacoesPersonalizadas(Usuario usuario) {
        String mensagem = "Olá " + usuario.getNomeUsuario() + ",\n\n"
                + "Você recebeu uma notificação personalizada do RecuperaAqui.\n"
                + "Mensagem: teste\n\n"
                + "Atenciosamente, equipe RecuperaAqui\n";

        // Implementação da lógica de envio de notificações personalizadas serao inclusas posteriormente
        
        enviarNotificacaoPorMeioEscolhido(usuario, mensagem);
    }

    // Função genérica para enviar notificações
    private void enviarNotificacaoPorMeioEscolhido(Usuario usuario, String mensagem) {

        System.out.println("Enviando notificação para: " + usuario.getEmailUsuario());
        System.out.println("Mensagem: " + mensagem);
    }
    
}
