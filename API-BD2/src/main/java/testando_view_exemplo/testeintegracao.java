/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package testando_view_exemplo;

import daos.clienteDAO;
import daos.horaDAO;
import daos.usuarioDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
      
      clienteDAO clienteDao = new clienteDAO();
      Cliente cliente = new Cliente();
      cliente.setRazao_social("trampos");
      cliente.setStatus_clientes("ATIVO");
      cliente.setCnpj(2355768);
      clienteDao.save(cliente);
      clienteDao.getCliente();
      
      
    }
}
