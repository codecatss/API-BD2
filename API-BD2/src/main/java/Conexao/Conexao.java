package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.opencsv.CSVWriter;
import java.io.File;
import javafx.stage.FileChooser;

public class Conexao {

    private static final String USERNAME = "admin"; //usuário do seu banco
    private static final String PASSWORD = "admin123"; //senha do seu banco
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/2rp"; //porta do seu banco

    public static Connection createConnectionToMySQL() throws Exception {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void gerarRelatorioCSV(String dataExtrato, String query) throws IOException, Exception {
        Connection connection = createConnectionToMySQL();
        Statement stmt = connection.createStatement();

        String caminhoDiretorio = System.getProperty("user.home") + "/Downloads/";
        String caminhoCompleto = caminhoDiretorio + "extrato_" + dataExtrato + ".csv";

        try (ResultSet rs = stmt.executeQuery(query)) {

            // Cria o escritor do arquivo CSV
            CSVWriter writer = new CSVWriter(new FileWriter(caminhoCompleto));

            // Obtem o metadado do resultado da consulta
            ResultSetMetaData rsmd = rs.getMetaData();

            // Obtem o número de colunas
            int numColunas = rsmd.getColumnCount();

            // Cria o array de Strings com o cabeçalho
            String[] cabecalho = new String[numColunas];
            for (int i = 1; i <= numColunas; i++) {
                cabecalho[i - 1] = rsmd.getColumnLabel(i);
            }
            // Escreve o cabeçalho no arquivo CSV
            writer.writeNext(cabecalho);

            // Escreve os dados no arquivo CSV
            while (rs.next()) {
                String[] linha = new String[numColunas];
                for (int i = 1; i <= numColunas; i++) {
                    linha[i - 1] = rs.getString(i);
                }
                writer.writeNext(linha);
            }

            // Fecha o escritor do arquivo CSV
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String caminho = System.getProperty("user.home") + "/Downloads/";
        System.out.println("Relatório 'extrato_" + dataExtrato + "' foi armazenado na pasta Downloads");
    }

    public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySQL();
        if (con != null) {
            System.out.println("Conexão obtida com sucesso!");
            con.close();
        } else {
            System.out.println("Erro ao conectar.");
        }
    }
}
