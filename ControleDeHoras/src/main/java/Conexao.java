import java.sql.*;

public class Conexao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/meu_banco_de_dados";
        String usuario = "usuario";
        String senha = "senha";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            Statement stmt = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
