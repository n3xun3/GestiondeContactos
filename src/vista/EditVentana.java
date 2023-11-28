package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class EditVentana extends JDialog {
    JTextField nombreField, telefonoField;
    JLabel nameLabel, phoneLabel;
    JButton okButton, cancelButton;
    DefaultTableModel tablaModelo;
    int filaSeleccionada;

    public EditVentana(JFrame parent, DefaultTableModel tablaModelo, int filaSeleccionada, String nombre, String telefono) {
        super(parent, "Editar Contacto", true);
        this.tablaModelo = tablaModelo;
        this.filaSeleccionada = filaSeleccionada;

        setBounds(300, 300, 400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            File iconFile = new File("src/files/icono.png");
            Image iconImage = new ImageIcon(iconFile.toURI().toURL()).getImage();
            setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 20, 80, 25);
        add(nameLabel);

        nombreField = new JTextField(nombre);
        nombreField.setBounds(110, 20, 200, 25);
        add(nombreField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 50, 80, 25);
        add(phoneLabel);

        telefonoField = new JTextField(telefono);
        telefonoField.setBounds(110, 50, 200, 25);
        add(telefonoField);

        okButton = new JButton("OK");
        okButton.setBounds(100, 90, 80, 30);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nuevoNombre = nombreField.getText();
                String nuevoTelefono = telefonoField.getText();

                if (nuevoNombre.isEmpty() || !nuevoNombre.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre válido (solo texto).");
                    return;
                }

                if (!nuevoTelefono.matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de teléfono válido (9 dígitos numéricos).");
                    return;
                }

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
