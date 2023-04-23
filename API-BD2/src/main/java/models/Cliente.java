package models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mikaela.begotti
 */
public class Cliente {
    private String razao_social;
    private Status_clientes status_clientes;
    private long cnpj;

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

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    
    public Status_clientes getStatus_clientesObj() {
        return status_clientes;
    }
    
    public String getStatus_clientes() {
        return status_clientes.name();
    }

  
    public void setStatus_clientes(String status_clientes) {
        this.status_clientes = Status_clientes.valueOf(status_clientes.toLowerCase());
    }
}
