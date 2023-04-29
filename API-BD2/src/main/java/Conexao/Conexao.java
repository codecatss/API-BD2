package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String USERNAME = "admin"; //usuário do seu banco
    private static final String PASSWORD = "admin123"; //senha do seu banco
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/2rp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; //porta do seu banco
    
    public static Connection createConnectionToMySQL() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }
    
    public static void main(String[] args) throws SQLException {
        Connection con = createConnectionToMySQL();
        if(con != null) {
            System.out.println("Conexão obtida com sucesso!");
            con.close();
        } else {
            System.out.println("Erro ao conectar.");
        }
    }
}
