package com.mycompany.api.bd2.models;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import com.mycompany.api.bd2.models.TipoHora;

public class Hora {
    
    private Hora instancia = null;
    private Integer id;
    private String cod_cr;
    private String username_lancador;
    private int cnpj_cliente;
    private String nome_cliente;
    private Timestamp data_hora_inicio;
    private Timestamp data_hora_fim;
    private String tipo;
    private String justificativa_lancamento;
    private String projeto;
    private String solicitante;
    private String username_aprovador;
    private String justificativa_negacao;
    private StatusAprovacao status_aprovacao;
    private boolean feriado;
    
    private static Hora instance = null;
    public static Hora getInstance() {
        if (instance == null) {
            instance = new Hora();
        }
        return instance;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    
    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
    
    
    public StatusAprovacao getStatus_aprovacaoObj() {
        return status_aprovacao;
    }
    
    public String getStatus_aprovacao() {
        return status_aprovacao.name();
    }
    
    public void setStatus_aprovacao(String status_aprovacao){
        this.status_aprovacao = StatusAprovacao.valueOf(status_aprovacao);
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
    public Timestamp getData_hora_inicio(){
        return data_hora_inicio;
    }

    /**
     * @param data_hora_inicio the data_hora_inicio to set
     */
    public void setData_hora_inicio(String time) throws ParseException{
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = formatter.parse(time);
        this.data_hora_inicio = new Timestamp(date.getTime());
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
    public void setData_hora_fim(String time) throws ParseException{
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = formatter.parse(time);
        this.data_hora_fim = new Timestamp(date.getTime());
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
        if (tipo.equals("EXTRA")||tipo.equals("HORA EXTRA")) {
            this.tipo = TipoHora.EXTRA.name();
        }else if(tipo.equals("SOBREAVISO")){
            this.tipo = TipoHora.SOBREAVISO.name();            
        }
        else{
            System.out.println(tipo);
        }
        
    }

    public String getProjeto() {
        return projeto;
    }
    

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(String projeto) {
        this.projeto = projeto;
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

    /**
     * @return the cnpj_cliente
     */
    public int getCnpj_cliente() {
        return cnpj_cliente;
    }

    /**
     * @param cnpj_cliente the cnpj_cliente to set
     */
    public void setCnpj_cliente(int cnpj_cliente) {
        this.cnpj_cliente = cnpj_cliente;
    }

    /**
     * @return the justificativa_lancamento
     */
    public String getJustificativa_lancamento() {
        return justificativa_lancamento;
    }

    /**
     * @param justificativa_lancamento the justificativa_lancamento to set
     */
    public void setJustificativa_lancamento(String justificativa_lancamento) {
        this.justificativa_lancamento = justificativa_lancamento;
    }

    /**
     * @return the username_aprovador
     */
    public String getUsername_aprovador() {
        return username_aprovador;
    }

    /**
     * @param username_aprovador the username_aprovador to set
     */
    public void setUsername_aprovador(String username_aprovador) {
        this.username_aprovador = username_aprovador;
    }

    /**
     * @return the justificativa_negacao
     */
    public String getJustificativa_negacao() {
        return justificativa_negacao;
    }

    /**
     * @param justificativa_negacao the justificativa_negacao to set
     */
    public void setJustificativa_negacao(String justificativa_negacao) {
        this.justificativa_negacao = justificativa_negacao;
    }

    /**
     * @return the feriado
     */
    public boolean getFeriado() {
        return feriado;
    }
    
    /**
     * @param feriado the feriado to set
     */
    public void setFeriado(boolean feriado) {
        this.feriado = feriado;
    }

    public void beNull(){
        this.cnpj_cliente= 000;
        this.cod_cr=null;
        this.data_hora_fim=null;
        this.data_hora_inicio=null;
        this.id=null;
        this.justificativa_lancamento=null;
        this.justificativa_negacao=null;
        this.nome_cliente=null;
        this.projeto=null;
        this.solicitante=null;
        this.status_aprovacao=null;
        this.tipo=null;
        this.username_aprovador=null;
        this.username_lancador=null;
        this.feriado=false;
                
    }
}
