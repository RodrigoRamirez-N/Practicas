import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

public class AsigAulaCrudOperations implements CrudOperations {

    @Override
    public void create() {
        int asignatura_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura"));
        int aula_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número del aula"));
        Time hora = Time.valueOf(JOptionPane.showInputDialog("Ingresa la hora de la asignatura"));
        String dia = JOptionPane.showInputDialog("Ingresa el día de la asignatura");
        String sqlInsertAsig_Aula = "INSERT INTO asignatura_aula (asignatura_id, aula_id, hora, dia) VALUES (?, ?, ?, ?) ";
         try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlInsertAsig_Aula)
        ) {
            declaracion.setInt(1, asignatura_id);
            declaracion.setInt(2, aula_id);
            declaracion.setTime(3, hora);
            declaracion.setString(4, dia);
            declaracion.executeUpdate();
            System.out.println("Operación de inserción completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        String sqlReadAsigAula = "SELECT * FROM asignatura_aula";
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlReadAsigAula)
        ) {
            ResultSet resultSet = declaracion.executeQuery(sqlReadAsigAula);
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
        int asignaturaAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura que quieres cambiar"));
        String sqlUpdateAsig_Aula = "UPDATE asignatura_aula SET asignatura_id = ?, aula_id = ?, hora = ?, dia = ? WHERE asignatura_id = "+asignaturaAfectada;
        int asignatura_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura"));
        int aula_id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número del aula"));
        Time hora = Time.valueOf(JOptionPane.showInputDialog("Ingresa la hora de la asignatura"));
        String dia = JOptionPane.showInputDialog("Ingresa el día de la asignatura");
         try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlUpdateAsig_Aula)
        ) {
            declaracion.setInt(1, asignatura_id);
            declaracion.setInt(2, aula_id);
            declaracion.setTime(3, hora);
            declaracion.setString(4, dia);
            declaracion.executeUpdate();
            System.out.println("Operación de modificar completada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {        
        int asignaturaAfectada = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código de la asignatura que quieres borrar"));
        String sqlDeleteAsig_Aula = "DELETE FROM asignatura_aula WHERE asignatura_id = (?)";
        try (
            Connection con = connection();
            PreparedStatement declaracion = con.prepareStatement(sqlDeleteAsig_Aula);
        ) {
            declaracion.setInt(1, asignaturaAfectada);
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
