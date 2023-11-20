package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class EditVentana extends JFrame {
    JTextField nombreField, telefonoField;
    JButton okButton, cancelButton;
    DefaultTableModel tablaModelo;
    int filaSeleccionada;

    public EditVentana(DefaultTableModel tablaModelo, int filaSeleccionada, String nombre, String telefono) {
        this.tablaModelo = tablaModelo;
        this.filaSeleccionada = filaSeleccionada;

        setBounds(300, 300, 400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Editar Contacto");
        try {
            File iconFile = new File("src/files/icono.png"); // Reemplaza con la ruta de tu archivo de icono
            Image iconImage = new ImageIcon(iconFile.toURI().toURL()).getImage();
            setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);

        nombreField = new JTextField(nombre);
        nombreField.setBounds(110, 20, 200, 25);
        add(nombreField);

        telefonoField = new JTextField(telefono);
        telefonoField.setBounds(110, 50, 200, 25);
        add(telefonoField);

        okButton = new JButton("OK");
        okButton.setBounds(100, 90, 80, 30);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nuevoNombre = nombreField.getText();
                String nuevoTelefono = telefonoField.getText();

                tablaModelo.setValueAt(nuevoNombre, filaSeleccionada, 0);
                tablaModelo.setValueAt(nuevoTelefono, filaSeleccionada, 1);

                dispose();
            }
        });
        add(okButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(200, 90, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton);
    }
}
