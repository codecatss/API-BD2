package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Hora;
import java.util.Set;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Conexao.Conexao;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ClassificarVerba {

    //VERIFICACÃO DIAS DA SEMANA
    public boolean diaUtil(int diaSelecionado) {
        return diaSelecionado != Calendar.SATURDAY && diaSelecionado != Calendar.SUNDAY;
    }

    public boolean diaNaoUtil(int diaSelecionado) {
        return diaSelecionado == Calendar.SATURDAY || diaSelecionado == Calendar.SUNDAY;
    }

    
    //DIFERENÇA EM MINUTOS
    public long calcularDiferencaPorDia(int horaInicio, int minutoInicio) {
        Calendar inicio = Calendar.getInstance();
        inicio.set(Calendar.HOUR_OF_DAY, horaInicio);
        inicio.set(Calendar.MINUTE, minutoInicio);

        Calendar diaFimFixo = Calendar.getInstance();
        diaFimFixo.set(Calendar.HOUR_OF_DAY, 23);
        diaFimFixo.set(Calendar.MINUTE, 59);

        Date inicioDate = inicio.getTime();
        Date fimDate = diaFimFixo.getTime();

        long diferencaMillis = fimDate.getTime() - inicioDate.getTime();
        long diferencaMinutosDia = TimeUnit.MILLISECONDS.toMinutes(diferencaMillis);

        return diferencaMinutosDia;
    }


    public long calcularDiferencaTotal(int horaInicio, int minutoInicio, int horaFim, int minutoFim) {
        Calendar inicio = Calendar.getInstance();
        inicio.set(Calendar.HOUR_OF_DAY, horaInicio);
        inicio.set(Calendar.MINUTE, minutoInicio);

        Calendar fim = Calendar.getInstance();
        fim.set(Calendar.HOUR_OF_DAY, horaFim);
        fim.set(Calendar.MINUTE, minutoFim);

        Date inicioDate = inicio.getTime();
        Date fimDate = fim.getTime();

        long diferencaMillis = fimDate.getTime() - inicioDate.getTime();
        long diferencaMinutosTotal = TimeUnit.MILLISECONDS.toMinutes(diferencaMillis);

        return diferencaMinutosTotal;
    }

    
    public boolean verificarSeMenorDe120(long diferencaMinutosTotal) {
        return diferencaMinutosTotal <= 120;
    }
    

    //VERIFICAR PERIODO
    public boolean seDiurno(Date diaSelecionado, long diferencaMinutosTotal) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaSelecionado);

        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        long MinutosEmHora = (diferencaMinutosTotal / 60);
        long horaTotal = hora + MinutosEmHora;

        return horaTotal >= 6 && hora < 22;
    }

    public boolean seNoturno(Date diaSelecionado, long diferencaMinutosTotal) {
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(diaSelecionado);

        int hora = inicio.get(Calendar.HOUR_OF_DAY);
        long MinutosEmHora = (int) (diferencaMinutosTotal / 60);
        long horaTotal = hora + MinutosEmHora;

        return horaTotal >= 0 && horaTotal < 6 || horaTotal >= 22 && horaTotal < 24;

    }
    

    //CLASSIFICAR TIPO 'HORA EXTRA'
    public void classificarHorasExtras() {
        horaDAO dao = new horaDAO();
        Set<Hora> horas = dao.getHoras();

        for (Hora hora : horas) {
            if (hora.getTipo().equals("hora_extra")) {

                Calendar inicio = Calendar.getInstance();
                inicio.setTime(hora.getData_hora_inicio());

                Calendar fim = Calendar.getInstance();
                fim.setTime(hora.getData_hora_fim());

                Calendar calendar = (Calendar) inicio.clone();

                //UM DIA POR VEZ
                while (calendar.before(fim) || calendar.equals(fim)) {
                    int diaSelecionado = calendar.get(Calendar.DAY_OF_WEEK);
                    Date diaSelec = hora.getData_hora_inicio();

                    int horaInicio = Calendar.HOUR_OF_DAY;
                    int minutoInicio = Calendar.MINUTE;
                    int horaFim = fim.HOUR_OF_DAY;
                    int minutoFim = fim.MINUTE;
                    long diferencaMinutosTotal = calcularDiferencaTotal(horaInicio, minutoInicio, horaFim, minutoFim);
                    long diferencaMinutosDiurno = calcularDiferencaTotal(horaInicio, minutoInicio, 21, 59);
                    long diferencaMinutosNoturno = calcularDiferencaTotal(22, 00, 23, 59);
                    long diferencaMinutosDia = calcularDiferencaTotal(horaInicio, minutoInicio, 23, 59);
                    long diurno75 = 0;
                    long diurno100 = 0;
                    long noturno75 = 0;
                    long noturno100 = 0;
                    long adn = 0;

                    boolean diaUtil = diaUtil(diaSelecionado);
                    boolean diaNaoUtil = diaNaoUtil(diaSelecionado);
                    boolean SeMenorDe120 = verificarSeMenorDe120(diferencaMinutosTotal);
                    boolean seDiurno = seDiurno(diaSelec, diferencaMinutosTotal);
                    boolean seNoturno = seNoturno(diaSelec, diferencaMinutosTotal);
                    boolean apenasUmDia = inicio.get(Calendar.DAY_OF_YEAR) == fim.get(Calendar.DAY_OF_YEAR);
                    
                    //VERIFICAÇOES DAS VERBAS:
                    
                    //SE APENAS UM DIA
                    if (!apenasUmDia){
                    
                    //DIA UTIL
                    if (diaUtil){
                        
                        if(SeMenorDe120) {
                            
                            if (seDiurno && seNoturno){
                                Verba HE75 = verbas.get(1601);
                                Verba HEN75 = verbas.get(3000);
                                Verba ADN = verbas.get(1809);
                                
                                HE75.setValor(diferencaMinutosDiurno);
                                HEN75.setValor(diferencaMinutosTotal - diurno75);
                                ADN.setValor(HEN75.getValor());
                            } 
                            else if (seDiurno){
                                Verba HE75 = verbas.get(1601);
                                
                                HE75.setValor(diferencaMinutosTotal);
                            } 
                            else if (seNoturno){
                                Verba HEN75 = verbas.get(3000);
                                Verba ADN = verbas.get(1809);
                                
                                HEN75.setValor(diferencaMinutosTotal);
                                ADN.setValor(HEN75.getValor());
                            }
                            
                        //MAIOR DE 120MIN
                        } else {
                            
                            if (seDiurno && seNoturno){
                                
                                if (horaFim >= 22 && diferencaMinutosDiurno > 120) {
                                    Verba HE75 = verbas.get(1601);
                                    Verba HE100 = verbas.get(1602);
                                    Verba HEN100 = verbas.get(3001);
                                    Verba ADN = verbas.get(1809);
                                    
                                    HE75.setValor(120);
                                    HE100.setValor(diferencaMinutosDiurno - 120);
                                    HEN100.setValor(diferencaMinutosTotal - diferencaMinutosDiurno);
                                    ADN.setValor(HEN100.getValor());
                                        
                                } else if (horaFim >= 22 && diferencaMinutosDiurno <= 120) {
                                    Verba HE75 = verbas.get(1601);
                                    Verba HEN75 = verbas.get(3000);
                                    Verba HEN100 = verbas.get(3001);
                                    Verba ADN = verbas.get(1809);
                                    
                                    HE75.setValor(diferencaMinutosDiurno);
                                    HEN75.setValor(120 - diferencaMinutosDiurno);
                                    HEN100.setValor(diferencaMinutosTotal - 120);
                                    ADN.setValor(HEN75.getValor() + HEN100.getValor());
                                        
                                } else if (horaFim < 6) {
                                    Verba HE100 = verbas.get(1602);
                                    Verba HEN75 = verbas.get(3000);
                                    Verba HEN100 = verbas.get(3001);
                                    Verba ADN = verbas.get(1809);
                                    
                                    HEN75.setValor(120);
                                    HEN100.setValor(calcularDiferencaTotal(horaInicio, minutoInicio, 5, 59) - 120);
                                    ADN.setValor(HEN75.getValor() + HEN100.getValor());
                                    HE100.setValor(diferencaMinutosTotal - (noturno75 + noturno100));
                                }
                                    
                            }
                            else if (seDiurno){
                                Verba HE75 = verbas.get(1601);
                                Verba HE100 = verbas.get(1602);
                                
                                HE75.setValor(120);
                                HE100.setValor(diferencaMinutosTotal - 120);
                            } 
                            else if (seNoturno){
                                Verba HEN75 = verbas.get(3000);
                                Verba HEN100 = verbas.get(3001);
                                Verba ADN = verbas.get(1809);
                                
                                HEN75.setValor(120);
                                HEN100.setValor(diferencaMinutosTotal - 120);
                                ADN.setValor(HEN75.getValor() + HEN100.getValor());
                            }
                        }
                    }
                    
                    //DIA NAO UTIL
                    else if (diaNaoUtil){
                        
                        if(seDiurno && seNoturno) {
                            if (horaFim >= 22) {
                                Verba HE100 = verbas.get(1602);
                                Verba HEN100 = verbas.get(3001);
                                Verba ADN = verbas.get(1809);
                                
                                HE100.setValor(diferencaMinutosDiurno);
                                HEN100.setValor(diferencaMinutosTotal - diferencaMinutosDiurno);
                                ADN.setValor(HEN100.getValor());
                            
                            } else if (horaInicio < 6) {
                                Verba HE100 = verbas.get(1602);
                                Verba HEN100 = verbas.get(3001);
                                Verba ADN = verbas.get(1809);
                                
                                HEN100.setValor(calcularDiferencaTotal(horaInicio, minutoInicio, 5, 59));
                                HE100.setValor(diferencaMinutosTotal - HEN100.getValor());
                                ADN.setValor(HEN100.getValor());
                            }
                        }
                        
                        else if (seDiurno) {
                            Verba HE100 = verbas.get(1602);
                            
                            HE100.setValor(diferencaMinutosTotal);
                        }
                        
                        else if (seNoturno){
                            Verba HEN100 = verbas.get(3001);
                            Verba ADN = verbas.get(1809);
                            
                             HEN100.setValor(diferencaMinutosTotal);
                             ADN.setValor(HEN100.getValor());
                        }
                    }
                    }
                    
                    calendar.add(Calendar.DATE, 1);
                
                }
            }
        }
    }
    
    
    public class Verba {
    private int codigo;
    private long valor;

    public Verba(int codigo, long valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
    
    public Map<Integer, Verba> verbas;

    public ClassificarVerba() {
        verbas = new HashMap<>();
        verbas.put(1601, new Verba(1601, 0));
        verbas.put(1602, new Verba(1602, 0));
        verbas.put(3000, new Verba(3000, 0));
        verbas.put(3001, new Verba(3001, 0));
        verbas.put(1809, new Verba(1809, 0));
        verbas.put(3016, new Verba(3016, 0));
    }
    
    
}
