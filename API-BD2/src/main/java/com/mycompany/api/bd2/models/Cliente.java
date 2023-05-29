package com.mycompany.api.bd2.models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mikaela.begotti
 */
public class Cliente {
    private Integer cnpj;
    private String razao_social;
    private Status_cliente status_cliente;

    /**
     * @return the razao_social
     */
    public String getRazao_social() {
        return razao_social;
    }

    /**
     * @param razao_social the razao_social to set
     */
    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    /**
     * @return the cnpj
     */
    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    
    public Status_cliente getStatus_clienteObj() {
        return status_cliente;
    }
    
    public String getStatus_cliente() {
        return status_cliente.name();
    }

  
    public void setStatus_cliente(String status_clientes) {
        this.status_cliente = Status_cliente.valueOf(status_clientes.toLowerCase());
    }
}
