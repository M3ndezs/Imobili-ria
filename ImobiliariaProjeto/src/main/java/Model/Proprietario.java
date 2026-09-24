/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Proprietario extends Usuario {

    private int idProprietario;

    public Proprietario() {
    }

    public Proprietario(int idProprietario, Usuario usuario) {

        super(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTelefone());

        this.idProprietario = idProprietario;
    }

    public int getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    public int getIdUsu() {
        return getId();
    }
}
