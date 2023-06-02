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
    
    
    //VERIFICAR SE <= 120 MINUTOS POR DIA
    public boolean verificarDiferenca120(long diferencaMinutosDia) {
        return diferencaMinutosDia <= 120;
    }
    
    

}