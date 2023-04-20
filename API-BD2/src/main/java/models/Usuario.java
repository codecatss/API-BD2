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
    private String user_name = "";
    private String nome = "";
    private String senha = "";
    private String status = "";
    private Funcao cargo;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    
        
}
