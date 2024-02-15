import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AlumnosCrudOperations implements CrudOperations {

    @Override
    public void create() {
        String sqlInsertAlumno = "INSERT INTO Alumnos (matricula_id, nombre, apellido_paterno, apellido_materno) VALUES (?, ?, ?, ?)";
        int mat=Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del alumno"));
        String nom=JOptionPane.showInputDialog("Ingresa el nombre del alumno");
        String apPat=JOptionPane.showInputDialog("Ingresa el apellido paterno del alumno");
        String apMat=JOptionPane.showInputDialog("Ingresa el apellido materno del alumno");

        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlInsertAlumno);
        ) {
            declaracion.setInt(1, mat);
            declaracion.setString(2, nom);
            declaracion.setString(3, apPat);
            declaracion.setString(4, apMat);
            declaracion.executeUpdate();
            System.out.println("Operación de inserción completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        String sqlSelectAlumnos = "SELECT * FROM Alumnos";
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlSelectAlumnos);
        ) {
            ResultSet resultSet = declaracion.executeQuery(sqlSelectAlumnos);
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
        int matriculaAfectada=Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del alumno que vas a modificar"));
        String sqlUpdateAlumno = "UPDATE Alumnos SET matricula_id = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ? WHERE matricula_id = "+matriculaAfectada;
        int mat=Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del nuevo alumno"));
        String nom=JOptionPane.showInputDialog("Ingresa el nombre del nuevo alumno");
        String apPat=JOptionPane.showInputDialog("Ingresa el apellido paterno del nuevo alumno");
        String apMat=JOptionPane.showInputDialog("Ingresa el apellido materno del nuevo alumno");
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlUpdateAlumno);
        ) {
            declaracion.setInt(1, mat);
            declaracion.setString(2, nom);
            declaracion.setString(3, apPat);
            declaracion.setString(4, apMat);
            declaracion.executeUpdate();
            System.out.println("Operación de modificar completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sqlDeletAlumno = "DELETE FROM Alumnos WHERE matricula_id = (?)";
        int mat=Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del alumno que quieres borrar"));
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlDeletAlumno);
        ) {
            declaracion.setInt(1, mat);
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