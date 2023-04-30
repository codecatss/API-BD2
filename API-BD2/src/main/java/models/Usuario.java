package models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danko
 */
public class Usuario {

    public static Usuario getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String username = "";
    private String nome = "";
    private String senha = "";
    private Funcao cargo;
    private Status_user status_user;

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

    
    public String getStatus_user() {
        return status_user.name();
    }
    
    public Status_user getStatus_userObj() {
        return status_user;
    }

    
    public void setStatus(String status_user) {
        this.status_user = Status_user.valueOf(status_user);
    }

    
        
}
