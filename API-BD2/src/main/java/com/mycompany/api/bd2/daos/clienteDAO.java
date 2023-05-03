/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.daos;

import com.mycompany.api.bd2.models.Cliente;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.api.bd2.models.Hora;
/**
 *
 * @author mikaela.begotti
 */
public class clienteDAO {
    
    public void save(Cliente cliente){
        String sql = "INSERT INTO cliente(cnpj, razao_social, status_cliente) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setLong(1,cliente.getCnpj());
            pstm.setString(2, cliente.getRazao_social());
            pstm.setString(3, cliente.getStatus_cliente());
            
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

    public void delete(Cliente cliente){
        String sql = "DELETE FROM cliente "+"WHERE cnpj";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setLong(1,cliente.getCnpj());
            
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
    public List<Cliente> getClientes(){
		
		String sql = "SELECT * FROM 2rp.cliente";
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setCnpj(rset.getLong("cnpj"));
				cliente.setRazao_social(rset.getString("razao_social"));
				cliente.setStatus_cliente(rset.getString("status_cliente"));
				clientes.add(cliente);
				
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
                        System.out.println(clientes);
			return clientes;
	}
    public Cliente getCliente(String nome_cliente){
		String sql = "SELECT * FROM 2rp.cliente where razao_social = ?";
				
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
                Cliente cliente = new Cliente();

		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
                        pstm.setString(1,nome_cliente);

			rset = pstm.executeQuery();
			
			if (rset.next()) {
				
				cliente.setCnpj(rset.getLong("cnpj"));
				cliente.setRazao_social(rset.getString("razao_social"));
				cliente.setStatus_cliente(rset.getString("status_cliente"));
				
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
                        System.out.println(cliente);
        return cliente;
    }
}

