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
      
     horaDAO horaDao = new horaDAO();
      Hora hora = new Hora();
      hora.setUsername_lancador("Estacius VanderGraaf");
      hora.setData_hora_inicio(new Date(2023,04,12));
      hora.setData_hora_fim(new Date(2023,04,12));
      hora.setTipo("Hora extra");
      horaDao.save(hora);
      horaDao.getHoras();
    }
}
