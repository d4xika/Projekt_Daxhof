package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class GUI_SelectOption extends JFrame {
    private JPanel contentPane;
    private JTextField tfPatientName;
    private JButton btSearch;
    private JScrollPane spPatientsFound;
    private JButton btEdit;
    private JButton btAdd;
    private JButton btDelete;
    private JTable tPatients;
    private JLabel lEnter;
    private JLabel lPicture;
    private JPanel pButton;

    public GUI_SelectOption() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Select option");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setLayout();

        fillPatientTable();

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
                fillPatientTable();

            }
        });

        btEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = tPatients.getSelectedRow();

                if (row >= 0) {

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

        btDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = tPatients.getSelectedRow();

                if (row >= 0) {

                    DefaultTableModel model = (DefaultTableModel) tPatients.getModel();
                    int id = (int) model.getValueAt(row, 0);


                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete this patient?",
                            "Delete Patient",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Patient.deletePatient(id);
                        fillPatientTable();
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "Please select a patient");
                }
            }
        });
    }

    public void fillPatientTable() {

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

    public void setLayout () {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 15, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lEnter, gbc);

        JTextField tfPatientName = new JTextField(30);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 20, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(tfPatientName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 10);
        gbc.fill = GridBagConstraints.NONE;
        contentPane.add(btSearch, gbc);

        JPanel pButton = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = GridBagConstraints.RELATIVE;
        gbcButton.weighty = 0;
        gbcButton.gridheight = 3;
        gbcButton.fill = GridBagConstraints.HORIZONTAL;
        gbcButton.insets = new Insets(15, 5, 15, 5);
        pButton.add(btAdd, gbcButton);
        pButton.add(btEdit, gbcButton);
        pButton.add(btDelete, gbcButton);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(15, 10, 15, 15);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        contentPane.add(pButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 15, 15, 10);
        gbc.fill = GridBagConstraints.BOTH;
        spPatientsFound.getViewport().setOpaque(false);
        spPatientsFound.setViewportView(tPatients);
        contentPane.add(spPatientsFound, gbc);

    }

}

