/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Proprietario;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProprietarioDAO {

    public void cadastrar(Proprietario proprietario) {
        String sql = "INSERT INTO proprietario (idUsu) VALUES (?)";

        try (Connection conexao = Conexao.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, proprietario.getId());
            comando.executeUpdate();

            System.out.println("Proprietário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar proprietário: " + e.getMessage());
        }
    }

    public void excluir(int idProprietario) {
        String sql = "DELETE FROM proprietario WHERE id = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idProprietario);
            comando.executeUpdate();

            System.out.println("Proprietário excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir proprietário: " + e.getMessage());
        }
    }
    
    public Proprietario buscarPorIdUsu(int idUsu) {
        String sql = "SELECT p.id AS idProprietario, u.* FROM proprietario p "
                + "JOIN usuario u ON u.id = p.idUsu WHERE p.idUsu = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idUsu);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) return mapear(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar proprietário: " + e.getMessage());
        }
        return null;
    }

    private Proprietario mapear(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario(
                resultado.getInt("id"), resultado.getString("nome"),
                resultado.getString("email"), resultado.getString("senha"),
                resultado.getString("telefone"));
        return new Proprietario(resultado.getInt("idProprietario"), usuario);
    }

}
