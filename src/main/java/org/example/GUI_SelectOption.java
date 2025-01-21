package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        initializeMenu();
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
                            "Are you sure you want to delete this patient? :(",
                            "Delete Patient",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Patient.deletePatient(id);
                        JOptionPane.showMessageDialog(null, "Patient deleted");
                        fillPatientTable();
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "Please select a patient");
                }
            }
        });
    }

    public void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem printItem = new JMenuItem("Print");
        fileMenu.add(printItem);
        printItem.addActionListener(e -> Menu.printTable(tPatients));

        JMenuItem exportItem = new JMenuItem("Export all to CSV");
        fileMenu.add(exportItem);
        exportItem.addActionListener(e -> Menu.exportToCSV(tPatients));

        JMenuItem exportPatientItem = new JMenuItem("Export Patient to CSV");
        fileMenu.add(exportPatientItem);
        exportPatientItem.addActionListener(e -> Menu.exportPatientToCSV(tPatients));

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);

        JMenuItem helpItem = new JMenuItem("Helpdesk");
        helpMenu.add(helpItem);
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "We don't know either :( \\n Please just ask Google");
            }
        });

        menuBar.add(Box.createHorizontalGlue());

        JMenu logoutMenu = new JMenu("Logout");
        menuBar.add(logoutMenu);
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutMenu.add(logoutItem);
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    GUI_Login gui = new GUI_Login();
                    gui.setVisible(true);
                });
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

}
