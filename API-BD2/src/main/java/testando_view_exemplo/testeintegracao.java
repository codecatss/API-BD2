/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package testando_view_exemplo;

import daos.usuarioDAO;
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
    }
}
