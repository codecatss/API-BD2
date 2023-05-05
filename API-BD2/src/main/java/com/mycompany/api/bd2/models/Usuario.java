package com.mycompany.api.bd2.models;

import com.mycompany.api.bd2.models.Funcao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danko
 */
public class Usuario {
    //private int id_user;
    private String username = "";
    private String nome = "";
    private String senha = "";
    private Funcao cargo;
    private String status = "";
    //private String hash;
   
    
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

    /**
     * @return the id_user
     */
    /*public int getId_user() {
        return id_user;
    }

    
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getHash() {
        
        return hash;
    }
    public void setHash(String senha){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes());
            String hashSenha = Base64.getEncoder().encodeToString(hashBytes);
            this.hash = hashSenha;
    } 
        catch (NoSuchAlgorithmException e) {
    // tratamento de exceção
    }
        
       
    }*/
    
     @Override
    public String toString() {
        return username;
    }

}
