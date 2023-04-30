/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testando_view_exemplo;

import daos.horaDAO;
import daos.integranteDAO;
import daos.usuarioDAO;
import java.sql.Timestamp;
import java.text.ParseException;
import models.Hora;
import models.Integrante;
import models.Usuario;

/**
 *
 * @author mikaela.begotti
 */
public class massatestedao {
    
    public static void main(String[] args) throws ParseException {
        /*Hora hora = new Hora();
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
        horaDao.save(hora);*/
        
        /*Integrante integrante = new Integrante();
        integranteDAO integranteDao = new integranteDAO();
        integrante.setGestor(3);
        integrante.setUsername_integrante("brendel");
        integrante.setCod_cr("13652");
        integranteDao.delete(integrante);*/
        
        Usuario usuario = new Usuario();
        usuarioDAO usuarioDao = new usuarioDAO();
        usuario.setUsername("brendel");
        usuario.setNome("Brendel Marques");
        usuario.setSenha("dev123");
        usuario.setCargo("colaborador");
        usuario.setStatus("ativo");
        usuarioDao.recuperarUsuario(usuario);
        
    }
}
