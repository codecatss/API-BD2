/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testando_view_exemplo;

import com.mycompany.api.bd2.daos.crDAO;
import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.daos.integranteDAO;
import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Centro_resultado;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.Integrante;
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
        
        Integrante integrante = new Integrante();
        integranteDAO idao = new integranteDAO();
        
        integrante.setCod_cr("13652");
        integrante.setGestor(1);
        integrante.setUsername_integrante("brendel");
        
        
        
        
   
           
        idao.update(integrante);

}
    
    
}
