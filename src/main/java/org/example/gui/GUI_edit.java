package org.example.gui;

import org.example.model.Gender;
import org.example.model.Insurance;
import org.example.model.Nationality;
import org.example.model.Patient;

import javax.swing.*;
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

        // Lade Komboboxen und Textfelder in einem separaten Thread
        new Thread(this::fillEditBoxes).start();
        new Thread(() -> fillTextFields(Patient.getPatient(id))).start();

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
                new Thread(() -> {
                    boolean edited = editPatient(id);
                    if (edited) {
                        SwingUtilities.invokeLater(() -> {
                            dispose();
                            GUI_SelectOption gui = new GUI_SelectOption();
                            gui.setVisible(true);
                        });
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(
                                    GUI_edit.this,
                                    "Fehler beim Bearbeiten des Patienten.",
                                    "Fehler",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        });
                    }
                }).start();
            }
        });
    }

    public void fillEditBoxes() {
        SwingUtilities.invokeLater(() -> GUI_add.fillBoxes(cbGender, cbNationality, cbInsurance));
    }

    public void fillTextFields(Patient p) {
        SwingUtilities.invokeLater(() -> {
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
        });
    }

    public boolean editPatient(int id) {

        return Patient.savePatient(
                id,
                tfFirstName.getText(),
                tfLastName.getText(),
                tfSVN.getText(),
                tfBirthDate.getText(),
                tfStreet.getText(),
                tfStreetNumber.getText(),
                tfPostalCode.getText(),
                tfCity.getText(),
                cbGender,
                cbNationality,
                cbInsurance
        );
    }

    public void setLayout() {
        setSize(370, 460);
        setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        SetLayout.setAddEditLayout(
                panel1,
                lFirstName, lLastName, lSVN, lBirthDate, lStreet, lStreetNumber, lPostalCode, lCity,
                lGender, lNationality, lInsurance,
                panel2,
                tfFirstName, tfLastName, tfSVN, tfBirthDate, tfStreet, tfStreetNumber, tfPostalCode, tfCity,
                cbGender, cbNationality, cbInsurance,
                contentPane,
                btReturn, btEdit
        );
    }

    public void addColor() {
        SetLayout.setAddEditColor(contentPane, panel1, panel2, btEdit, btReturn);
    }

}


