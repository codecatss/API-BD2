package com.mycompany.api.bd2;

import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Hora;
import java.util.Set;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
                                //VERBA HE75: diurno75 = diferencaMinutosDiurno;
                                //VERBA HEN75: noturno75 = diferencaMinutosTotal - diurno75;
                                //VERBA ADN: adn = noturno75;
                            } 
                            else if (seDiurno){
                                //VERBA HE75: diurno75 = diferencaMinutosTotal;
                            } 
                            else if (seNoturno){
                                //VERBA HEN75: noturno75 = diferencaMinutosTotal;
                                //VERBA ADN: adn = noturno75;
                            }
                            
                        //MAIOR DE 120MIN
                        } else {
                            
                            if (seDiurno && seNoturno){
                                
                                if (horaFim >= 22 && diferencaMinutosDiurno > 120) {
                                        //VERBA HE75: diurno75 = 120;
                                        //VERBA HE100: diurno100 = diferencaMinutosDiurno - 120;
                                        //VERBA HEN100: noturno100 = diferencaMinutosTotal - diferencaMinutosDiurno;
                                        //VERBA ADN: adn = noturno100;
                                        
                                } else if (horaFim >= 22 && diferencaMinutosDiurno <= 120) {
                                        //VERBA HE75: diurno75 = diferencaMinutosDiurno;
                                        //VERBA HEN75: noturno75 = 120 - diferencaMinutosDiurno;
                                        //VERBA HEN100: noturno100 = diferencaMinutosTotal - 120;
                                        //VERBA ADN: adn = noturno75 + noturno100;
                                        
                                } else if (horaFim < 6) {
                                        //VERBA HEN75: noturno75 = 120;
                                        //VERBA HEN100: noturno100 = calcularDiferencaTotal(horaInicio, minutoInicio, 5, 59) - noturno75;
                                        //VERBA HE 100: diurno100 = diferencaMinutosTotal - (noturno75 + noturno100)
                                        //VERBA ADN: adn = noturno75 + noturno100;
                                }
                                    
                            }
                            else if (seDiurno){
                            //VERBA HE75: diurno75 = 120;
                            //VERBA HE100: diurno100 = diferencaMinutosTotal - diurno75; 
                            } 
                            else if (seNoturno){
                                //VERBA HEN75: noturno75 = 120;
                                //VERBA HEN100: noturno100 = diferencaMinutosTotal - noturno75;
                                //VERBA ADN: adn = noturno75 + noturno100;
                            }
                        }
                    }
                    
                    //DIA NAO UTIL
                    else if (diaNaoUtil){
                        
                        if(seDiurno && seNoturno) {
                            if (horaFim >= 22 && diferencaMinutosDiurno > 120) {
                                //VERBA HE100: diurno100 = diferencaMinutosDiurno;
                                //VERBA HEN100: noturno100 = diferencaMinutosTotal - diferencaMinutosDiurno;
                                //VERBA ADN: adn = noturno100;
                                        
                                } else if (horaFim >= 22 && diferencaMinutosDiurno <= 120) {
                                    //VERBA HE100: diurno100 = diferencaMinutosDiurno;
                                    //VERBA HEN100: noturno100 = diferencaMinutosTotal - diferencaMinutosDiurno;
                                    //VERBA ADN: adn = noturno100;
                                
                                } else if (horaFim < 6) {
                                    //VERBA HEN100: noturno100 = calcularDiferencaTotal(horaInicio, minutoInicio, 5, 59);
                                    //VERBA HE 100: diurno100 = diferencaMinutosTotal - noturno100;
                                    //VERBA ADN: adn = noturno100;
                                }
                        }
                        
                        else if (seDiurno) {
                            //VERBA HE100: diurno100 = diferencaMinutosTotal;
                        }
                        
                        else if (seNoturno){
                            //VERBA HEN100: noturno100 = diferencaMinutosTotal;
                            //VERBA ADN: adn = noturno100;
                                }
                    }
                    }
                    
                calendar.add(Calendar.DATE, 1);
            }
        }
    }
}
    
    
    //ENUM VERBAS
    public enum tipoVerba {
        HE_75(1601),
        HE_100(1602),
        HEN_75(3000),
        HEN_100(3001),
        AD_NT(1809),
        SOBAV(3016);

        private final int valor;

        tipoVerba(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }
}
