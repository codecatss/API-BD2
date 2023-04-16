  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

/**
 *
 * @author danko
 */
public class Conector {
    
    public static void main(String [] args){
    try{
        Class.forName("com.mysql.jdbc.Driver");
    } catch(ClassNotFoundException ex){
        System.out.println("Driver não localizado");
    }
}
}
