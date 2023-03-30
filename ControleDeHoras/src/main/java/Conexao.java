import java.sql.*;

public class ExemploConexao {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meu_banco_de_dados", "usuario", "senha");

            // Executar comandos SQL usando a conexão
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM minha_tabela");
            while (rs.next()) {
                System.out.println(rs.getString("coluna1"));
            }

        } catch (SQLException ex) {
            // Tratar exceções
            ex.printStackTrace();
        } finally {
            // Fechar a conexão
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
