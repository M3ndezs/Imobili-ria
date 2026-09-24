/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Cliente extends Usuario{
    private int idCliente;
    
    public Cliente(){}
    
    public Cliente(int idCliente, Usuario usuario){
        super(usuario.getId(), usuario.getNome(), usuario.getEmail(),usuario.getSenha(), usuario.getTelefone());
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
     public int getIdUsu() { return getId(); }

    public void agendarVisita(Imovel imovel) {
        // regra de negócio delegada ao VisitaController/DAO
    }
    
}
