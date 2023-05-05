/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testando_view_exemplo;

import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.daos.usuarioDAO;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.Usuario;

/**
 *
 * @author mikaela.begotti
 */
public class teste {
    public static void main(String[] args) {
        usuarioDAO usuariodao = new usuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setUsername("marilia3");
        usuario.setNome("Marília Mendonça");
        //usuario.setSenha("dev123");
        usuario.setCargo("admin");
        usuario.setStatus("ativo");
        usuario.setHash(usuario.getSenha().toString());
        usuariodao.save(usuario);
}
    
    
}
