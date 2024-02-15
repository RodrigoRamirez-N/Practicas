import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProfesoresCrudOperations implements CrudOperations {

    @Override
    public void create() {
        String sqlInsertProfesor = "INSERT INTO Profesores (expediente_id, nombre, apellido_paterno, apellido_materno, direccion, especialidad) VALUES (?, ?, ?, ?, ?, ?)";
        int exp_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el expediente del nuevo profesor"));
        String nom=JOptionPane.showInputDialog("Ingresa el nombre del nuevo profesor");
        String apPat=JOptionPane.showInputDialog("Ingresa el apellido paterno del nuevo profesor");
        String apMat=JOptionPane.showInputDialog("Ingresa el apellido materno del nuevo profesor");
        String dir = JOptionPane.showInputDialog("Ingresa la dirección del nuevo profesor");
        String esp = JOptionPane.showInputDialog("Ingresa la especialidad del nuevo profesor");
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlInsertProfesor);
        ) {
            declaracion.setInt(1, exp_id);
            declaracion.setString(2, nom);
            declaracion.setString(3, apPat);
            declaracion.setString(4, apMat);
            declaracion.setString(5, dir);
            declaracion.setString(6, esp);
            declaracion.executeUpdate();
            System.out.println("Operación de inserción completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        String sqlSelectProfesores = "SELECT * FROM Profesores";
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlSelectProfesores);
        ) {
            ResultSet resultSet = declaracion.executeQuery(sqlSelectProfesores);
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
        int expedienteAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el expediente del profesor que deseas modificar"));
        String sqlUpdateAlumno = "UPDATE Profesores SET expediente_id = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ? WHERE expediente_id = "+expedienteAfectada;
        int exp_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el expediente del nuevo profesor"));
        String nom=JOptionPane.showInputDialog("Ingresa el nombre del nuevo profesor");
        String apPat=JOptionPane.showInputDialog("Ingresa el apellido paterno del nuevo profesor");
        String apMat=JOptionPane.showInputDialog("Ingresa el apellido materno del nuevo profesor");
        String dir = JOptionPane.showInputDialog("Ingresa la dirección del nuevo profesor");
        String esp = JOptionPane.showInputDialog("Ingresa la especialidad del nuevo profesor");
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlUpdateAlumno);
        ) {
            declaracion.setInt(1, exp_id);
            declaracion.setString(2, nom);
            declaracion.setString(3, apPat);
            declaracion.setString(4, apMat);
            declaracion.setString(3, dir);
            declaracion.setString(4, esp);
            declaracion.executeUpdate();
            System.out.println("Operación de modificar completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sqlDeletProfesor = "DELETE FROM Profesores WHERE expediente_id = (?)";
        int expedienteAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el expediente del profesor que deseas borrar"));
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlDeletProfesor);
        ) {
            declaracion.setInt(1, expedienteAfectada);
            declaracion.executeUpdate();
            System.out.println("Operación de borrado completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection connection() throws SQLException{ //this returns a connection to my database named escueladb as the url specifies
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e) {
            System.out.println("Library not found" + e.getMessage());
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/escueladb", "root", "");
    }

}
