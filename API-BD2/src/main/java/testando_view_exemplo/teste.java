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
        usuarioDAO usuarioDao = new usuarioDAO();
        Usuario usuario = new Usuario();
        //System.out.println(usuarioDao.getUsuarios());
        String cargo = usuarioDao.getUsuarioByUsername("marilia").getCargo();
        String nome = usuarioDao.getUsuarioByUsername("marilia").getNome();
        String senha = usuarioDao.getUsuarioByUsername("marilia").getSenha();
        usuario.setUsername("marilia");
        usuario.setCargo(cargo);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setStatus("ativo");
        usuarioDao.update(usuario);
}
    
    
}
