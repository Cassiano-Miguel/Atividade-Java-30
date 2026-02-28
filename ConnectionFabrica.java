import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFabrica {
    public static final String URL = "jdbc:mysql://localhost:3306/seubanco"; // Adicione a porta e nome do banco
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar o driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}