/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.imobiliariaprojeto;

import DAO.Conexao;
import View.Login;

/**
 *
 * @author aluno.saolucas
 */
public class ImobiliariaProjeto {

    public static void main(String[] args) {
        Conexao.inicializarBanco();
        Login tela = new Login();
        tela.setVisible(true);
    }
}
