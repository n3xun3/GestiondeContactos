package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AddVentana extends JFrame {

    JTextField nombreField, telefonoField;
    JLabel nombreLabel, telefonoLabel;
    JButton okButton, cancelButton;
    DefaultTableModel tablaModelo;
    public AddVentana(DefaultTableModel tablaModelo) {

        this.tablaModelo = tablaModelo;


        setBounds(200, 200, 400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Cierra esta ventana sin cerrar la aplicación principal
        setTitle("Add contact");
        try {
            File iconFile = new File("src/files/icono.png"); // Reemplaza con la ruta de tu archivo de icono
            Image iconImage = new ImageIcon(iconFile.toURI().toURL()).getImage();
            setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);

        nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(20, 20, 80, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(110, 20, 200, 25);
        add(nombreField);

        telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(20, 50, 80, 25);
        add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(110, 50, 200, 25);
        add(telefonoField);

        okButton = new JButton("OK");
        okButton.setBounds(100, 90, 80, 30);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener datos de los campos
                String nombre = nombreField.getText();
                String telefono = telefonoField.getText();

                // Agregar datos a la tabla en la ventana principal
                if (!nombre.isEmpty() && !telefono.isEmpty()) {
                    Object[] rowData = {nombre, telefono};
                    tablaModelo.addRow(rowData); // Agregar nueva fila a la tabla
                }

                dispose(); // Cerrar esta ventana
            }
        });
        add(okButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(200, 90, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana sin realizar ninguna acción
            }
        });
        add(cancelButton);

    }
}
