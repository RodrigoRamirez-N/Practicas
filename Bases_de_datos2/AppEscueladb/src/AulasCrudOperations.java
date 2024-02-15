import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AulasCrudOperations implements CrudOperations{

    @Override
    public void create() {
        String sqlInsertAula = "INSERT INTO Aulas (aula_id, descripcion) VALUES (?, ?)";
        int aula_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de aula"));
        String desc = JOptionPane.showInputDialog("Ingresa la descripción del aula");
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlInsertAula);
        ) {
            declaracion.setInt(1, aula_id);
            declaracion.setString(2, desc);

            declaracion.executeUpdate();
            System.out.println("Operación de inserción completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        String sqlSelectAula = "SELECT * FROM Aulas";

        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlSelectAula);
        ) {
            ResultSet resultSet = declaracion.executeQuery(sqlSelectAula);
            java.sql.ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
            System.out.println("Operación de leer completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        int aulaAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código del aula que deseas modificar"));
        String sqlUpdateAula = "UPDATE Aulas SET aula_id = ?, descripcion = ? WHERE aula_id = "+aulaAfectada;
        int aula_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de la nueva aula"));
        String desc = JOptionPane.showInputDialog("Ingresa la descripción de la nueva aula");

        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlUpdateAula)
        ) {
            declaracion.setInt(1, aula_id);
            declaracion.setString(2, desc);
            declaracion.executeUpdate();
            System.out.println("Operación de modificar compeltada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sqlDeletAula = "DELETE FROM Aulas WHERE aula_id = (?)";
        int aula_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de aula"));
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlDeletAula);
        ) {
            declaracion.setInt(1, aula_id);
            declaracion.executeUpdate();
            System.out.println("Operación de borrado completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection connection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e) {
            System.out.println("Library not found" + e.getMessage());
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/escueladb", "root", "");
    }

}
