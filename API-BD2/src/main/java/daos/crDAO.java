/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Centro_resultado;

/**
 *
 * @author mikaela.begotti
 */
public class crDAO {
    public void save(Centro_resultado cr){
        String sql = "INSERT INTO centro_resultado(nome, status_aprovacao, codigo_cr, sigla) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,cr.getNome());
            pstm.setString(3, cr.getCodigo_cr());
            pstm.setString(2, cr.getStatus_aprovacao());
            pstm.setString(4, cr.getSigla());
            
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

    public void delete(Centro_resultado cr){
        String sql = "DELETE FROM centro_resultado "+"WHERE codigo_cr";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(3,cr.getCodigo_cr());
            
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
    public List<Centro_resultado> getCliente(){
		
		String sql = "SELECT * FROM 2rp.centro_resultado";
		
		List<Centro_resultado> crs = new ArrayList<Centro_resultado>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Centro_resultado cr = new Centro_resultado();
				
				
				cr.setCodigo_cr(rset.getString("codigo_cr"));
				
				cr.setNome(rset.getString("nome"));
				cr.setSigla(rset.getString("sigla"));
                                cr.setStatus_clientes(rset.getString("status_aprovacao"));
			
				crs.add(cr);
				
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
                        System.out.println(crs);
			return crs;
	}
}
