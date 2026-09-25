/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cliente;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ClienteDAO {
   
    public void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente (idUsu) VALUES (?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, cliente.getId());
            comando.executeUpdate();

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
    
     public void excluir(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idCliente);
            comando.executeUpdate();

            System.out.println("Cliente excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

     public Cliente buscarPorIdUsu(int idUsu) {
        String sql = "SELECT c.id AS idCliente, u.* FROM cliente c "
                + "JOIN usuario u ON u.id = c.idUsu WHERE c.idUsu = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idUsu);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) return mapear(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }
     
     
     
      private Cliente mapear(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario(
                resultado.getInt("id"), resultado.getString("nome"),
                resultado.getString("email"), resultado.getString("senha"),
                resultado.getString("telefone"));
        return new Cliente(resultado.getInt("idCliente"), usuario);
    }
}
