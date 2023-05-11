/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.daos;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.api.bd2.models.Integrante;

/**
 *
 * @author mikaela.begotti
 */
public class integranteDAO {
    
    public void save(Integrante integrante){
        String sql = "INSERT INTO integrante(gestor, username_integrante, cod_cr) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1,integrante.getGestor());
            pstm.setString(2,integrante.getUsername_integrante());
            pstm.setString(3,integrante.getCod_cr());
            
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

    public void delete(Integrante integrante){
        String sql = "DELETE FROM integrante "+"WHERE username_integrante=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,integrante.getUsername_integrante());
            
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
    public List<Integrante> getIntegrantes(){
		
		String sql = "SELECT * FROM 2rp.integrante";
		
		List<Integrante> integrantes = new ArrayList<Integrante>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Integrante integrante = new Integrante();
				
				
				integrante.setGestor(rset.getInt("gestor"));
				integrante.setUsername_integrante(rset.getString("username_integrante"));
                                integrante.setCod_cr(rset.getString("cod_cr"));
				
				integrantes.add(integrante);
				
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
                        System.out.println(integrantes);
			return integrantes;
	}

    public List<Integer> getListCrGestor(String nomegestor){
		
		String sql = "SELECT cod_cr FROM 2rp.integrante WHERE gestor = 0 AND username_lancador = ?";

		
		List<Integer> liscr = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
                    conn = Conexao.createConnectionToMySQL();
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, nomegestor);  // Define o valor de nomeProcurado no PreparedStatement
                    rset = pstm.executeQuery();
			
                    while (rset.next()) {

                            liscr.add(Integer.valueOf((rset.getString("cod_cr"))));

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
                        System.out.println(liscr);
			return liscr;
	}
}
