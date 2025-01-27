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

    /**
     * constructor of GUI_edit
     * calls methods and contains ActionListeners for buttons
     * @param id patient id of patient to be edited
     */
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
                                UIManager.put("OptionPane.background", SetLayout.cBackground);
                                UIManager.put("Panel.background", SetLayout.cBackground);
                                JOptionPane.showMessageDialog(
                                        GUI_edit.this,
                                        "There was a problem editing the patient",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            });
                        }
                }).start();
            }
        });
    }

    /**
     * calls method to fill the Comboboxes
     */
    public void fillEditBoxes() {
        SwingUtilities.invokeLater(() -> GUI_add.fillBoxes(cbGender, cbNationality, cbInsurance));
    }

    /**
     * fills the TextFields of the GUI with the information of the to be edited patient
     * @param p patient to be edited
     */
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

    /**
     * edits patient
     * @param id id of patient to be edited
     * @return boolean if editing was successfully
     */
    public boolean editPatient(int id) {

        try {
            boolean success = Patient.savePatient(
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
                    cbInsurance);
            if (!success){
                return false;
            } return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * sets layout of the GUI
     */
    public void setLayout() {
        setSize(370, 460);
        setLocationRelativeTo(null);
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

    /**
     * sets color of the GUI
     */
    public void addColor() {
        SetLayout.setAddEditColor(contentPane, panel1, panel2, btEdit, btReturn);
    }

}


