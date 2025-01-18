package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class GUI_add extends JFrame {
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfStreet;
    private JTextField tfCity;
    private JComboBox<Gender> cbGender;
    private JComboBox<Nationality> cbNationality;
    private JComboBox<Insurance> cbInsurance;
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
    private JTextField tfStreetNumber;
    private JTextField tfSVN;
    private JTextField tfPostalCode;
    private JTextField tfBirthDate;
    private JPanel panel1;
    private JPanel panel2;

    GUI_add() {
        setTitle("Add Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setSize(370,460);
        setLocationRelativeTo(null);


        GridBagConstraints gbcP1 = new GridBagConstraints();

            //Panel1 NOCH IMMER VERSETZT
            gbcP1.gridx = 0;
            gbcP1.gridy = GridBagConstraints.RELATIVE;
            gbcP1.weightx = 1;
            gbcP1.weighty = 0.5;
            gbcP1.fill = GridBagConstraints.NONE;
            gbcP1.insets = new Insets(5,5,5,5);
            gbcP1.anchor = GridBagConstraints.EAST;
            panel1.add(lFirstName, gbcP1);
            panel1.add(lLastName, gbcP1);
            panel1.add(lSVN, gbcP1);
            panel1.add(lBirthDate, gbcP1);
            panel1.add(lStreet, gbcP1);
            panel1.add(lStreetNumber, gbcP1);
            panel1.add(lPostalCode, gbcP1);
            panel1.add(lCity, gbcP1);

            gbcP1.weighty = 1.1;
            panel1.add(lGender, gbcP1);
            panel1.add(lNationality, gbcP1);
            panel1.add(lInsurance, gbcP1);


        GridBagConstraints gbcP2 = new GridBagConstraints();

            //Panel2
            gbcP2.gridx = 0;
            gbcP2.gridy = GridBagConstraints.RELATIVE;
            gbcP2.weightx = 1;
            gbcP2.fill = GridBagConstraints.HORIZONTAL;
            gbcP2.insets = new Insets(5,5,5,5);
            gbcP2.anchor = GridBagConstraints.CENTER;
            panel2.add(tfFirstName, gbcP2);
            panel2.add(tfLastName, gbcP2);
            panel2.add(tfSVN, gbcP2);
            panel2.add(tfBirthDate, gbcP2);
            panel2.add(tfStreet, gbcP2);
            panel2.add(tfStreetNumber, gbcP2);
            panel2.add(tfPostalCode, gbcP2);
            panel2.add(tfCity, gbcP2);
            panel2.add(cbGender, gbcP2);
            panel2.add(cbNationality, gbcP2);
            panel2.add(cbInsurance, gbcP2);

        GridBagConstraints gbcCP = new GridBagConstraints(); //für ContentPane bzw. JPanel

            //Panel1
            gbcCP.gridx = 0;
            gbcCP.gridy = 0;
            gbcCP.weightx = 0.7;
            gbcCP.weighty = 1;
            gbcCP.fill = GridBagConstraints.BOTH;
            gbcCP.insets = new Insets(10,10,8,5);
            gbcCP.anchor = GridBagConstraints.CENTER;
            contentPane.add(panel1,gbcCP);

            //Panel2
            gbcCP.gridx = 1;
            gbcCP.gridy = 0;
            gbcCP.weightx = 3;
            gbcCP.weighty = 1;
            gbcCP.fill = GridBagConstraints.HORIZONTAL;
            gbcCP.insets = new Insets(10,5,8,10);
            gbcCP.anchor = GridBagConstraints.CENTER;
            contentPane.add(panel2, gbcCP);

            //Button Return
            gbcCP.gridx = 0;
            gbcCP.gridy = 1;
            gbcCP.weightx = 0.7;
            gbcCP.fill = GridBagConstraints.HORIZONTAL;
            gbcCP.insets = new Insets(7,15,15,10);
            gbcCP.anchor = GridBagConstraints.CENTER;
            contentPane.add(btReturn, gbcCP);

            //Button Add
            gbcCP.gridx = 1;
            gbcCP.gridy = 1;
            gbcCP.weightx = 3;
            gbcCP.fill = GridBagConstraints.HORIZONTAL;
            gbcCP.insets = new Insets(7,10,15,15);
            gbcCP.anchor = GridBagConstraints.CENTER;
            contentPane.add(btAdd, gbcCP);

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

    public void fillBoxes() {
        List<Gender> genders = Patient.getGenderList();
        genders.forEach(gender -> cbGender.addItem(gender));
        List<Nationality> nationalities = Patient.getNationalityList();
        nationalities.forEach(nationality -> cbNationality.addItem(nationality));
        List<Insurance> insurances = Patient.getInsuranceList();
        insurances.forEach(insurance -> cbInsurance.addItem(insurance));
    }

    public void addNewPatient() {

        if (tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfSVN.getText().isEmpty() || tfBirthDate.getText().isEmpty() || tfStreet.getText().isEmpty() ||
                tfStreetNumber.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfCity.getText().isEmpty() || cbGender.getSelectedItem() == null ||
                cbNationality.getSelectedItem() == null || cbInsurance.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please enter all information");
        } else {

            try {

                long SVN = Long.parseLong(tfSVN.getText());
                Date birthDate = java.sql.Date.valueOf(tfBirthDate.getText());
                int streetNumber = Integer.parseInt(tfStreetNumber.getText());
                int postalCode = Integer.parseInt(tfPostalCode.getText());

                Patient.addPatient(tfFirstName.getText(), tfLastName.getText(), SVN, birthDate, tfStreet.getText(),
                        streetNumber, postalCode, tfCity.getText(), ((Gender) cbGender.getSelectedItem()).getGenderId(),
                        ((Nationality) cbNationality.getSelectedItem()).getNationalityId(),
                        ((Insurance) cbInsurance.getSelectedItem()).getInsuranceId());
                System.out.println("Patient successfully added!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number format in one of the fields!");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid birth date: use the format yyyy-mm-dd!");
            }
        }
    }


}

