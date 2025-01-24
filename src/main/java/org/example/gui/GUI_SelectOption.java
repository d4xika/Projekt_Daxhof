package org.example.gui;

import org.example.model.Patient;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


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
    private final Color cBackground = new Color(202,255,112);
    private final Color cButton = new Color (162,205,90);
    private final Color cBorder = new Color (110,139,61);

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
                    UIManager.put("OptionPane.background", cBackground);
                    UIManager.put("Panel.background", cBackground);
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


                    UIManager.put("OptionPane.background", cBackground);
                    UIManager.put("Panel.background", cBackground);
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete this patient? :(",
                            "Delete Patient",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        UIManager.put("OptionPane.background", cBackground);
                        UIManager.put("Panel.background", cBackground);
                        Patient.deletePatient(id);
                        UIManager.put("OptionPane.background", cBackground);
                        UIManager.put("Panel.background", cBackground);
                        JOptionPane.showMessageDialog(null, "Patient deleted");
                        fillPatientTable();
                    }


                }else {
                    UIManager.put("OptionPane.background", cBackground);
                    UIManager.put("Panel.background", cBackground);
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
        menuBar.add(helpMenu);

        JMenuItem helpItem = new JMenuItem("Helpdesk");
        helpMenu.add(helpItem);
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.background", cBackground);
                UIManager.put("Panel.background", cBackground);
                JOptionPane.showMessageDialog(null, "We don't know either :(\nPlease just ask Google");
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

        customizeTable(tPatients);
        if (patients.isEmpty()) {
            UIManager.put("OptionPane.background", cBackground);
            UIManager.put("Panel.background", cBackground);
            JOptionPane.showMessageDialog(null, "No patients found");
        }
    }

    public void setLayout() {

        setSize(1100,600);
        setLocationRelativeTo(null);
        Color cBackground = new Color(188,238,104);
        contentPane.setBackground(cBackground);
        pButton.setBackground(cBackground);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 15, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lEnter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 15, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(tfPatientName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.ipadx = 10;
        gbc.insets = new Insets(5, 0, 5, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(btSearch, gbc);

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
        gbc.insets = new Insets(15, 7, 15, 15);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        contentPane.add(pButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 15, 15, 8);
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(spPatientsFound, gbc);
    }
    public void addColor (){

        contentPane.setBackground(cBackground);
        pButton.setBackground(cBackground);

        spPatientsFound.getViewport().setBackground(cButton);
        btSearch.setBackground(cButton);
        btAdd.setBackground(cButton);
        btEdit.setBackground(cButton);
        btDelete.setBackground(cButton);

        btSearch.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,3,2,3)));
        btAdd.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,3,2,3)));
        btEdit.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,3,2,3)));
        btDelete.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,5,2,5)));
    }
    public void customizeTable(JTable tPatients) {
        // Spaltenbreite anpassen
        TableColumnModel columnModel = tPatients.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);//ID
        columnModel.getColumn(1).setPreferredWidth(160);//firtName
        columnModel.getColumn(2).setPreferredWidth(175);//lastName
        columnModel.getColumn(3).setPreferredWidth(150);//SVN
        columnModel.getColumn(4).setPreferredWidth(150);//Birthdate
        columnModel.getColumn(5).setPreferredWidth(200);//Street Name
        columnModel.getColumn(6).setPreferredWidth(80);//Street Number
        columnModel.getColumn(7).setPreferredWidth(110);//Postalcode
        columnModel.getColumn(8).setPreferredWidth(150);//City
        columnModel.getColumn(9).setPreferredWidth(100);//Gender
        columnModel.getColumn(10).setPreferredWidth(150);//Natiionality
        columnModel.getColumn(11).setPreferredWidth(125);//Insurance


            tPatients.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    JTableHeader header = tPatients.getTableHeader();
                    tPatients.getTableHeader().setPreferredSize(new Dimension(0, 30));
                    header.setBackground(cButton);
                    header.setFont(new Font("Arial", Font.BOLD, 12));
                    return c;
                }
            });

        }


}

