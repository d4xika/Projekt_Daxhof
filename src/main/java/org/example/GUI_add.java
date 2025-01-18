package org.example;

import javax.swing.*;
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

    GUI_add() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Add Patient");
        setLocationRelativeTo(null);
        pack();

        fillAddBoxes();

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
                boolean added = addNewPatient();
                if (added) {
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        GUI_SelectOption gui = new GUI_SelectOption();
                        gui.setVisible(true);
                    });
                }
            }
        });
    }

    public void fillAddBoxes() {
        fillBoxes(cbGender, cbNationality, cbInsurance);
    }

    public static void fillBoxes(JComboBox<Gender> cbGender, JComboBox<Nationality> cbNationality, JComboBox<Insurance> cbInsurance) {
        List<Gender> genders = Patient.getGenderList();
        genders.forEach(cbGender::addItem);
        List<Nationality> nationalities = Patient.getNationalityList();
        nationalities.forEach(cbNationality::addItem);
        List<Insurance> insurances = Patient.getInsuranceList();
        insurances.forEach(cbInsurance::addItem);
    }

    public boolean addNewPatient() {

        return savePatient(0, tfFirstName.getText(), tfLastName.getText(), tfSVN.getText(), tfBirthDate.getText(), tfStreet.getText(),
                tfStreetNumber.getText(), tfPostalCode.getText(), tfCity.getText(), cbGender, cbNationality, cbInsurance);
    }

    public static boolean savePatient (int idPatients, String firstNamePatients, String lastNamePatients, String svnPatients, String birthDatePatients,
                                    String streetPatients, String streetNumberPatients, String postalCodePatients, String cityPatients,
                                    JComboBox<Gender> cbGender, JComboBox<Nationality> cbNationality, JComboBox<Insurance> cbInsurance) {

        boolean success = false;

        if (firstNamePatients.isEmpty() || lastNamePatients.isEmpty() || svnPatients.isEmpty() || birthDatePatients==null || streetPatients.isEmpty() ||
                streetNumberPatients.isEmpty() || postalCodePatients.isEmpty() || cityPatients.isEmpty() || cbGender.getSelectedItem() == null ||
                cbNationality.getSelectedItem() == null || cbInsurance.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please enter all information");
        } else {

            try {

                long SVN = Long.parseLong(svnPatients);
                Date birthDate = java.sql.Date.valueOf(birthDatePatients);
                int streetNumber = Integer.parseInt(streetNumberPatients);
                int postalCode = Integer.parseInt(postalCodePatients);

                if (idPatients == 0) {
                    Patient.addPatient(firstNamePatients, lastNamePatients, SVN, birthDate, streetPatients,
                            streetNumber, postalCode, cityPatients, ((Gender) cbGender.getSelectedItem()).getGenderId(),
                            ((Nationality) cbNationality.getSelectedItem()).getNationalityId(),
                            ((Insurance) cbInsurance.getSelectedItem()).getInsuranceId());
                    System.out.println("Patient successfully added!");
                    success = true;
                }else {
                    Patient.editPatient(idPatients, firstNamePatients, lastNamePatients, SVN, birthDate, streetPatients,
                            streetNumber, postalCode, cityPatients, ((Gender) cbGender.getSelectedItem()).getGenderId(),
                            ((Nationality) cbNationality.getSelectedItem()).getNationalityId(),
                            ((Insurance) cbInsurance.getSelectedItem()).getInsuranceId());
                    System.out.println("Patient successfully updated!");
                    success = true;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number format in one of the fields!");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid birth date: use the format yyyy-mm-dd!");
            }
        }
        return success;

    }


}

