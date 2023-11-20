package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Vista extends JFrame {
    private JButton buttonDelete, buttonEdit, buttonAddContact;
    private JTable table;

    private JLabel titleLabel;
    private DefaultTableModel defaultTableModel;

    public Vista() {
        setBounds(100, 100, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Contact");
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Cambia el color de fondo de la ventana
        try {
            File iconFile = new File("src/files/icono.png"); // Reemplaza con la ruta de tu archivo de icono
            Image iconImage = new ImageIcon(iconFile.toURI().toURL()).getImage();
            setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeVariables();
        setVisible(true);
    }

    public void initializeVariables() {
        Font buttonFont = new Font("Arial", Font.BOLD, 16); // Define una fuente para los botones

        buttonDelete = new JButton();
        buttonDelete.setBounds(500, 260, 100, 61);
        buttonDelete.setOpaque(false); // Hace que el botón sea transparente
        buttonDelete.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        buttonDelete.setBorderPainted(false); // Quita el borde del botón
        try {
            ImageIcon deleteIcon = new ImageIcon("src/files/eliminar.png"); // Reemplaza con la ruta de tu icono
            Image img = deleteIcon.getImage();
            Image newImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Cambia el tamaño del icono

            buttonDelete.setIcon(new ImageIcon(newImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    defaultTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Vista.this, "Select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(buttonDelete);

        buttonEdit = new JButton();
        buttonEdit.setBounds(500, 150, 100, 61);
        buttonEdit.setOpaque(false); // Hace que el botón sea transparente
        buttonEdit.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        buttonEdit.setBorderPainted(false); // Quita el borde del botón
        try {
            ImageIcon editIcon = new ImageIcon("src/files/editar.png"); // Reemplaza con la ruta de tu icono
            Image img = editIcon.getImage();
            Image newImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Cambia el tamaño del icono

            buttonEdit.setIcon(new ImageIcon(newImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nombre = (String) defaultTableModel.getValueAt(selectedRow, 0);
                    String telefono = (String) defaultTableModel.getValueAt(selectedRow, 1);
                    EditVentana editarVentana = new EditVentana(defaultTableModel, selectedRow, nombre, telefono);
                    editarVentana.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Vista.this, "Select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(buttonEdit);

        buttonAddContact = new JButton();
        buttonAddContact.setBounds(130, 450, 300, 100);
        buttonAddContact.setOpaque(false); // Hace que el botón sea transparente
        buttonAddContact.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        buttonAddContact.setBorderPainted(false); // Quita el borde del botón
        try {
            ImageIcon addIcon = new ImageIcon("src/files/addContact.png"); // Reemplaza con la ruta de tu icono
            Image img = addIcon.getImage();
            Image newImg = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH); // Cambia el tamaño del icono

            buttonAddContact.setIcon(new ImageIcon(newImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonAddContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddVentana addVentana = new AddVentana(defaultTableModel);
                addVentana.setVisible(true);
            }
        });
        add(buttonAddContact);

        String[] nombreColumnas = {"Name", "Phone"};
        defaultTableModel = new DefaultTableModel(nombreColumnas, 0);
        table = new JTable(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(103, 90, 380, 350);
        add(jScrollPane);

        cargarDatosPorDefecto("src/files/datos.txt");
    }

    public void cargarDatosPorDefecto(String archivo) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(","); // Suponiendo que los datos en el archivo están separados por comas
                if (datos.length == 2) {
                    defaultTableModel.addRow(datos); // Agregar fila a la tabla con los datos del archivo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



