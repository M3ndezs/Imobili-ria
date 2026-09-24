/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aluno.saolucas
 */
public class Imovel {

    private int idImovel;
    private int idProp;
    private Proprietario proprietario;
    private String descricao;
    private boolean tipo;     // true = venda, false = aluguel
    private double preco;
    private String endereco;
    private boolean status;   // true = disponivel, false = indisponivel

    public Imovel() {
    }

    public Imovel(int id, int idProp, String descricao, boolean tipo, double preco, String endereco, boolean status) {
        this.idImovel = idImovel;
        this.idProp = idProp;
        this.descricao = descricao;
        this.tipo = tipo;
        this.preco = preco;
        this.endereco = endereco;
        this.status = status;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int id) {
        this.idImovel = idImovel;
    }

    public int getIdProp() {
        return idProp;
    }

    public void setIdProp(int idProp) {
        this.idProp = idProp;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
        if (proprietario != null) {
            this.idProp = proprietario.getIdProprietario();
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getTipoDescricao() {
        return tipo ? "Venda" : "Aluguel";
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusDescricao() {
        return status ? "Disponível" : "Indisponível";
    }

    @Override
    public String toString() {
        return idImovel + " - " + descricao + " - " + endereco;
    }
}
