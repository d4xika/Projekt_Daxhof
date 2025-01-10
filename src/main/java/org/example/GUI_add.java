package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;


public class GUI_add extends JFrame {
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfSVN;
    private JTextField tfBirthDate;
    private JTextField tfStreet;
    private JTextField tfStreetNumber;
    private JTextField tfPostalCode;
    private JTextField tfCity;
    private JComboBox<String> cbGender;
    private JComboBox<String> cbNationality;
    private JComboBox<String> cbInsurance;
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
    private JPanel contentPane;
    private JButton btReturn;
    private JButton btAdd;

    GUI_add() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Add Patient");
        pack();

        fillBoxes();

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
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPatient();
            }
        });
    }

    String url = "jdbc:mysql://localhost:3306/projekt_daxhof";
    String user = "root";
    String password = "mOrtible4827!#";
    Connection connection = null;

    public void fillBoxes () {
        List<Gender> genders= Patient.getGenderList();
        genders.forEach(gender -> cbGender.addItem(gender.getGenderName()));
        List<Nationality> nationalities= Patient.getNationalityList();
        nationalities.forEach(nationality -> cbNationality.addItem(nationality.getNationalityName()));
        List<Insurance> insurances= Patient.getInsuranceList();
        insurances.forEach(insurance -> cbInsurance.addItem(insurance.getInsuranceName()));
    }

    public void addNewPatient() {

        boolean success = false;

        while (!success) {

            if (tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfSVN.getText().isEmpty() || tfBirthDate.getText().isEmpty() || tfStreet.getText().isEmpty() ||
                    tfStreetNumber.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfCity.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all information");
                break;
            }

            long SVN = 0;
            try {
                SVN = Long.parseLong(tfSVN.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid SVN number");
                break;
            }

            Date birthDate = null;
            try {
                birthDate = java.sql.Date.valueOf(tfBirthDate.getText());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid birth date");
                break;
            }

            int streetNumber = 0;
            try {
                streetNumber = Integer.parseInt(tfStreet.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid street number");
                break;
            }

            int postalCode = 0;
            try {
                postalCode = Integer.parseInt(tfPostalCode.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid postal code");
                break;
            }

            Patient.addPatient(tfFirstName.getText(), tfLastName.getText(), SVN, birthDate, tfStreet.getText(),
                    streetNumber, postalCode, tfCity.getText(), cbGender.getSelectedIndex(), cbNationality.getSelectedIndex(),
                    cbInsurance.getSelectedIndex());
            success = true;
            System.out.println("patient successfully added");
        }
    }



}

