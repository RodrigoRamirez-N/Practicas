import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AsignaturasCrudOperations implements CrudOperations {

    @Override
    public void create() {
        String sqlInsertAsignatura = "INSERT INTO Asignatura (aula_id, descripcion) VALUES (?, ?, ?, ?)";
        int asignatura_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura"));
        String nom = JOptionPane.showInputDialog("Ingresa el nombre de la asignatura");
        String esp = JOptionPane.showInputDialog("Ingresa el expediente del profesor");
        int exp_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la especialidad del profesor"));
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlInsertAsignatura)
        ) {
            declaracion.setInt(1, asignatura_id);
            declaracion.setString(2, nom);
            declaracion.setString(3, esp);
            declaracion.setInt(4, exp_id);
            declaracion.executeUpdate();
            System.out.println("Operación de inserción completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        String sqlSelectAsignatura = "SELECT * FROM Asignaturas";

        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlSelectAsignatura);
        ) {
            ResultSet resultSet = declaracion.executeQuery(sqlSelectAsignatura);
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
        int asignaturaAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código del aula que deseas modificar"));
        String sqlUpdateAsignatura = "UPDATE Asignatura SET asignatura_id = ?, nombre = ?, especialidad = ?, profesor_id = ? WHERE asignatura_id = "+asignaturaAfectada;
        int asignatura_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura"));
        String nom = JOptionPane.showInputDialog("Ingresa el nombre de la asignatura");
        String esp = JOptionPane.showInputDialog("Ingresa el expediente del profesor");
        int exp_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la especialidad del profesor"));
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlUpdateAsignatura)
        ) {
            declaracion.setInt(1, asignatura_id);
            declaracion.setString(2, nom);
            declaracion.setString(3, esp);
            declaracion.setInt(4, exp_id);
            declaracion.executeUpdate();
            System.out.println("Operación de modificar completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sqlDeletAsignatura = "DELETE FROM Asignatura WHERE asignatura_id = (?)";
        int asignatura_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura que quieres borrar"));
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlDeletAsignatura);
        ) {
            declaracion.setInt(1, asignatura_id);
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
