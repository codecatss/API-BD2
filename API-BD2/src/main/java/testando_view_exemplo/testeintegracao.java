/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package testando_view_exemplo;

import daos.horaDAO;
import daos.usuarioDAO;
import java.sql.Date;
import models.Hora;
import models.Usuario;

/**
 *
 * @author mikaela.begotti
 */

//isso não é necessariamente uma massa de testes formal com o junit, mas é apenas para testar a integração.
public class testeintegracao {

    public static void main(String[] args) {
      usuarioDAO usuarioDao = new usuarioDAO();
      Usuario usuario = new Usuario();
      usuario.setUser_name("mikela@20");
      usuario.setCargo("colaborador");
      usuario.setNome("mikao");
      usuario.setSenha("miskamus");
      usuario.setStatus("inativo");
      usuarioDao.save(usuario);
      usuarioDao.getContatos();
      
     horaDAO horaDao = new horaDAO();
      Hora hora = new Hora();
      hora.setUsername_lancador("Estacius VanderGraaf");
      
      hora.setData_hora_inicio(01,04,2023,12,30);
      hora.setData_hora_fim(01,04,2023,13,30);
      hora.setTipo("Hora extra");
    }
}
