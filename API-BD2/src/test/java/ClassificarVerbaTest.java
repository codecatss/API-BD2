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
    
    
}
