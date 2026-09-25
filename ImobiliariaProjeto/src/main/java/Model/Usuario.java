/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aluno.saolucas
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public boolean fazerLogin(String emailDigitado, String senhaDigitada) {
        return this.email != null && this.senha != null
                && this.email.equalsIgnoreCase(emailDigitado)
                && this.senha.equals(senhaDigitada);
    }

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public class UsuarioComPapel {

        private final Usuario usuario;
        private final String papel;

        public UsuarioComPapel(Usuario usuario, String papel) {
            this.usuario = usuario;
            this.papel = papel;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public String getPapel() {
            return papel;
        }

    }
}
