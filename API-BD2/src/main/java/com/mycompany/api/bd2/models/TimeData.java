/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.models;

/**
 *
 * @author mikaela.begotti
 */
public class TimeData {
   
    private int horaInicio;
    private int minutoInicio;
    private int horaFim;
    private int minutoFim;

    private static TimeData instance = null;

    private TimeData() {}

    public static TimeData getInstance() {
        if(instance == null) {
            instance = new TimeData();
        }
        return instance;
    }

    public void setTimeData(int hora_inicio, int min_inicio, int hora_fim, int min_fim) {
        this.horaInicio = hora_inicio;
        this.minutoInicio = min_inicio;
        this.horaFim = hora_fim;
        this.minutoFim = min_fim;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getMinutoInicio() {
        return minutoInicio;
    }

    public int getHoraFim() {
        return horaFim;
    }

    public int getMinutoFim() {
        return minutoFim;
    }

}
