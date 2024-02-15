import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AsigAlumnoCrudOperations implements CrudOperations {

    @Override
    public void create() {
        int asignatura_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura"));
        int matricula_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del alumno"));
        double calificacion = Double.parseDouble(JOptionPane.showInputDialog("Ingresa la calificación del alumno"));
        String sqlInsertAlumno_Asig = "INSERT INTO alumno_asignatura (calificacion, asignatura_id, matricula_id) VALUES (?, ?, ?) ";
         try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlInsertAlumno_Asig)
        ) {
            declaracion.setDouble(1, calificacion);
            declaracion.setInt(2, asignatura_id);
            declaracion.setInt(3, matricula_id);
            declaracion.executeUpdate();
            System.out.println("Operación de inserción completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        String sqlReadAlumno_Asig = "SELECT * FROM alumno_asignatura";
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlReadAlumno_Asig)
        ) {
            ResultSet resultSet = declaracion.executeQuery(sqlReadAlumno_Asig);
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
        int matriculaAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del alumno al que deseas modificar"));
        int asignatura_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la nueva asignatura"));
        int matricula_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del nuevo alumno"));
        double calificacion = Double.parseDouble(JOptionPane.showInputDialog("Ingresa la nueva calificación del alumno"));
        String sqlUpdateAlumno_Asig = "UPDATE alumno_asignatura SET calificacion = ?, asignatura_id = ?, matricula_id = ? WHERE matricula_id ="+matriculaAfectada;
         try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlUpdateAlumno_Asig)
        ) {
            declaracion.setDouble(1, calificacion);
            declaracion.setInt(2, asignatura_id);
            declaracion.setInt(3, matricula_id);
            declaracion.executeUpdate();
            System.out.println("Operación de modificar completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        int matriculaAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la matricula del alumno del cuál quieres borrar su calificación"));
        String sqlDeleteAlumno_Asig = "DELETE FROM alumno_asignatura WHERE matricula_id = (?)";
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlDeleteAlumno_Asig);
        ) {
            declaracion.setInt(1, matriculaAfectada);
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
