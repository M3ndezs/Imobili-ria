/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aluno.saolucas
 */
public class Conexao {

    private static final String url = "jdbc:sqlite:Imobiliaria.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static void inicializarBanco() {
        String sqlUsuario
                = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, email TEXT NOT NULL UNIQUE, senha TEXT NOT NULL, telefone TEXT)";

        String sqlCliente
                = "CREATE TABLE IF NOT EXISTS cliente (id INTEGER PRIMARY KEY AUTOINCREMENT,idUsu INTEGER NOT NULL UNIQUE, FOREIGN KEY (idUsu) REFERENCES usuario(id))";

        String sqlProprietario 
                = "CREATE TABLE IF NOT EXISTS proprietario ( id INTEGER PRIMARY KEY AUTOINCREMENT,idUsu INTEGER NOT NULL UNIQUE, FOREIGN KEY (idUsu) REFERENCES usuario(id))";

        String sqlAdministrador
                = "CREATE TABLE IF NOT EXISTS administrador (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsu INTEGER NOT NULL UNIQUE, FOREIGN KEY (idUsu) REFERENCES usuario(id))";

        String sqlImovel
                = "CREATE TABLE IF NOT EXISTS imovel (id INTEGER PRIMARY KEY AUTOINCREMENT, idProp INTEGER NOT NULL, descricao TEXT, tipo INTEGER, preco REAL, endereco TEXT, status INTEGER, FOREIGN KEY (idProp) REFERENCES proprietario(id))";

        String sqlVisita
                = "CREATE TABLE IF NOT EXISTS visita (id INTEGER PRIMARY KEY AUTOINCREMENT, idCli INTEGER NOT NULL, idImo INTEGER NOT NULL, data TEXT NOT NULL, FOREIGN KEY (idCli) REFERENCES cliente(id), FOREIGN KEY (idImo) REFERENCES imovel(id))";

        try (Connection conexao = conectar(); Statement comando = conexao.createStatement()) {
            comando.execute(sqlUsuario);
            comando.execute(sqlCliente);
            comando.execute(sqlProprietario);
            comando.execute(sqlAdministrador);
            comando.execute(sqlImovel);
            comando.execute(sqlVisita);
            System.out.println("banco de dados conectado!");
            System.out.println("tabelas verificadas!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar!! " + e.getMessage());
        }
    }
}
