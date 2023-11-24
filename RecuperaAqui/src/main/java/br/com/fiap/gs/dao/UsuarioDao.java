package br.com.fiap.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.gs.model.Usuario;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }

    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (nome_usuario, email_usuario, senha_usuario, dt_nasc_usuario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getEmailUsuario());
            stmt.setString(3, usuario.getSenhaUsuario());
            stmt.setTimestamp(4, usuario.getDtNascUsuario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE USUARIO SET nome_usuario = ?, email_usuario = ?, senha_usuario = ?, dt_nasc_usuario = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getEmailUsuario());
            stmt.setString(3, usuario.getSenhaUsuario());
            stmt.setTimestamp(4, usuario.getDtNascUsuario());
            stmt.setInt(5, usuario.getIdUsuario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirUsuario(int idUsuario) {
        String sql = "DELETE FROM USUARIO WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario pesquisarUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getTimestamp("dt_nasc_usuario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getTimestamp("dt_nasc_usuario")
                );

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario realizarLogin(String email, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIO WHERE email_usuario = ? AND senha_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getTimestamp("dt_nasc_usuario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
