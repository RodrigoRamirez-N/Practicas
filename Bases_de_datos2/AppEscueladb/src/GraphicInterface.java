import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicInterface extends JFrame implements ActionListener {
    // Componentes GUI
    private JLabel textLabel;
    private JButton createButton, readButton, updateButton, deleteButton;
    private JComboBox<String> operationComboBox;
    private JPanel cardPanel;

    private CrudOperations currentCrudOperations; // Variable para almacenar la instancia específica de operaciones CRUD

    public GraphicInterface() {
        // Configuración del JFrame
        setTitle("Operaciones CRUD");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Container container = getContentPane();
        //setLayout(new BoxLayout(container, 500));

        // Configuración del ComboBox y sus opciones
        operationComboBox = new JComboBox<>(new String[]{"Alumnos","Profesores", "Aulas", "Asignaturas", "Asignatura-Aula", "Asignatura-Alumno"});
        operationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (e.getSource() == operationComboBox) {
                    // Cambiar la "carta" actual según la opción seleccionada en el ComboBox
                    CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                    cardLayout.show(cardPanel, (String) operationComboBox.getSelectedItem());

                    String selectedTable = (String) operationComboBox.getSelectedItem();

                    // Crear la instancia específica de operaciones CRUD según la opción seleccionada
                    switch (selectedTable) {
                        case "Alumnos":
                            currentCrudOperations = new AlumnosCrudOperations();
                            break;
                        case "Profesores":
                            currentCrudOperations = new ProfesoresCrudOperations();
                            break;
                        case "Aulas":
                            currentCrudOperations = new AulasCrudOperations();
                            break;
                        case "Asignaturas":
                            currentCrudOperations = new AsignaturasCrudOperations();
                            break;    
                        case "Asignatura-Aula":
                            currentCrudOperations = new AsigAulaCrudOperations();
                            break;
                        case "Asignatura-Alumno":
                            currentCrudOperations = new AsigAlumnoCrudOperations();
                            break;            
                        default:
                            currentCrudOperations = null;
                        break;
                    }
                }
            }
        }); //addActionListener should call for a new instance of actionListener to listen for changes

        // Configuración del CardLayout
        cardPanel = new JPanel(new CardLayout());

        JPanel alumnoPanel = new JPanel();
        JPanel profesorPanel = new JPanel();
        JPanel aulaPanel = new JPanel();
        JPanel asignaturaPanel = new JPanel();
        JPanel asig_aulaPanel = new JPanel();
        JPanel asig_alumnoPanel = new JPanel();

        cardPanel.add(alumnoPanel, "Alumnos");
        cardPanel.add(profesorPanel, "Profesores");
        cardPanel.add(aulaPanel, "Aulas");
        cardPanel.add(asignaturaPanel, "Asignaturas");
        cardPanel.add(asig_aulaPanel, "Asignatura-Aula");
        cardPanel.add(asig_alumnoPanel, "Asignatura-Alumno");

        // Inicialización de componentes
        textLabel = new JLabel();
        textLabel.setText("Selecciona una de las siguientes operaciones CRUD");
        createButton = new JButton("CREATE");
        readButton = new JButton("READ");
        updateButton = new JButton("UPDATE");
        deleteButton = new JButton("DELETE");

        // Agregar ActionListener a los botones
        createButton.addActionListener(this);
        readButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        // Agregar componentes al JFrame
        add(textLabel);
        add(createButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(operationComboBox);
        container.add(cardPanel);

        // Hacer visible la GUI
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Lógica de operaciones CRUD
        
        if (e.getSource() == createButton) {
            // Llamado al método crear en la instancia que retorno el switch-case
            currentCrudOperations.create();
            JOptionPane.showMessageDialog(this, "Operación CREATE ejecutada en la tabla actual");
        } else if (e.getSource() == readButton) {
            // Llamado al método read en la instancia que retorno el switch-case
            currentCrudOperations.read();
            JOptionPane.showMessageDialog(this, "Operación READ ejecutada en la tabla actual");
        } else if (e.getSource() == updateButton) {
            currentCrudOperations.update();
            // Llamado al método update en la instancia que retorno el switch-case
            JOptionPane.showMessageDialog(this, "Operación UPDATE ejecutada en la tabla actual");
        } else if (e.getSource() == deleteButton) {
            // Llamado al método delete en la instancia que retorno el switch-case
            currentCrudOperations.delete();
            JOptionPane.showMessageDialog(this, "Operación DELETE ejecutada en la tabla actual");
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphicInterface());
    }
}
