/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.models;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author larissa
 */
public class Verba {

    private int cod_verba;
    private String nome_verba;
    private double fator_multiplicacao;
    private Time hora_inicio;
    private Time hora_fim;

    public int getCod_verba() {
        return cod_verba;
    }

    public void setCod_verba(int cod_verba) {
        this.cod_verba = cod_verba;
    }

    public String getNome_verba() {
        return nome_verba;
    }

    public void setNome_verba(String nome_verba) {
        this.nome_verba = nome_verba;
    }

    public double getFator_multiplicacao() {
        return fator_multiplicacao;
    }

    public void setFator_multiplicacao(double fator_multiplicacao) {
        this.fator_multiplicacao = fator_multiplicacao;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Time hora = new Time(formatter.parse(hora_inicio).getTime());
        this.hora_inicio = hora;
    }

    public Time getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(String hora_fim) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Time hora = new Time(formatter.parse(hora_fim).getTime());
        this.hora_fim = hora;
    }
}
