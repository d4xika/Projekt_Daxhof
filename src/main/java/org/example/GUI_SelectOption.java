package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI_SelectOption extends JFrame {
    private JPanel contentPane;
    private JTextField tfEnter;
    private JTextField tfPatientName;
    private JButton btSearch;
    private JScrollPane spPatientsFound;
    private JButton btEdit;
    private JList<Patient> listPatients;
    private JButton btAdd;
    private JButton btDelete;

    public GUI_SelectOption() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Edit Patient");
        setTitle("Select option");
        setLocationRelativeTo(null);
        pack();

        fillScrollPanel();

        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    GUI_add gui = new GUI_add();
                    gui.setVisible(true);
                });
            }
        });
        btSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScrollPanel();
            }
        });
    }

    public void fillScrollPanel () {

        DefaultListModel<Patient> listModel = new DefaultListModel<Patient>();
        List<Patient> patients = Patient.getAllPatients();

        patients.forEach(listModel::addElement);

        if (!listModel.isEmpty()) {
            listPatients.setModel(listModel);
        }
    }

    public void updateScrollPanel () {

        DefaultListModel<Patient> listModel = new DefaultListModel<Patient>();
        List<Patient> patients = Patient.searchPatients(tfPatientName.getText());
        patients.forEach(listModel::addElement);

        if (!listModel.isEmpty()) {
            listPatients.setModel(listModel);
        }
    }
}
