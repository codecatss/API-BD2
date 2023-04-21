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
      
      crDAO crDao = new crDAO();
      Centro_resultado centro = new Centro_resultado();
      centro.setCodigo_cr("37475");
      centro.setNome("Nome");
      centro.setSigla("PSDB");
      centro.setStatus_clientes("inativo");
      crDao.save(centro);
      crDao.getCliente();
      
      
      
    }
}
