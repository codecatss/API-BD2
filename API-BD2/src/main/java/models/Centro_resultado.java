/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author mikaela.begotti
 */
public class Centro_resultado {
    private String nome;
    private String codigo_cr;
    private String sigla;
    private Status_cliente status_cr; //reutilizando o enum do status clientes.
    
    public Status_cliente getStatus_crObj() {
        return status_cr;
    }
    
    public String getStatus_cr() {
        return status_cr.name();
    }

  
    public void setStatus_cr(String status_cr) {
        this.status_cr = Status_cliente.valueOf(status_cr.toLowerCase());
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the codigo_cr
     */
    public String getCodigo_cr() {
        return codigo_cr;
    }

    /**
     * @param codigo_cr the codigo_cr to set
     */
    public void setCodigo_cr(String codigo_cr) {
        this.codigo_cr = codigo_cr;
    }

    /**
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla the sigla to set
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
}
