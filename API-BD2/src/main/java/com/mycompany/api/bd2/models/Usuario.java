package com.mycompany.api.bd2.models;


import com.mycompany.api.bd2.models.Funcao;

public class Usuario {
    private static Usuario instancia = null;
    private String user_name = "";
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
    
    public void logout() {
        this.nome = null;
        this.senha = null;
    }
}
