package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_edit extends JFrame {
    private JLabel lFirstName;
    private JLabel lLastName;
    private JLabel lSVN;
    private JLabel lBirthDate;
    private JLabel lStreet;
    private JLabel lStreetNumber;
    private JLabel lPostalCode;
    private JLabel lCity;
    private JLabel lGender;
    private JLabel lNationality;
    private JLabel lInsurance;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfStreet;
    private JTextField tfCity;
    private JComboBox<Gender> cbGender;
    private JComboBox<Nationality> cbNationality;
    private JComboBox<Insurance> cbInsurance;
    private JTextField tfSVN;
    private JTextField tfBirthDate;
    private JTextField tfStreetNumber;
    private JTextField tfPostalCode;
    private JButton btReturn;
    private JPanel contentPane;
    private JButton btEdit;
    private JPanel panel1;
    private JPanel panel2;


    GUI_edit(int id) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Edit Patient");
        setLayout();
        addColor();

        fillEditBoxes();
        fillTextFields(Patient.getPatient(id));

        btReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    GUI_SelectOption gui = new GUI_SelectOption();
                    gui.setVisible(true);
                });
            }
        });

        btEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean edited = editPatient(id);
                if (edited) {
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        GUI_SelectOption gui = new GUI_SelectOption();
                        gui.setVisible(true);
                    });
                }
            }
        });
    }

    public void fillEditBoxes() {
        GUI_add.fillBoxes(cbGender, cbNationality, cbInsurance);
    }

    public void fillTextFields (Patient p) {

        tfFirstName.setText(p.getFirstNamePatients());
        tfLastName.setText(p.getLastNamePatients());
        tfSVN.setText(String.valueOf(p.getSvnPatients()));
        tfBirthDate.setText(p.getBirthDatePatients().toString());
        tfStreet.setText(p.getStreetPatients());
        tfStreetNumber.setText(String.valueOf(p.getStreetNumberPatients()));
        tfPostalCode.setText(String.valueOf(p.getPostalCodePatients()));
        tfCity.setText(p.getCityPatients());
        cbGender.setSelectedItem(Patient.getGender(p.getIdGender()));
        cbNationality.setSelectedItem(Patient.getNationality(p.getIdNationality()));
        cbInsurance.setSelectedItem(Patient.getInsurance(p.getIdInsurance()));

    }

    public boolean editPatient(int id) {

        return Patient.savePatient(id, tfFirstName.getText(), tfLastName.getText(), tfSVN.getText(), tfBirthDate.getText(), tfStreet.getText(),
                tfStreetNumber.getText(), tfPostalCode.getText(), tfCity.getText(), cbGender, cbNationality, cbInsurance);
    }
    public void setLayout(){

        setSize(370,460);
        setLocationRelativeTo(null);
        Color cBackground = new Color(188,238,104);
        contentPane.setBackground(cBackground);
        panel1.setBackground(cBackground);
        panel2.setBackground(cBackground);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(lFirstName, gbc);
        panel1.add(lLastName, gbc);
        panel1.add(lSVN, gbc);
        panel1.add(lBirthDate, gbc);
        panel1.add(lStreet, gbc);
        panel1.add(lStreetNumber, gbc);
        panel1.add(lPostalCode, gbc);
        panel1.add(lCity, gbc);

        gbc.weighty = 1.1;
        panel1.add(lGender, gbc);
        panel1.add(lNationality, gbc);
        panel1.add(lInsurance, gbc);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.CENTER;
        panel2.add(tfFirstName, gbc);
        panel2.add(tfLastName, gbc);
        panel2.add(tfSVN, gbc);
        panel2.add(tfBirthDate, gbc);
        panel2.add(tfStreet, gbc);
        panel2.add(tfStreetNumber, gbc);
        panel2.add(tfPostalCode, gbc);
        panel2.add(tfCity, gbc);
        panel2.add(cbGender, gbc);
        panel2.add(cbNationality, gbc);
        panel2.add(cbInsurance, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,8,5);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(panel1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,5,8,10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(panel2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7,15,15,10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(btReturn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7,10,15,15);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(btEdit, gbc);
    }
    public void addColor (){

        Color cBackground = new Color(202,255,112);
        contentPane.setBackground(cBackground);
        panel1.setBackground(cBackground);
        panel2.setBackground(cBackground);

        Color cButton = new Color (162,205,90);
        btEdit.setBackground(cButton);
        btReturn.setBackground(cButton);

        Color cBorder = new Color (110,139,61);

        Border bTf = BorderFactory.createCompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(1,3,1,3));
        for (Component component : panel2.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setBorder(bTf);
            } else if (component instanceof JOptionPane) {
                ((JOptionPane) component).setBorder(bTf);
            }
        }
    }

}

