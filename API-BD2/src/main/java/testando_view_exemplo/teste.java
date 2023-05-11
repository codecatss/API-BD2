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

/**
 *
 * @author mikaela.begotti
 */
public class teste {
    public static void main(String[] args) {
        Centro_resultado cr = new Centro_resultado();
        crDAO crdao = new crDAO();
        
        cr.setCodigo_cr("13652");
        cr.setNome("Codecats");
        cr.setSigla("CCTS");
        cr.setStatus_cr("inativo");
        
        crdao.update(cr);

}
    
    
}
