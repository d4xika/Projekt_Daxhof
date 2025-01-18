package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.List;

public class GUI_SelectOption extends JFrame {
    private JPanel contentPane;
    private JTextField tfEnter;
    private JTextField tfPatientName;
    private JButton btSearch;
    private JScrollPane spPatientsFound;
    private JButton btEdit;
    private JButton btAdd;
    private JButton btDelete;
    private JTable tPatients;

    public GUI_SelectOption() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Select option");
        setLocationRelativeTo(null);
        pack();

        fillScrollPanel();

        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    GUI_add gui = new GUI_add();
                    gui.setVisible(true);
                });
            }
        });
        btSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillScrollPanel();

            }
        });

        btEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = tPatients.getSelectedRow();

                if (row > 0) {

                    DefaultTableModel model = (DefaultTableModel) tPatients.getModel();
                    int id = (int) model.getValueAt(row, 0);

                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        GUI_edit gui = new GUI_edit(id);
                        gui.setVisible(true);
                    });
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please select a patient");
                }
            }
        });

    }

    public void fillScrollPanel () {

        List<Patient> patients;

        if(tfPatientName.getText().isEmpty()) {
            patients = Patient.getAllPatients();
        }else {
            patients = Patient.searchPatients(tfPatientName.getText());
        }

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID");
        tableModel.addColumn("first Name");
        tableModel.addColumn("last Name");
        tableModel.addColumn("SVN Number");
        tableModel.addColumn("Birth Date");
        tableModel.addColumn("Street");
        tableModel.addColumn("Street Number");
        tableModel.addColumn("Postal Code");
        tableModel.addColumn("City");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Nationality");
        tableModel.addColumn("Insurance");

        for (Patient patient : patients) {
            tableModel.addRow(new Object[] {
                patient.getIdPatients(), patient.getFirstNamePatients(), patient.getLastNamePatients(),
                patient.getSvnPatients(), patient.getBirthDatePatients(), patient.getStreetPatients(),
                patient.getStreetNumberPatients(), patient.getPostalCodePatients(), patient.getCityPatients(),
                patient.getIdGender(), patient.getIdNationality(), patient.getIdInsurance()
            });
        }

        tPatients.setModel(tableModel);
        if (patients.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No patients found");
        }
    }

}
