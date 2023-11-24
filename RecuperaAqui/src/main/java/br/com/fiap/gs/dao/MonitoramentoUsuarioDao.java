package br.com.fiap.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.gs.model.MonitoramentoUsuario;
import br.com.fiap.gs.model.Usuario;

public class MonitoramentoUsuarioDao {
    private Connection connection;

    
    public MonitoramentoUsuarioDao(Connection connection) {
        this.connection = connection;
    }

    
    public void inserirMonitoramentoUsuario(MonitoramentoUsuario monitoramento) {
        String sql = "INSERT INTO MONITORAMENTO_USUARIO (qtd_consumo_diario_atual, data_inicio_monitoramento, data_fim_monitoramento, id_usuario, id_TIPO_DEPENDENCIA_QUIMICA) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, monitoramento.getQtdConsumoDiarioAtual());
            stmt.setTimestamp(2, monitoramento.getDataInicioMonitoramento());
            stmt.setTimestamp(3, monitoramento.getDataFimMonitoramento());
            stmt.setInt(4, monitoramento.getIdUsuario());
            stmt.setInt(5, monitoramento.getIdTipoDependenciaQuimica());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    
    public void atualizarMonitoramentoUsuario(MonitoramentoUsuario monitoramento) {
        String sql = "UPDATE MONITORAMENTO_USUARIO SET qtd_consumo_diario_atual = ?, data_inicio_monitoramento = ?, data_fim_monitoramento = ? WHERE id_dependencia_user = ? AND id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, monitoramento.getQtdConsumoDiarioAtual());
            stmt.setTimestamp(2, monitoramento.getDataInicioMonitoramento());
            stmt.setTimestamp(3, monitoramento.getDataFimMonitoramento());
            stmt.setInt(4, monitoramento.getIdDependenciaUser());
            stmt.setInt(5, monitoramento.getIdUsuario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

   
    public void excluirMonitoramentoUsuario(int idDependenciaUser) {
        String sql = "DELETE FROM MONITORAMENTO_USUARIO WHERE id_dependencia_user = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDependenciaUser);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

   
    public List<MonitoramentoUsuario> pesquisarMonitoramentosPorUsuario(int idUsuario) {
        List<MonitoramentoUsuario> monitoramentos = new ArrayList<>();
        String sql = "SELECT * FROM MONITORAMENTO_USUARIO WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MonitoramentoUsuario monitoramento = new MonitoramentoUsuario(
                        rs.getInt("id_dependencia_user"),
                        rs.getString("qtd_consumo_diario_atual"),
                        rs.getTimestamp("data_inicio_monitoramento"),
                        rs.getTimestamp("data_fim_monitoramento"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_TIPO_DEPENDENCIA_QUIMICA")
                );

                monitoramentos.add(monitoramento);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return monitoramentos;
    }
    
    public int calcularEscalaEmocional(MonitoramentoUsuario monitoramento) {
        // Obter a quantidade de consumo diário atual e o tipo de dependência química do monitoramento
        String qtdConsumoDiario = monitoramento.getQtdConsumoDiarioAtual();
        int tipoDependenciaQuimica = monitoramento.getIdTipoDependenciaQuimica();

        // Convertendo a quantidade de consumo diário para um valor numérico (assumindo que seja um número)
        int consumoDiarioNumerico;
        try {
            consumoDiarioNumerico = Integer.parseInt(qtdConsumoDiario);
        } catch (NumberFormatException e) {
            // Em caso de erro na conversão, use um valor padrão
            consumoDiarioNumerico = 0;
        }

        // Lógica de cálculo com base na quantidade de consumo diário e tipo de dependência química
        int escalaEmocional = 0;

        if (consumoDiarioNumerico > 0) {
            // Se houver consumo diário, a escala emocional é influenciada pelo tipo de dependência química
            switch (tipoDependenciaQuimica) {
                case 1: // Exemplo: Álcool
                    escalaEmocional = consumoDiarioNumerico / 2; 
                    break;
                case 2: // Exemplo: Nicotina
                    escalaEmocional = consumoDiarioNumerico * 2; 
                    break;
                // Add casos conforme necessário para outros tipos de dependência química
                default:
                    escalaEmocional = consumoDiarioNumerico; 
            }
        }

        return escalaEmocional;
    }

    
    public void analisarPadroesEmocionaisConsumoNicotina(Usuario usuario) {
        MonitoramentoUsuarioDao monitoramentoUsuarioDao = new MonitoramentoUsuarioDao(connection);
        List<MonitoramentoUsuario> monitoramentos = monitoramentoUsuarioDao.pesquisarMonitoramentosPorUsuario(usuario.getIdUsuario());

        // Vamos assumir que o estado emocional está associado à quantidade de consumo de nicotina e queremos identificar uma tendência.

        int totalConsumo = 0;
        int totalEmocional = 0;

        for (MonitoramentoUsuario monitoramento : monitoramentos) {
            totalConsumo += Integer.parseInt(monitoramento.getQtdConsumoDiarioAtual());
            totalEmocional += monitoramentoUsuarioDao.calcularEscalaEmocional(monitoramento);
        }

        double mediaConsumo = totalConsumo / (double) monitoramentos.size();
        double mediaEmocional = totalEmocional / (double) monitoramentos.size();

        // Se a média emocional for alta, pode indicar uma tendência de maior consumo.
        if (mediaEmocional > 7) {
            System.out.println("Alerta: Sua média emocional está alta. Isso pode indicar um aumento no consumo de nicotina.");

            if (mediaConsumo > 5) {
                System.out.println("Recomendação: Considere explorar estratégias de gerenciamento de estresse, como meditação ou atividades físicas.");
            }
        }
    }

    public List<String> gerarRelatoriosPersonalizados(Usuario usuario) {
        MonitoramentoUsuarioDao monitoramentoUsuarioDao = new MonitoramentoUsuarioDao(connection);
        List<MonitoramentoUsuario> monitoramentos = monitoramentoUsuarioDao.pesquisarMonitoramentosPorUsuario(usuario.getIdUsuario());
        List<String> relatorios = new ArrayList<>();

        relatorios.add("Relatório de Consumo de Nicotina");
        relatorios.add("Usuário: " + usuario.getNomeUsuario());
        relatorios.add("-----------------------------------");

        for (MonitoramentoUsuario monitoramento : monitoramentos) {
            relatorios.add("Data: " + monitoramento.getDataInicioMonitoramento());
            relatorios.add("Consumo Diário: " + monitoramento.getQtdConsumoDiarioAtual());
            relatorios.add("Escala Emocional: " + monitoramentoUsuarioDao.calcularEscalaEmocional(monitoramento));
            relatorios.add("-----------------------------------");
        }

        return relatorios;
    }


	public List<MonitoramentoUsuario> listarMonitoramentosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}


	public MonitoramentoUsuario pesquisarMonitoramentoUPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
