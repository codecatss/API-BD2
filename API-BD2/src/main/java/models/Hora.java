package models;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import models.TipoHora;

public class Hora {

    private Integer id;
    private String username_lancador;
    private Timestamp data_hora_inicio;
    private Timestamp data_hora_fim;
    private String tipo;
    private String status;
    private Integer centro_resultado;
    private String justificativa;
    private String projeto;

    public Hora(String username_lancador, Timestamp data_hora_inicio, Timestamp data_hora_fim, String tipo, Integer centro_resultado, String projeto) {
        this.username_lancador = username_lancador;
        this.data_hora_inicio = data_hora_inicio;
        this.data_hora_fim = data_hora_fim;
        this.tipo = tipo;
        this.centro_resultado = centro_resultado;
        this.projeto = projeto;
    }

    public String getStatus() {
        return status;
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
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
    public Timestamp getData_hora_inicio() throws ParseException {
        return data_hora_inicio;
    }

    /**
     * @param data_hora_inicio the data_hora_inicio to set
     */
    public void setData_hora_inicio(String time) throws ParseException {
        String dataHoraString = time;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataHoraDate = dateFormat.parse(dataHoraString);
        Timestamp timestamp = new Timestamp(dataHoraDate.getTime());
        this.data_hora_inicio = timestamp;
    }

    /**
     * @return the data_hora_fim
     */
    public Timestamp getData_hora_fim() throws ParseException {
        return data_hora_fim;
    }

    /**
     * @param data_hora_fim the data_hora_fim to set
     */
    public void setData_hora_fim(String time) throws ParseException {
        String dataHoraString = time;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataHoraDate = dateFormat.parse(dataHoraString);
        Timestamp timestamp = new Timestamp(dataHoraDate.getTime());
        this.data_hora_fim = timestamp;
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
        if (tipo.equals(TipoHora.EXTRA.name()) || tipo.equals(TipoHora.SOBREAVISO.name())) {
            this.tipo = tipo;
        }
    }

    /**
     * @return the cod_cr
     */
    public int getCentro_resultado() {
        return centro_resultado;
    }

    /**
     * @param cod_cr the cod_cr to set
     */
    public void setCentro_resultado(int centro_resultado) {
        this.centro_resultado = centro_resultado;
    }

    /**
     * @return the justificativa
     */
    public String getJustificativa() {
        return justificativa;
    }

    /**
     * @param justificativa the justificativa to set
     */
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    /**
     * @return the projeto
     */
    public String getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

}
