/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Imovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno.saolucas
 */
public class ImovelDAO {
      public void cadastrar(Imovel imovel) {
        String sql = "INSERT INTO imovel (idProp, descricao, tipo, preco, endereco, status) VALUES (?,?,?,?,?,?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            preencher(comando, imovel);
            comando.executeUpdate();

            System.out.println("Imóvel cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar imóvel: " + e.getMessage());
        }
    }
      

    public void alterar(Imovel imovel) {
        String sql = "UPDATE imovel SET idProp=?, descricao=?, tipo=?, preco=?, endereco=?, status=? WHERE id=?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            preencher(comando, imovel);
            comando.setInt(7, imovel.getIdImovel());
            comando.executeUpdate();

            System.out.println("Imóvel atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar imóvel: " + e.getMessage());
        }
    }
    

    public void excluir(int id) {
        String sql = "DELETE FROM imovel WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);
            comando.executeUpdate();

            System.out.println("Imóvel excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir imóvel: " + e.getMessage());
        }
    }

    public List<Imovel> buscar(String termo) {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imovel WHERE (descricao LIKE ? OR endereco LIKE ?) AND status = 1";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            String like = "%" + termo + "%";
            comando.setString(1, like);
            comando.setString(2, like);

            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) imoveis.add(mapear(resultado));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar imóveis: " + e.getMessage());
        }
        return imoveis;
    }

    public List<Imovel> listarPorProprietario(int idProp) {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imovel WHERE idProp = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, idProp);
            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) imoveis.add(mapear(resultado));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar imóveis do proprietário: " + e.getMessage());
        }
        return imoveis;
    }

    public List<Imovel> listar() {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imovel";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) imoveis.add(mapear(resultado));

        } catch (SQLException e) {
            System.out.println("Erro ao listar imóveis: " + e.getMessage());
        }
        return imoveis;
    }

    private void preencher(PreparedStatement comando, Imovel imovel) throws SQLException {
        comando.setInt(1, imovel.getIdProp());
        comando.setString(2, imovel.getDescricao());
        comando.setBoolean(3, imovel.isTipo());
        comando.setDouble(4, imovel.getPreco());
        comando.setString(5, imovel.getEndereco());
        comando.setBoolean(6, imovel.isStatus());
    }

    private Imovel mapear(ResultSet resultado) throws SQLException {
        return new Imovel(
                resultado.getInt("id"),
                resultado.getInt("idProp"),
                resultado.getString("descricao"),
                resultado.getBoolean("tipo"),
                resultado.getDouble("preco"),
                resultado.getString("endereco"),
                resultado.getBoolean("status")
        );
    }
    
    
}
