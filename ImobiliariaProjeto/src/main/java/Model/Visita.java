/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author aluno.saolucas
 */
public class Visita {
    private int idVisita;
    private int idCli;
    private int idImo;
    private Cliente cliente;
    private Imovel imovel;
    private LocalDateTime data;

    public Visita(){}

    public Visita(int idVisita, int idCli, int idImo, LocalDateTime data) {
        this.idVisita = idVisita;
        this.idCli = idCli;
        this.idImo = idImo;
        this.data = data;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public int getIdImo() {
        return idImo;
    }

    public void setIdImo(int idImo) {
        this.idImo = idImo;
    }

    public Cliente getIdCliente() {
        return cliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.cliente = cliente;
        if (cliente != null) this.idCli = cliente.getIdCliente();
    }

    public Imovel getIdImovel() {
        return imovel;
    }

    public void setIdImovel(Imovel idImovel) {
        this.imovel = imovel;
        if (imovel != null) this.idImo = imovel.getIdImovel();
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    

    
    
}
