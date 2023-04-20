/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Hora;
/**
 *
 * @author mikaela.begotti
 */
public class horaDAO {
    
    public void save(Hora hora){
        String sql = "INSERT INTO HORA(username_lancador,data_hora_inicio,data_hora_fim,tipo) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,hora.getUsername_lancador());
            pstm.setDate(2, new Date(hora.getData_hora_inicio().getTime()));
            pstm.setDate(3, new Date(hora.getData_hora_fim().getTime()));
            pstm.setString(4, hora.getTipo());
            
            pstm.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }

    public void delete(Hora hora){
        String sql = "DELETE FROM hora "+"WHERE username_lancador=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,hora.getUsername_lancador());
            
            pstm.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
    public List<Hora> getContatos(){
		
		String sql = "SELECT * FROM 2rp.hora";
		
		List<Hora> horas = new ArrayList<Hora>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Hora hora = new Hora();
				
				
				hora.setUsername_lancador(rset.getString("username_lancador"));
				
				hora.setData_hora_inicio(rset.getDate("data_hora_inicio"));
				hora.setData_hora_fim(rset.getDate("data_hora_fim"));
				hora.setTipo(rset.getString("tipo"));
                                
				horas.add(hora);
				
			}
		}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}
					
					if(pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
                        System.out.println(horas);
			return horas;
	}
    
}
