/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package testando_view_exemplo;

import daos.horaDAO;
import daos.usuarioDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import models.Hora;
import models.Usuario;

/**
 *
 * @author mikaela.begotti
 */

//isso não é necessariamente uma massa de testes formal com o junit, mas é apenas para testar a integração.
public class testeintegracao {

    public static void main(String[] args) throws ParseException{
      
     
     horaDAO horaDao = new horaDAO();
      Hora hora = new Hora();
      hora.setUsername_lancador("Geniscleita");
      hora.setData_hora_inicio("2023-04-15 12:30:00");
      hora.setData_hora_fim("2023-04-15 13:30:00");
      hora.setTipo("Hora extra");
      hora.setCentro_resultado("dssfusefh");
      horaDao.save(hora);
      horaDao.getHoras();
    }
}
