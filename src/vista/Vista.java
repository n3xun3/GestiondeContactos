package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame {
    JButton buttonDelete, buttonEdit, buttonAddContact;
    JTable table;

    DefaultTableModel defaultTableModel;
    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
        this.defaultTableModel = defaultTableModel;
    }

    JScrollPane jScrollPane;

    public Vista(){
        setBounds(100,100,790,770);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        initializeVariables();
        setVisible(true);
    }

    public void  initializeVariables(){
        buttonDelete = new JButton("Deleted");
        buttonDelete.setBounds(600,360,100,61);
        add(buttonDelete);

        buttonEdit = new JButton("Add");
        buttonEdit.setBounds(600,100,100,61);
        add(buttonEdit);

        buttonAddContact = new JButton("Add Contact");
        buttonAddContact.setBounds(90,600, 300,61);
        add(buttonAddContact);

        String[] nombreColumnas = {"Nombre","Telefono"};
        defaultTableModel = new DefaultTableModel(nombreColumnas,0);
        table = new JTable(defaultTableModel);
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(133,118,258,200);
        add(jScrollPane);
    }

}
