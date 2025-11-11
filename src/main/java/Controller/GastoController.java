package controller;

import dao.GastoDAO;
import model.Gasto;
import model.Usuario;

import java.util.List;

public class GastoController {

    private GastoDAO gastoDAO = new GastoDAO();

    public void adicionarGasto(Usuario usuario, String descricao, double valor, String categoria) {
        gastoDAO.inserirGasto(usuario, descricao, valor, categoria);
    }

    public List<Gasto> listarGastos(Usuario usuario) {
        return gastoDAO.listarGastos(usuario.getId());
    }
}