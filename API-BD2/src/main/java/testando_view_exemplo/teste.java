/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testando_view_exemplo;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.Usuario;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mikaela.begotti
 */
public class teste {
    public static void main(String[] args) throws ParseException {
        
        Hora hora = new Hora();
        horaDAO horadao = new horaDAO();
        
        hora.setCnpj_cliente(123456789);
        hora.setCod_cr("13652");
        hora.setData_hora_fim("2023-04-16 01:20:00");
        hora.setData_hora_inicio("2023-04-15 23:30:00");
        hora.setJustificativa_lancamento("Populando os bancos");
        hora.setJustificativa_negacao("");
        hora.setProjeto("me hours");
        hora.setSolicitante("Brendel");
        hora.setStatus_aprovacao("pendente");
        hora.setTipo("SOBREAVISO");
        hora.setUsername_aprovador("nicole");
        hora.setUsername_lancador("brendel");
        hora.setId(4);
        
        
   
           
        horadao.update(hora);

}
    
    
}
