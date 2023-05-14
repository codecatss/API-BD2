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
import java.util.LinkedList;

/**
 *
 * @author mikaela.begotti
 */
public class integranteDAO {

    public void save(Integrante integrante) {
        String sql = "INSERT INTO integrante(gestor, username_integrante, cod_cr) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(2, integrante.getUsername_integrante());
            pstm.setString(3, integrante.getCod_cr());
            if (integrante.getGestor().equals("gestor")) {
                pstm.setInt(1, 1);
            } else {
                pstm.setInt(1, 0);
            }

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

    public void delete(Integrante integrante) {
        String sql = "DELETE FROM integrante " + "WHERE username_integrante=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, integrante.getUsername_integrante());

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

    public List<Integrante> getIntegrantes() {

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

                integrante.setUsername_integrante(rset.getString("username_integrante"));
                integrante.setCod_cr(rset.getString("cod_cr"));

                if (rset.getInt("gestor") == 1) {
                    integrante.setGestor("gestor");
                } else {
                    integrante.setGestor("colaborador");
                }

                integrantes.add(integrante);

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
        System.out.println(integrantes);
        return integrantes;
    }

    public List<Integrante> getIntegrantesByCr(String cod_cr) {

        String sql = "SELECT * FROM 2rp.integrante where cod_cr = ?";

        List<Integrante> integrantes = new ArrayList<Integrante>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, cod_cr);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Integrante integrante = new Integrante();

                integrante.setUsername_integrante(rset.getString("username_integrante"));
                integrante.setCod_cr(rset.getString("cod_cr"));

                if (rset.getInt("gestor") == 1) {
                    integrante.setGestor("gestor");
                } else {
                    integrante.setGestor("colaborador");
                }

                integrantes.add(integrante);

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
        System.out.println(integrantes);
        return integrantes;
    }

    public Integrante getGestorFromCr(String cod_cr) {
        String sql = "SELECT * FROM 2rp.integrante where cod_cr = ? AND gestor = 1";

        Integrante integrante = new Integrante();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cod_cr);  // Define o valor de nomeProcurado no PreparedStatement
            rset = pstm.executeQuery();

            if (rset.next()) {

                integrante.setUsername_integrante(rset.getString("username_integrante"));
                integrante.setCod_cr(rset.getString("cod_cr"));
                if (rset.getInt("gestor") == 1) {
                    integrante.setGestor("gestor");
                } else {
                    integrante.setGestor("colaborador");
                }
            } else {
                return null;
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
        System.out.println("getGestorFromCr: " + integrante.getUsername_integrante());
        return integrante;
    }

    public LinkedList<Integer> getListCrGestor(String nomegestor) {

        String sql = "SELECT cod_cr FROM 2rp.integrante WHERE gestor = 1 AND username_integrante = ?";

        LinkedList<Integer> liscr = new LinkedList<>();

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
        System.out.println("getListCrGestor: " + liscr);
        return liscr;
    }

    public void removeFromCr(String cod_cr, String username_integrante) {
        String sql = "DELETE FROM integrante " + "WHERE username_integrante=? AND cod_cr = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, username_integrante);
            pstm.setString(2, cod_cr);

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

    public void updateGestor(String funcao, String username_integrante, String cod_cr) {
        String sql = "UPDATE 2rp.integrante SET gestor=? WHERE cod_cr=? AND username_integrante=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            if (funcao.equals("gestor")) {
                pstm.setInt(1, 1);
            }else{
                pstm.setInt(1, 0);
            }
            pstm.setString(2, cod_cr);
            pstm.setString(3, username_integrante);

            pstm.executeUpdate();
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
        System.out.println("updateGestor: editado");
    }
}
