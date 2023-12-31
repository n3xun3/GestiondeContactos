package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVentana extends JDialog {

    JTextField nombreField, telefonoField;
    JLabel nombreLabel, telefonoLabel;
    JButton okButton, cancelButton;
    DefaultTableModel tablaModelo;

    public AddVentana(JFrame parent, DefaultTableModel tablaModelo) {
        super(parent, "Add contact", true);
        this.tablaModelo = tablaModelo;

        setBounds(200, 200, 400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(null);

        nombreLabel = new JLabel("Name:");
        nombreLabel.setBounds(20, 20, 80, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(110, 20, 200, 25);
        add(nombreField);

        telefonoLabel = new JLabel("Phone:");
        telefonoLabel.setBounds(20, 50, 80, 25);
        add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(110, 50, 200, 25);
        add(telefonoField);

        okButton = new JButton("OK");
        okButton.setBounds(100, 90, 80, 30);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String telefono = telefonoField.getText();

                if (nombre.isEmpty() || !nombre.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre válido (solo texto).");
                    return;
                }
                if (!telefono.matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de teléfono válido (9 dígitos numéricos).");
                    return;
                }

                Object[] rowData = {nombre, telefono};
                tablaModelo.addRow(rowData);

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
