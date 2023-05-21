/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testando_view_exemplo;

import com.mycompany.api.bd2.daos.horaDAO;
import com.mycompany.api.bd2.models.Hora;

/**
 *
 * @author mikaela.begotti
 */
public class testeUp {
    horaDAO horadao = new horaDAO();
    Hora hora = new Hora();
    public  void main(String[] args) {
        hora.setCod_cr("1334");
    }
}
