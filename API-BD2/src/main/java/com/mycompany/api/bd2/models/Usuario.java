package com.mycompany.api.bd2.models;

import com.mycompany.api.bd2.models.Funcao;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danko
 */
public class Usuario {
    private static Usuario instancia = null;
    private String username = "";
    private String nome = "";
    private String senha = "";
    private String status = "";
    private Funcao cargo;

    private Usuario() {}

    public static Usuario getInstance() {
        if (instancia == null) {
            instancia = new Usuario();
        }
        return instancia;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Funcao getCargoObj() {
        return cargo;
    }
    public String getCargo() {
        return cargo.name();
    }
    
    

    public void setCargo(String cargo) {
        this.cargo = Funcao.valueOf(cargo.toLowerCase());
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

    public void logout(){
        this.username = "";
        this.nome = "";
        this.senha = "";
        this.status = "";   
    }
        
}
