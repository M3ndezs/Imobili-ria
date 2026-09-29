/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Administrador extends Usuario {
    
   
    private int idAdm;

    public Administrador() {
    }

    public Administrador(int idAdm, Usuario usuario) {   // <-- trocado idProprietario por idAdm
        super(usuario.getId(), usuario.getNome(), usuario.getEmail(),
              usuario.getSenha(), usuario.getTelefone());

        this.idAdm = idAdm;   // agora bate certinho com o parâmetro
    }

    public int getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(int idAdm) {
        this.idAdm = idAdm;
    }

    public int getIdUsu() {
        return getId();
    }
}
