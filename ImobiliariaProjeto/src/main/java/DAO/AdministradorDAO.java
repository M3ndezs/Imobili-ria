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

public class AdministradorDAO {
    
    
    public void cadastrar(Administrador administrador) {
        String sql = "INSERT INTO administrador (idUsu) VALUES (?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, administrador.getId());
            comando.executeUpdate();

            System.out.println("Administrador cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar administrador: " + e.getMessage());
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
}
