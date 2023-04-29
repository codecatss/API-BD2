/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testando_view_exemplo;

import daos.horaDAO;
import java.sql.Timestamp;
import java.text.ParseException;
import models.Hora;

/**
 *
 * @author mikaela.begotti
 */
public class massatestedao {
    
    public static void main(String[] args) throws ParseException {
        Hora hora = new Hora();
        horaDAO horaDao = new horaDAO();
        hora.setCod_cr("123844");
        hora.setUsername_lancador("brendel");
        hora.setCnpj_cliente(384857342);
        hora.setData_hora_inicio("2023-4-18 18:30:02");
        hora.setData_hora_fim("2023-4-18 19:30:02");
        hora.setTipo("tipo");
        hora.setJustificativa_lancamento("gostaria de lançar");
        hora.setProjeto("Integrador 2.0");
        hora.setUsername_aprovador("larissa");
        hora.setJustificativa_negacao(null);
        hora.setStatus_aprovacao("aprovado");
        horaDao.save(hora);
        
    }
}
