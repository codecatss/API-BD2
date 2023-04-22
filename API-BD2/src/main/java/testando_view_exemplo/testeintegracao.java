/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package testando_view_exemplo;

import daos.clienteDAO;
import daos.crDAO;
import daos.horaDAO;
import daos.usuarioDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import models.Centro_resultado;
import models.Cliente;
import models.Hora;
import models.Usuario;

/**
 *
 * @author mikaela.begotti
 */

//isso não é necessariamente uma massa de testes formal com o junit, mas é apenas para testar a integração.
public class testeintegracao {

    public static void main(String[] args) throws ParseException{
        
      /*horaDAO horaDao = new horaDAO();
      Hora hora = new Hora(null, "Geniscleita","2023-02-12 13:34:01","2023-02-12 13:34:01","ajajs","euffdg","euffdg","just","trampos");
      
      hora.setUsername_lancador("Geniscleita");
      hora.setData_hora_inicio("2023-02-12 13:34:01");
      hora.setData_hora_fim("2023-02-12 13:34:01");
      hora.setTipo("ajajs");
      hora.setCentro_resultado("euffdg");
      hora.setJustificativa("just");
      hora.setProjeto("trampos");
      
      horaDao.save(hora);
      
      */
    }
}
