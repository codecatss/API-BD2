/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2;

import Conexao.Conexao;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author danko
 */
public class TesteGerarRelatorio {
    public static void gerarRelatorio() throws Exception{
        Conexao conexao = new Conexao();
        Calendar data = Calendar.getInstance();
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd.MM.yyyy");
        String formData = formatadorData.format(data.getTime());

        conexao.gerarRelatorioCSV(formData,"SELECT * FROM 2rp.hora");
    }
}
