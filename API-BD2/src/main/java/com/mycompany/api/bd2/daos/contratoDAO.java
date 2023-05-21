/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.daos;

import Conexao.Conexao;
import com.mycompany.api.bd2.models.Contrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danko
 */
public class contratoDAO {
    public void save(Contrato contrato){
        String sql = "INSERT INTO contrato(cod_cr, cnpj_cliente) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,contrato.getCod_cr());
            pstm.setLong(2, contrato.getCnpj_cliente());
            
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

    public void delete(Contrato contrato){
        String sql = "DELETE FROM contrato "+"WHERE id";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(2,contrato.getId());
            
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
    public List<Contrato> getContrato(){
		
		String sql = "SELECT * FROM 2rp.contrato";
		
		List<Contrato> contratos = new ArrayList<Contrato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Contrato contrato = new Contrato();
				
				
				contrato.setCod_cr(rset.getString("cod_cr"));
				
				contrato.setCnpj_cliente(rset.getLong("cnpj_cliente"));
			
			
				contratos.add(contrato);
				
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
                        System.out.println(contratos);
			return contratos;
	}
        public Contrato getCr(String cod_cr){
		String sql = "SELECT * FROM 2rp.contrato where cod_cr = ?";
				
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
                Contrato contrato = new Contrato();

		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
                        pstm.setString(1,cod_cr);

			rset = pstm.executeQuery();
			
			if (rset.next()) {
				
				contrato.setCod_cr(rset.getString("cod_cr"));
				contrato.setCnpj_cliente(rset.getLong("cnpj_cliente"));
				
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
                        System.out.println(contrato.getCod_cr());
        return contrato;
    }
}
