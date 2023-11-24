package br.com.fiap.gs.service;

import br.com.fiap.gs.dao.UsuarioDao;
import br.com.fiap.gs.model.Usuario;

import java.sql.Connection;
import java.util.List;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService(Connection connection) {
        this.usuarioDao = new UsuarioDao(connection);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioDao.inserirUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDao.atualizarUsuario(usuario);
    }

    public void excluirUsuario(int idUsuario) {
        usuarioDao.excluirUsuario(idUsuario);
    }

    public Usuario pesquisarUsuarioPorId(int idUsuario) {
        return usuarioDao.pesquisarUsuarioPorId(idUsuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDao.listarUsuarios();
    }

    public Usuario realizarLogin(String email, String senha) {
        return usuarioDao.realizarLogin(email, senha);
    }
}
