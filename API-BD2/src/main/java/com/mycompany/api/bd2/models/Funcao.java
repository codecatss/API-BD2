/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.models;

/**
 *
 * @author danko
 */
public enum Funcao {
    admin, colaborador, gestor;    

    public boolean equals(Funcao cargo_usuario, Funcao cargo_desejado) {
        return cargo_desejado == cargo_usuario;
    }
}
