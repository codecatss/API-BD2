/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.daos;

import Conexao.Conexao;
import com.mycompany.api.bd2.TelaLoginController;
import com.mycompany.api.bd2.models.Funcao;
import com.mycompany.api.bd2.models.Hora;
import com.mycompany.api.bd2.models.StatusAprovacao;
import com.mycompany.api.bd2.models.TabelaAprovaçãoGestor;
import com.mycompany.api.bd2.models.TipoHora;
import com.mycompany.api.bd2.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikaela.begotti
 */
public class horaDAO {

    public void save(Hora hora) {
        String sql = "INSERT INTO hora(cod_cr, username_lancador, cnpj_cliente, data_hora_inicio, data_hora_fim, tipo, justificativa_lancamento, projeto, aprovador_gestor, justificativa_negacao, status_aprovacao, solicitante_lancamento) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, hora.getCod_cr());
            pstm.setString(2, hora.getUsername_lancador());
            pstm.setLong(3, hora.getCnpj_cliente());
            pstm.setTimestamp(4, hora.getData_hora_inicio());
            pstm.setTimestamp(5, hora.getData_hora_fim());
            pstm.setString(6, hora.getTipo());
            pstm.setString(7, hora.getJustificativa_lancamento());
            pstm.setString(8, hora.getProjeto());
            pstm.setString(9, hora.getUsername_aprovador());
            pstm.setString(10, hora.getJustificativa_negacao());
            pstm.setString(11, hora.getStatus_aprovacao());
            pstm.setString(12, hora.getSolicitante());

            pstm.execute();
            System.out.println("Salvo");
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void atualizarStatusAprovacao(String codCr, String status) {
        String sql = "UPDATE hora SET status_aprovacao = ? WHERE cod_cr = ? ";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, status);
            pstm.setString(2, codCr);
            pstm.executeUpdate();

            System.out.println("Status de aprovação atualizado");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Hora hora) {
        String sql = "DELETE FROM hora " + "WHERE id=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, hora.getId());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Set<Hora> getHoras() {

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
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));

                horas.add(hora);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(horas);
        return horas;
    }

    public Set<Hora> getHora(String username_lancador) {

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
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));
                hora.setSolicitante(rset.getString("solicitante_lancamento"));
                hora.setNome_cliente(getNomeClient(rset.getLong("cnpj_cliente")));

                horasUser.add(hora);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(horasUser);
        return horasUser;
    }
    
    public List getHora(StatusAprovacao status, String ini, String fim) {

        String sql = "SELECT * FROM 2rp.hora WHERE data_hora_inicio BETWEEN ? AND ? AND data_hora_fim BETWEEN ? AND ?";
        if(!status.name().equals("todos"))sql = sql + "AND status_aprovacao = ?";
        
        List horasUser = new LinkedList();
        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco. ***SELECT****
        ResultSet rset = null;
        //Hora hora = Hora.getInstance();

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,ini);
            pstm.setString(2,fim);
            pstm.setString(3,ini);
            pstm.setString(4,fim);
            if(!status.name().equals("todos")) pstm.setString(5, status.name());
            rset = pstm.executeQuery();
            
            while (rset.next()) {
                Hora hora = new Hora();
                hora.setCod_cr(rset.getString("cod_cr"));
                hora.setUsername_lancador(rset.getString("username_lancador"));
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));
                hora.setSolicitante(rset.getString("solicitante_lancamento"));

                horasUser.add(hora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(horasUser);
        return horasUser;
    }

    public LinkedList getHora(LinkedList<Integer> lis_int_cr, String tipo, StatusAprovacao status, String ini, String fim) {//sobrecarga para gerar a tabela de apontamentos

        LinkedList horasUser = new LinkedList<>();
       
        String sql = "SELECT * FROM 2rp.hora WHERE cod_cr = ? AND data_hora_inicio BETWEEN ? AND ? AND data_hora_fim BETWEEN ? AND ?";
        if(status != StatusAprovacao.todos) sql = sql + " AND status_aprovacao = ?";
            
        for (Integer i : lis_int_cr) {
            
            Connection conn = null;
            PreparedStatement pstm = null;
            //Classe que vai recuperar os dados do banco. ***SELECT****
            ResultSet rset = null;
            //Hora hora = Hora.getInstance();

            try {
                String cod = i.toString();

                conn = Conexao.createConnectionToMySQL();

                pstm = (PreparedStatement) conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(cod));
                pstm.setString(2,ini);
                pstm.setString(3,fim);
                pstm.setString(4,ini);
                pstm.setString(5,fim);
                if(status != StatusAprovacao.todos) pstm.setString(6, status.name());
                
                rset = pstm.executeQuery();
                while (rset.next()) {
                    if(tipo.equals("TabelaAprovaçãoGestor")){
                        TabelaAprovaçãoGestor info = new TabelaAprovaçãoGestor();
                        info.setCod_cr(rset.getString("cod_cr"));
                        info.setUsername(rset.getString("username_lancador"));
                        info.setInicio(rset.getString("data_hora_inicio"));
                        info.setFim(rset.getString("data_hora_fim"));
                        info.setTipo(rset.getString("tipo"));
                        info.setJustificativa(rset.getString("justificativa_lancamento"));
                        info.setProjeto(rset.getString("projeto"));
                        info.setId(rset.getInt("id"));
                        info.setEmpresa(rset.getInt("cnpj_cliente"));

                        horasUser.add(info);
                    }
                    if(tipo.equals("Hora")){
                        Hora info = new Hora();
                        info.setCod_cr(rset.getString("cod_cr"));
                        info.setUsername_lancador(rset.getString("username_lancador"));
                        info.setData_hora_inicio(rset.getString("data_hora_inicio"));
                        info.setData_hora_fim(rset.getString("data_hora_fim"));
                        info.setTipo(rset.getString("tipo"));
                        info.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                        info.setProjeto(rset.getString("projeto"));
                        info.setId(rset.getInt("id"));
                        info.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                        info.setStatus_aprovacao(rset.getString("status_aprovacao"));

                        horasUser.add(info);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rset != null) {
                        rset.close();
                    }

                    if (pstm != null) {
                        pstm.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(horasUser);
        return horasUser;
    }
    
    public LinkedList getHora(LinkedList<Integer> lis_int_cr, String tipo, StatusAprovacao status) {//sobrecarga para gerar a tabela de apontamentos

        LinkedList horasUser = new LinkedList<>();
       
        String sql = "SELECT * FROM 2rp.hora WHERE cod_cr = ?";
        if(status != StatusAprovacao.todos) sql = sql + " AND status_aprovacao = ?";
            
        for (Integer i : lis_int_cr) {
            
            Connection conn = null;
            PreparedStatement pstm = null;
            //Classe que vai recuperar os dados do banco. ***SELECT****
            ResultSet rset = null;
            //Hora hora = Hora.getInstance();

            try {
                String cod = i.toString();

                conn = Conexao.createConnectionToMySQL();

                pstm = (PreparedStatement) conn.prepareStatement(sql);
                pstm.setInt(1, Integer.parseInt(cod));
                if(status != StatusAprovacao.todos) pstm.setString(2, status.name());
                
                rset = pstm.executeQuery();
                while (rset.next()) {
                    if(tipo.equals("TabelaAprovaçãoGestor")){
                        TabelaAprovaçãoGestor info = new TabelaAprovaçãoGestor();
                        info.setCod_cr(rset.getString("cod_cr"));
                        info.setUsername(rset.getString("username_lancador"));
                        info.setInicio(rset.getString("data_hora_inicio"));
                        info.setFim(rset.getString("data_hora_fim"));
                        info.setTipo(rset.getString("tipo"));
                        info.setJustificativa(rset.getString("justificativa_lancamento"));
                        info.setProjeto(rset.getString("projeto"));
                        info.setId(rset.getInt("id"));
                        info.setEmpresa(rset.getInt("cnpj_cliente"));

                        horasUser.add(info);
                    }
                    if(tipo.equals("Hora")){
                        Hora info = new Hora();
                        info.setCod_cr(rset.getString("cod_cr"));
                        info.setUsername_lancador(rset.getString("username_lancador"));
                        info.setData_hora_inicio(rset.getString("data_hora_inicio"));
                        info.setData_hora_fim(rset.getString("data_hora_fim"));
                        info.setTipo(rset.getString("tipo"));
                        info.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                        info.setProjeto(rset.getString("projeto"));
                        info.setId(rset.getInt("id"));
                        info.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                        info.setStatus_aprovacao(rset.getString("status_aprovacao"));

                        horasUser.add(info);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rset != null) {
                        rset.close();
                    }

                    if (pstm != null) {
                        pstm.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(horasUser);
        return horasUser;
    }

    private String nome;

    public String getNomeClient(long n_cnpj) {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nome;
    }

    public void aprovarHora(int id, String usernameAprovador) {
        String sql = "UPDATE hora SET status_aprovacao = 'aprovado_gestor', aprovador_gestor = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

    try {
        conn = Conexao.createConnectionToMySQL();
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, usernameAprovador);
        pstm.setInt(2, id);

            pstm.executeUpdate();

            System.out.println("Update realizado");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void reprovarHora(Integer id, String justificativaNegacao, String usernameReprovador){
    Usuario usuario = TelaLoginController.usuariologado;
    String negacao="";
    
    if(usuario.getCargo().equals(Funcao.administrador)){
        negacao = "'aprovado_adm'"; 
        System.out.println("adm");
    }
    if(usuario.getCargo().equals(Funcao.gestor)){
        negacao = "'negado_gestor'";
        System.out.println("gestor");
    }
    
    String sql = "UPDATE hora SET status_aprovacao = "+negacao+", justificativa_negacao = ?, aprovador_gestor = ? WHERE id = ?";
    Connection conn = null;
    PreparedStatement pstm = null;

    try {
        conn = Conexao.createConnectionToMySQL();
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, justificativaNegacao);
        pstm.setString(2, usernameReprovador);
        pstm.setInt(3, id);

            pstm.executeUpdate();

            System.out.println("Update realizado");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

public void reprovarHoraADM(Integer id, String justificativaNegacao, String usernameReprovador) {
    String sql = "UPDATE hora SET status_aprovacao = 'negado_adm', justificativa_negacao = ?, aprovador_gestor = ? WHERE id = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        try {
            conn = Conexao.createConnectionToMySQL();
        } catch (Exception ex) {
            Logger.getLogger(horaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, justificativaNegacao);
        pstmt.setString(2, usernameReprovador);
        pstmt.setInt(3, id);
        pstmt.executeUpdate();

        System.out.println("Hora reprovada com sucesso");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    public Set<Hora> getHorasAprovadas() {
        String sql = "SELECT * FROM 2rp.hora WHERE status_aprovacao = 'pendente' OR status_aprovacao = 'aprovado_gestor'";
        Set<Hora> horasAprovadas = new HashSet<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Hora hora = new Hora();
                hora.setId(rset.getInt("id"));
                hora.setCod_cr(rset.getString("cod_cr"));
                hora.setUsername_lancador(rset.getString("username_lancador"));
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));

                horasAprovadas.add(hora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(horasAprovadas);
        return horasAprovadas;
    }

    public void aprovarHoraADM(int id, String usernameAprovador) {
        String sql = "UPDATE hora SET status_aprovacao = 'aprovado_adm', aprovador_ADM = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            try {
                conn = Conexao.createConnectionToMySQL();
            } catch (Exception ex) {
                Logger.getLogger(horaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, usernameAprovador);
            pstm.setInt(2, id);

            pstm.executeUpdate();

            System.out.println("Hora aprovada com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<Hora> getAprovadaHoras() {
        String sql = "SELECT * FROM 2rp.hora WHERE status_aprovacao = 'aprovado_gestor' OR status_aprovacao = 'aprovado_adm'";
        Set<Hora> horasAprovadas = new HashSet<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Hora hora = new Hora();
                hora.setId(rset.getInt("id"));
                hora.setCod_cr(rset.getString("cod_cr"));
                hora.setUsername_lancador(rset.getString("username_lancador"));
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));

                horasAprovadas.add(hora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return horasAprovadas;
    }

    public Set<Hora> getPendenteHoras() {
        String sql = "SELECT * FROM 2rp.hora WHERE status_aprovacao = 'pendente'";
        Set<Hora> horasAprovadas = new HashSet<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Hora hora = new Hora();
                hora.setId(rset.getInt("id"));
                hora.setCod_cr(rset.getString("cod_cr"));
                hora.setUsername_lancador(rset.getString("username_lancador"));
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));

                horasAprovadas.add(hora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return horasAprovadas;
    }

    public Set<Hora> getReprovadaHoras() {
        String sql = "SELECT * FROM 2rp.hora WHERE status_aprovacao = 'negado_gestor' OR status_aprovacao = 'negado_adm'";

        Set<Hora> horasAprovadas = new HashSet<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Hora hora = new Hora();
                hora.setId(rset.getInt("id"));
                hora.setCod_cr(rset.getString("cod_cr"));
                hora.setUsername_lancador(rset.getString("username_lancador"));
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));

                horasAprovadas.add(hora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return horasAprovadas;
    }

    public Set<Hora> getTodasHoras() {
        String sql = "SELECT * FROM 2rp.hora";
        Set<Hora> todasHoras = new HashSet<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Hora hora = new Hora();
                hora.setId(rset.getInt("id"));
                hora.setCod_cr(rset.getString("cod_cr"));
                hora.setUsername_lancador(rset.getString("username_lancador"));
                hora.setCnpj_cliente(rset.getInt("cnpj_cliente"));
                hora.setData_hora_inicio(rset.getString("data_hora_inicio"));
                hora.setData_hora_fim(rset.getString("data_hora_fim"));
                hora.setTipo(rset.getString("tipo"));
                hora.setJustificativa_lancamento(rset.getString("justificativa_lancamento"));
                hora.setProjeto(rset.getString("projeto"));
                hora.setUsername_aprovador(rset.getString("aprovador_gestor"));
                hora.setJustificativa_negacao(rset.getString("justificativa_negacao"));
                hora.setStatus_aprovacao(rset.getString("status_aprovacao"));

                todasHoras.add(hora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return todasHoras;
    }
}
