/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Administrador;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    public boolean cadastrar(Administrador administrador) {
        String sql = "INSERT INTO administrador (idUsu) VALUES (?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setInt(1, administrador.getId()); // id herdado de Usuario = idUsu
            comando.executeUpdate();

            try (ResultSet chaves = comando.getGeneratedKeys()) {
                if (chaves.next()) administrador.setIdAdm(chaves.getInt(1));
            }

            System.out.println("Administrador cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar administrador: " + e.getMessage());
            return false;
        }
    }

    public void excluir(int idAdministrador) {
        String sql = "DELETE FROM administrador WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idAdministrador);
            comando.executeUpdate();

            System.out.println("Administrador excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir administrador: " + e.getMessage());
        }
    }

    public Administrador buscarPorIdUsu(int idUsu) {
        String sql = "SELECT a.id AS idAdministrador, u.* FROM administrador a "
                + "JOIN usuario u ON u.id = a.idUsu WHERE a.idUsu = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idUsu);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) return mapear(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar administrador: " + e.getMessage());
        }
        return null;
    }

    private Administrador mapear(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario(
                resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getString("email"),
                resultado.getString("senha"),
                resultado.getString("telefone"));
        return new Administrador(resultado.getInt("idAdministrador"), usuario);
    }
    
    public List<Administrador> listarTodos() {
    List<Administrador> lista = new ArrayList<>();
    String sql = "SELECT a.id AS idAdministrador, u.* FROM administrador a "
            + "JOIN usuario u ON u.id = a.idUsu";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement comando = conexao.prepareStatement(sql);
         ResultSet resultado = comando.executeQuery()) {

        while (resultado.next()) lista.add(mapear(resultado));

    } catch (SQLException e) {
        System.out.println("Erro ao listar administradores: " + e.getMessage());
    }
    return lista;
}
    
}