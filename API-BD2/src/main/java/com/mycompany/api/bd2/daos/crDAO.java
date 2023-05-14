/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.daos;

import Conexao.Conexao;
import com.mycompany.api.bd2.models.Centro_resultado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mikaela.begotti
 */
public class crDAO {

    public void save(Centro_resultado cr) {
        String sql = "INSERT INTO centro_resultado(nome, codigo_cr, sigla, status_cr) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, cr.getNome());
            pstm.setString(2, cr.getCodigo_cr());
            pstm.setString(3, cr.getSigla());
            pstm.setString(4, cr.getStatus_cr());

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

    public void delete(Centro_resultado cr) {
        String sql = "DELETE FROM centro_resultado " + "WHERE codigo_cr";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(2, cr.getCodigo_cr());

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

    public List<Centro_resultado> getCrs() {

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
                cr.setStatus_cr(rset.getString("status_cr"));

                crs.add(cr);

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
        System.out.println(crs);
        return crs;
    }

    public Centro_resultado getCr(String nome_cr) {
        String sql = "SELECT * FROM 2rp.centro_resultado where nome = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco. ***SELECT****
        ResultSet rset = null;
        Centro_resultado cr = new Centro_resultado();

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, nome_cr);

            rset = pstm.executeQuery();

            if (rset.next()) {

                cr.setCodigo_cr(rset.getString("codigo_cr"));
                cr.setNome(rset.getString("nome"));
                cr.setSigla(rset.getString("sigla"));
                cr.setStatus_cr(rset.getString("status_cr"));
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
        System.out.println(cr.getNome());
        return cr;
    }

    public Centro_resultado getCrByCodigo(String codigo_cr) {

        String sql = "SELECT * FROM centro_resultado WHERE codigo_cr = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco. ***SELECT****
        ResultSet rset = null;
        Centro_resultado cr = new Centro_resultado();

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, codigo_cr);
            rset = pstm.executeQuery();

            if (rset.next()) {

                cr.setCodigo_cr(rset.getString("codigo_cr"));
                cr.setNome(rset.getString("nome"));
                cr.setSigla(rset.getString("sigla"));
                cr.setStatus_cr(rset.getString("status_cr"));

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
        return cr;
    }

    public void update(Centro_resultado cr) {
        String sql = "UPDATE centro_resultado SET nome=?, status_cr=?, sigla=? WHERE codigo_cr=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, cr.getNome());
            pstm.setString(2, cr.getStatus_cr());
            pstm.setString(3, cr.getSigla());
            pstm.setString(4, cr.getCodigo_cr());

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
    }
}
