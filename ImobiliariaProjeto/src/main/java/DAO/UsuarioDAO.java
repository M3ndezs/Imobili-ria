/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    
    public boolean cadastrar( Usuario usuario){
           String sql = "INSERT INTO usuario (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        comando.setString(1, usuario.getNome());
        comando.setString(2, usuario.getEmail());
        comando.setString(3, usuario.getSenha());
        comando.setString(4, usuario.getTelefone());
        comando.executeUpdate();

        try (ResultSet chaves = comando.getGeneratedKeys()) {
            if (chaves.next()) usuario.setId(chaves.getInt(1));
        }

        System.out.println("Usuário cadastrado com sucesso!");
        return true;
    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        return false;
    }
    }
    
    
     public boolean alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, email=?, senha=?, telefone=? WHERE id=?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement comando = conexao.prepareStatement(sql)) {

        comando.setString(1, usuario.getNome());
        comando.setString(2, usuario.getEmail());
        comando.setString(3, usuario.getSenha());
        comando.setString(4, usuario.getTelefone());
        comando.setInt(5, usuario.getId());
        comando.executeUpdate();

        System.out.println("Usuário atualizado com sucesso!");
        return true;
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        return false;
    }
    }

    
      public void excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);
            comando.executeUpdate();

            System.out.println("Usuário excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

      
       private Usuario mapear(ResultSet resultado) throws SQLException {
        return new Usuario(
                resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getString("email"),
                resultado.getString("senha"),
                resultado.getString("telefone")
        );
    }
       
      
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, email);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) return mapear(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por e-mail: " + e.getMessage());
        }
        return null;
    }
    
    
        public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) usuarios.add(mapear(resultado));

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }
        
    }
    

