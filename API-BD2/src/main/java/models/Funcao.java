/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author danko
 */
public enum Funcao {
    ADMIN, COLABORADOR, GESTOR;    

    public boolean equals(Funcao cargo_usuario, Funcao cargo_desejado) {
        return cargo_desejado == cargo_usuario;
    }
}