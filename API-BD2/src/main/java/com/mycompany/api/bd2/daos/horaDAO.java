/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.daos;

import Conexao.Conexao;
import com.mycompany.api.bd2.models.Hora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mikaela.begotti
 */
public class horaDAO {
    public void save(Hora hora){
    String sql = "INSERT INTO hora(cod_cr, username_lancador, cnpj_cliente, data_hora_inicio, data_hora_fim, tipo, justificativa_lancamento, projeto, username_aprovador, justificativa_negacao, status_aprovacao) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?)";
    Connection conn = null;
    PreparedStatement pstm = null; 
        
        try{
    
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,hora.getCod_cr());
            pstm.setString(2,hora.getUsername_lancador());
            pstm.setLong(3,hora.getCnpj_cliente());
            pstm.setTimestamp(4,hora.getData_hora_inicio());
            pstm.setTimestamp(5,hora.getData_hora_fim());
            pstm.setString(6,hora.getTipo());
            pstm.setString(7,hora.getJustificativa_lancamento());
            pstm.setString(8,hora.getProjeto());
            pstm.setString(9,hora.getUsername_aprovador());
            pstm.setString(10,hora.getJustificativa_negacao());
            pstm.setString(11,hora.getStatus_aprovacao());
            
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
        String sql = "DELETE FROM hora "+"WHERE id=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1,hora.getId());
            
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
    public Set<Hora> getHoras(){
		
		String sql = "SELECT * FROM 2rp.hora";
		
		Set<Hora> horas = new HashSet<Hora>();
		
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
    
    public Set<Hora> getHora(String username_lancador){
	
                String sql = "SELECT * FROM 2rp.hora WHERE username_lancador = ?";
		Set<Hora> horasUser = new HashSet<Hora>();
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
                //Hora hora = Hora.getInstance();
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
                        pstm.setString(1, username_lancador);			
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
                                hora.setSolicitante(rset.getString("solicitante_lancamento"));
                                hora.setNome_cliente(getNomeClient(rset.getLong("cnpj_cliente")));

				horasUser.add(hora);
				
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
                        System.out.println(horasUser);
			return horasUser;
	}
    
    private String nome;
    public String getNomeClient(long n_cnpj){
        String sql = "SELECT razao_social FROM 2rp.cliente WHERE cnpj = ?";
		
            Set<Hora> horas = new HashSet<Hora>();

            Connection conn = null;
            PreparedStatement pstm = null;
            //Classe que vai recuperar os dados do banco. ***SELECT****
            ResultSet rset = null;

            try {
                conn = Conexao.createConnectionToMySQL();

                pstm = (PreparedStatement) conn.prepareStatement(sql);
                pstm.setLong(1, n_cnpj);

                rset = pstm.executeQuery();

                if (rset.next()) {
                    nome = rset.getString("razao_social");
                }
            }catch(Exception e){
                e.printStackTrace();}
            finally {
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
    return nome;}
}