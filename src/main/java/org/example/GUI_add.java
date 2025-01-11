package org.example;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
    private JFormattedTextField ftfSVN;
    private JFormattedTextField ftfBirthDate;
    private JFormattedTextField ftfStreetNumber;
    private JFormattedTextField ftfPostalCode;

    GUI_add() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Add Patient");
        pack();

        setFormattedTextFields();
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

    public void setFormattedTextFields () {

        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(nf);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(0);

        DefaultFormatterFactory numberFormatterFactory = new DefaultFormatterFactory(numberFormatter);
        ftfStreetNumber.setFormatterFactory(numberFormatterFactory);
        ftfPostalCode.setFormatterFactory(numberFormatterFactory);
        ftfSVN.setFormatterFactory(numberFormatterFactory);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        DefaultFormatterFactory dateFormatterFactory = new DefaultFormatterFactory(dateFormatter);
        ftfBirthDate.setFormatterFactory(dateFormatterFactory);

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

        if (tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || ftfSVN.getText().isEmpty() || ftfBirthDate.getText().isEmpty() || tfStreet.getText().isEmpty() ||
                ftfStreetNumber.getText().isEmpty() || ftfPostalCode.getText().isEmpty() || tfCity.getText().isEmpty() || cbGender.getSelectedItem() == null ||
                cbNationality.getSelectedItem() == null || cbInsurance.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please enter all information");
        } else {

            try {

                long SVN = Long.parseLong(ftfSVN.getText());
                Date birthDate = java.sql.Date.valueOf(ftfBirthDate.getText());
                int streetNumber = Integer.parseInt(ftfStreetNumber.getText());
                int postalCode = Integer.parseInt(ftfPostalCode.getText());

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

