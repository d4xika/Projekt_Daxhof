package org.example.gui;

import org.example.model.Patient;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel pButton;

    public GUI_SelectOption() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Select option");
        setLayout();
        addColor();

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
                //Thread für Search
                new Thread(GUI_SelectOption.this::fillPatientTable).start();
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
                } else {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
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

                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete this patient? :(",
                            "Delete Patient",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        //Thread für Delete
                        new Thread(() -> {
                            Patient.deletePatient(id);
                            SwingUtilities.invokeLater(() -> {
                                UIManager.put("OptionPane.background", SetLayout.cBackground);
                                UIManager.put("Panel.background", SetLayout.cBackground);
                                JOptionPane.showMessageDialog(null, "Patient deleted");
                                fillPatientTable();
                            });
                        }).start();
                    }
                } else {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Please select a patient");
                }
            }
        });
    }

    public void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(SetLayout.cButton);
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setBackground(SetLayout.cButton);
        menuBar.add(fileMenu);

        JMenuItem printItem = new JMenuItem("Print");
        printItem.setBackground(SetLayout.cButton);
        fileMenu.add(printItem);
        printItem.addActionListener(e -> Menu.printTable(tPatients));

        JMenuItem exportItem = new JMenuItem("Export all to CSV");
        exportItem.setBackground(SetLayout.cButton);
        fileMenu.add(exportItem);
        exportItem.addActionListener(e -> Menu.exportToCSV(tPatients));

        JMenuItem exportPatientItem = new JMenuItem("Export Patient to CSV");
        exportPatientItem.setBackground(SetLayout.cButton);
        fileMenu.add(exportPatientItem);
        exportPatientItem.addActionListener(e -> Menu.exportPatientToCSV(tPatients));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setBackground(SetLayout.cButton);
        menuBar.add(helpMenu);

        JMenuItem helpItem = new JMenuItem("Helpdesk");
        helpItem.setBackground(SetLayout.cButton);
        helpMenu.add(helpItem);
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.background", SetLayout.cBackground);
                UIManager.put("Panel.background", SetLayout.cBackground);
                JOptionPane.showMessageDialog(null, "We don't know either :(\nPlease just ask Google");
            }
        });

        menuBar.add(Box.createHorizontalGlue());

        JMenu logoutMenu = new JMenu("Logout");
        logoutMenu.setBackground(SetLayout.cButton);
        menuBar.add(logoutMenu);
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setBackground(SetLayout.cButton);
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
        new Thread(() -> {
            List<Patient> patients;

            if (tfPatientName.getText().isEmpty()) {
                patients = Patient.getAllPatients();
            } else {
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
            tableModel.addColumn("SVN");
            tableModel.addColumn("Birth Date");
            tableModel.addColumn("Street");
            tableModel.addColumn("Str.Nr.");
            tableModel.addColumn("Post.Co.");
            tableModel.addColumn("City");
            tableModel.addColumn("Gender");
            tableModel.addColumn("Nationality");
            tableModel.addColumn("Insurance");

            tPatients.getTableHeader().setReorderingAllowed(false);
            tPatients.setModel(tableModel);
            SetLayout.customizeTable(tPatients);

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
            tPatients.setRowSorter(sorter);
            sorter.setSortable(3, false);
            sorter.setSortable(5, false);
            sorter.setSortable(6, false);

            for (Patient patient : patients) {
                tableModel.addRow(new Object[]{
                        patient.getIdPatients(), patient.getFirstNamePatients(), patient.getLastNamePatients(),
                        patient.getSvnPatients(), patient.getBirthDatePatients(), patient.getStreetPatients(),
                        patient.getStreetNumberPatients(), patient.getPostalCodePatients(), patient.getCityPatients(),
                        Patient.getGender(patient.getIdGender()), Patient.getNationality(patient.getIdNationality()), Patient.getInsurance(patient.getIdInsurance()),
                });
            }

            SetLayout.customizeTable(tPatients);
            SwingUtilities.invokeLater(() -> {
                if (patients.isEmpty()) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "No patients found");
                }
            });
        }).start();
    }

    public void setLayout() {
        setSize(1100, 600);
        setLocationRelativeTo(null);
        SetLayout.setSOLayout(contentPane, tfPatientName, spPatientsFound, lEnter, pButton, btSearch, btAdd, btEdit, btDelete);
    }

    public void addColor() {
        SetLayout.setSOColor(contentPane, spPatientsFound, pButton, btSearch, btAdd, btEdit, btDelete);
    }
}
