/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author mikaela.begotti
 */
public class Contrato {
    private int id;
    private String cod_cr; 
    private long cnpj_cliente; 

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cod_cr
     */
    public String getCod_cr() {
        return cod_cr;
    }

    /**
     * @param cod_cr the cod_cr to set
     */
    public void setCod_cr(String cod_cr) {
        this.cod_cr = cod_cr;
    }

    /**
     * @return the cnpj_cliente
     */
    public long getCnpj_cliente() {
        return cnpj_cliente;
    }

    /**
     * @param cnpj_cliente the cnpj_cliente to set
     */
    public void setCnpj_cliente(long cnpj_cliente) {
        this.cnpj_cliente = cnpj_cliente;
    }
}