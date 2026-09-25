/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Visita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {
     
    public void agendar(Visita visita) {
        String sql = "INSERT INTO visita (idCli, idImo, data) VALUES (?,?,?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, visita.getIdCli());
            comando.setInt(2, visita.getIdImo());
            comando.setString(3, visita.getData().toString());
            comando.executeUpdate();


            System.out.println("Visita agendada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao agendar visita: " + e.getMessage());
        }
    }
    
    
    public void cancelar(int idVisita) {
        String sql = "DELETE FROM visita WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idVisita);
            comando.executeUpdate();

            System.out.println("Visita cancelada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar visita: " + e.getMessage());
        }
    }

    
    public List<Visita> listarPorCliente(int idCli) {
        List<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visita WHERE idCli = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idCli);
            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    visitas.add(new Visita(
                            resultado.getInt("id"),
                            resultado.getInt("idCli"),
                            resultado.getInt("idImo"),
                            LocalDateTime.parse(resultado.getString("data"))
                    ));
                }
            }
               
        } catch (SQLException e) {
            System.out.println("Erro ao listar visitas: " + e.getMessage());
        }
        return visitas;
    }
    
    
}
