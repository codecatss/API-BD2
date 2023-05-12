/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.models;

/**
 *
 * @author csous
 */
public class TabelaAprovaçãoGestor {
    
    private String username;
    private int cod_cr;
    private String nome_cr;
    private String empresa;
    private String projeto;
    private TipoHora tipo;
    private String inicio;
    private String fim;
    private String justificativa;
    private String justificativa_lancamento;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCod_cr() {
        return cod_cr;
    }

    public void setCod_cr(String cod_cr) {
        this.cod_cr = Integer.parseInt(cod_cr);
    }

    public String getNome_cr() {
        return nome_cr;
    }

    public void setNome_cr(String nome_cr) {
        this.nome_cr = nome_cr;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public TipoHora getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = TipoHora.valueOf(tipo);
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getJustificativa_lancamento() {
        return justificativa_lancamento;
    }

    public void setJustificativa_lancamento(String justificativa_lancamento) {
        this.justificativa_lancamento = justificativa_lancamento;
    }

    
}
