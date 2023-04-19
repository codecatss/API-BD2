
package models;

import java.util.Date;


public class Hora {
    
    private int id;
    private String username_lancador;
    private Date data_hora_inicio; 
    private Date data_hora_fim;
    private String tipo; 

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username_lancador
     */
    public String getUsername_lancador() {
        return username_lancador;
    }

    /**
     * @param username_lancador the username_lancador to set
     */
    public void setUsername_lancador(String username_lancador) {
        this.username_lancador = username_lancador;
    }

    /**
     * @return the data_hora_inicio
     */
    public Date getData_hora_inicio() {
        return data_hora_inicio;
    }

    /**
     * @param data_hora_inicio the data_hora_inicio to set
     */
    public void setData_hora_inicio(int dia, int mes, int ano, int hora, int min) {
        this.data_hora_inicio = new Date(dia,mes,ano,hora,min);
    }

    /**
     * @return the data_hora_fim
     */
    public Date getData_hora_fim() {
        return data_hora_fim;
    }

    /**
     * @param data_hora_fim the data_hora_fim to set
     */
    public void setData_hora_fim(int dia, int mes, int ano, int hora, int min) {
        this.data_hora_fim = new Date(dia,mes,ano,hora,min);
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
