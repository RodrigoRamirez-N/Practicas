import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseAccess {
    private String connection_string = "jdbc:mysql://localhost:3306/escueladb";
    public DataBaseAccess() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e) {
            System.out.println("Library not found" + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connection_string, "root", "");
    }

    public PreparedStatement Create(String query) {
        try (
            Connection con = getConnection();
            PreparedStatement declaracion = con.prepareStatement(query);
        ) {
            return declaracion;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}