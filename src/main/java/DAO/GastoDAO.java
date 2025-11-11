package dao;

import model.Gasto;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexao.Conexao;

public class GastoDAO {

    public void inserirGasto(Usuario usuario, String descricao, double valor, String categoria) {
        String sql = "INSERT INTO gasto (descricao, valor, categoria, idUsuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descricao);
            stmt.setDouble(2, valor);
            stmt.setString(3, categoria);
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();
            System.out.println("Gasto inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir gasto: " + e.getMessage());
        }
    }

    public List<Gasto> listarGastos(int idUsuario) {

        List<Gasto> lista = new ArrayList<>();

        String sql = "SELECT * FROM gasto WHERE idUsuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Gasto g = new Gasto(
                        rs.getInt("idGasto"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("categoria"),
                        rs.getInt("idUsuario")
                );

                lista.add(g);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar gastos: " + e.getMessage());
        }

        return lista;
    }
}
