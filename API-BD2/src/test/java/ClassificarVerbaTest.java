package com.mycompany.api.bd2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Calendar;

public class ClassificarVerbaTest {

    private ClassificarVerba classificarVerba;

    @Before
    public void setUp() {
        classificarVerba = new ClassificarVerba();
    }
    
    @Test
    public void testDiaUtil() {
        boolean result = classificarVerba.diaUtil(2);
        assertTrue(result);
    }
    
    @Test
    public void testDiaNaoUtil() {
        boolean result = classificarVerba.diaNaoUtil(7);
        assertTrue(result);
    }
    
    @Test
    public void testCalcularDiferencaTotal() {
        int horaInicio = 9;
        int minutoInicio = 30;
        int horaFim = 13;
        int minutoFim = 45;
        
        long resultado = classificarVerba.calcularDiferencaTotal(horaInicio, minutoInicio, horaFim, minutoFim);

        assertEquals(255, resultado);
    }
    
    @Test
    public void testCalcularDiferencaPorDia() {
        int horaInicio = 22;
        int minutoInicio = 59;
        
        long resultado = classificarVerba.calcularDiferencaPorDia (horaInicio, minutoInicio);

        assertEquals(60, resultado);
    }
    

}
