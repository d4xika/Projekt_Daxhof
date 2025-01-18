package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

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


    GUI_edit(int id) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Edit Patient");
        setLocationRelativeTo(null);
        pack();

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

        return GUI_add.savePatient(id, tfFirstName.getText(), tfLastName.getText(), tfSVN.getText(), tfBirthDate.getText(), tfStreet.getText(),
                tfStreetNumber.getText(), tfPostalCode.getText(), tfCity.getText(), cbGender, cbNationality, cbInsurance);
    }


}

