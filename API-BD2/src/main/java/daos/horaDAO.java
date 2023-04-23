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
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import models.Hora;
/**
 *
 * @author mikaela.begotti
 */
public class horaDAO {
    
    public void save(Hora hora) throws ParseException{
        
        String sql = "INSERT INTO hora(data_hora_inicio, data_hora_fim, tipo, justificativa_lancamento, projeto, justificativa_negacao, status_aprovacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        //para fazer funcionar os outros campos, digital na query acima o nome da coluna e mais um ponto de '?', e tirar do comentário o campo que você inseriu na query.
        //obs: todos os campos do meu banco local tiveram o NOT NULL removido.
        //Campos com um X do lado, indica que não existe isso no front ou não tem campo para ele como entrada
        // campos em comentário indicam chaves estrangeiras
        try{
            
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //pstm.setString(1, hora.getCod_cr());
            //pstm.setString(2, hora.getUsername_lancador());
            //pstm.setLong(3, hora.getCnpj_cliente());
            pstm.setTimestamp(1, hora.getData_hora_inicio());
            pstm.setTimestamp(2, hora.getData_hora_fim());
            pstm.setString(3, hora.getTipo());

            pstm.setString(4, hora.getJustificativa_lancamento()); //X
            pstm.setString(5, hora.getProjeto());
            //pstm.setString(6, hora.getUsername_aprovador());
            pstm.setString(6, hora.getJustificativa_negacao()); //X
            pstm.setString(7, hora.getStatus_aprovacao()); //X
            
            
            pstm.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                System.out.println("void save");
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
            pstm.setString(2,hora.getUsername_lancador());
            
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
    public List<Hora> getHoras(){
		
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
				
				hora.setCod_cr(rset.getString("cod_cr"));
                                hora.setUsername_lancador(rset.getString("username_lancador"));
                                hora.setCnpj_cliente(rset.getLong("cnpj_cliente"));
                                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                                hora.setTipo(rset.getString("tipo"));
                                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
				hora.setProjeto(rset.getString("projeto"));
                                hora.setUsername_aprovador(rset.getString("username_aprovador"));
                                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));
                                
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
