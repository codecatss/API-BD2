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
    
    
    
}