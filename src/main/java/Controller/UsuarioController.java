package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {

    private Usuario usuarioAtual;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void criarUsuario(String nome) {
        usuarioDAO.inserirUsuario(nome);
        System.out.println("Usuário criado com sucesso!");
    }

    public boolean carregarUsuario(String nome) {
        Usuario usuario = usuarioDAO.buscarPorNome(nome);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return false;
        }

        this.usuarioAtual = usuario;
        System.out.println("Usuário carregado: " + usuario.getNome());
        return true;
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }
}