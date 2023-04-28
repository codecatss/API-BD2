/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author mikaela.begotti
 */
public class Integrante {
    private int gestor; 
    private String username_integrante;
    private String cod_cr;

    /**
     * @return the gestor
     */
    public int getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(int gestor) {
        this.gestor = gestor;
    }

    /**
     * @return the username_integrante
     */
    public String getUsername_integrante() {
        return username_integrante;
    }

    /**
     * @param username_integrante the username_integrante to set
     */
    public void setUsername_integrante(String username_integrante) {
        this.username_integrante = username_integrante;
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
}
