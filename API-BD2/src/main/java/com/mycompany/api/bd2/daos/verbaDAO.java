/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.daos;

import Conexao.Conexao;
import com.mycompany.api.bd2.models.Verba;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author larissa
 */
public class verbaDAO {

    public void save(Verba verba) {
        String sql = "INSERT INTO verba(cod_verba, nome_verba, fator_multiplicacao, hora_inicio, hora_fim) VALUES (?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, verba.getCod_verba());
            pstm.setString(2, verba.getNome_verba());
            pstm.setDouble(3, verba.getFator_multiplicacao());
            pstm.setTime(4, verba.getHora_inicio());
            pstm.setTime(5, verba.getHora_fim());

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

    public void delete(Verba verba) {
        String sql = "DELETE FROM verba " + "WHERE cod_verba=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, verba.getCod_verba());

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

    public Set<Verba> getVerbas() {

        String sql = "SELECT * FROM 2rp.verba";

        Set<Verba> verbas = new HashSet<Verba>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Verba verba = new Verba();

                verba.setCod_verba(rset.getInt("cod_verba"));
                verba.setNome_verba(rset.getString("nome_verba"));
                verba.setFator_multiplicacao(rset.getDouble("fator_multiplicacao"));
                verba.setHora_inicio(rset.getString("hora_inicio"));
                verba.setHora_fim(rset.getString("hora_fim"));

                verbas.add(verba);

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
        System.out.println(verbas);
        return verbas;
    }

    public Verba getVerbaByCod(int cod_verba) {
        String sql = "SELECT * FROM 2rp.verba where cod_verba = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Verba verba = new Verba();

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, cod_verba);

            rset = pstm.executeQuery();

            if (rset.next()) {

                verba.setCod_verba(rset.getInt("codigo_cr"));
                verba.setNome_verba(rset.getString("nome_verba"));
                verba.setFator_multiplicacao(rset.getDouble("fator_multiplicacao"));
                verba.setHora_inicio(rset.getString("hora_inicio"));
                verba.setHora_fim(rset.getString("hora_fim"));
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
        System.out.println(verba.getNome_verba());
        return verba;
    }

    public Verba getVerbaByNome(String nome_verba) {

        String sql = "SELECT * FROM verba WHERE nome_verba = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Verba verba = new Verba();

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, nome_verba);
            rset = pstm.executeQuery();

            if (rset.next()) {

                verba.setCod_verba(rset.getInt("codigo_cr"));
                verba.setNome_verba(rset.getString("nome_verba"));
                verba.setFator_multiplicacao(rset.getDouble("fator_multiplicacao"));
                verba.setHora_inicio(rset.getString("hora_inicio"));
                verba.setHora_fim(rset.getString("hora_fim"));

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
        System.out.println(verba.getNome_verba());
        return verba;
    }

    public void update(Verba verba) {
        String sql = "UPDATE verba SET nome_verba=?, fator_multiplicacao=?, hora_inicio=?, hora_fim=? WHERE cod_verba=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, verba.getNome_verba());
            pstm.setDouble(2, verba.getFator_multiplicacao());
            pstm.setTime(3, verba.getHora_inicio());
            pstm.setTime(4, verba.getHora_fim());
            pstm.setInt(5, verba.getCod_verba());

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
