package org.example.gui;

import org.example.model.Gender;
import org.example.model.Insurance;
import org.example.model.Nationality;
import org.example.model.Patient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /**
     * constructor of GUI_add
     * calls methods for the GUI and contains ActionListeners for buttons
     */
    public GUI_add() {
        setTitle("Add Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);

        setLayout();
        addColor();

        new Thread(() -> fillAddBoxes()).start(); // Daten in Thread laden für ComboBox

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
                new Thread(() -> {
                    boolean added = addNewPatient(); // Überprüfe, ob Patienten erfolgreich hinzugefügt werden

                    if (added) {
                        SwingUtilities.invokeLater(() -> {
                            dispose();
                            GUI_SelectOption gui = new GUI_SelectOption();
                            gui.setVisible(true);
                        });
                    } else {
                        // Protokollierung für detailliertere Fehleranalyse
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(
                                    GUI_add.this,
                                    "Fehler beim Hinzufügen des Patienten. Überprüfen Sie die Eingabedaten und versuchen Sie es erneut.",
                                    "Fehler",
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
    public void fillAddBoxes() {
        SwingUtilities.invokeLater(() -> fillBoxes(cbGender, cbNationality, cbInsurance));
    }

    /**
     * fills comboboxes with the corresponding lists from SQL
     * @param cbGender combobox with genders
     * @param cbNationality combobox with nationalities
     * @param cbInsurance combobox with insurances
     */
    public static void fillBoxes(JComboBox<Gender> cbGender, JComboBox<Nationality> cbNationality, JComboBox<Insurance> cbInsurance) {

        List<Gender> genders = Patient.getGenderList();
        genders.forEach(cbGender::addItem);

        List<Nationality> nationalities = Patient.getNationalityList();
        nationalities.forEach(cbNationality::addItem);

        List<Insurance> insurances = Patient.getInsuranceList();
        insurances.forEach(cbInsurance::addItem);
    }

    /**
     * adds a new patient to the database
     * @return boolean if adding was successfully
     */
    public boolean addNewPatient() {
        try {Patient.savePatient(
                0,
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

            return true; // Rückgabe true, wenn der Patient erfolgreich hinzugefügt wurde
        } catch (Exception ex) {
            ex.printStackTrace(); // Fehler wird in der Konsole ausgegeben
            return false; // Rückgabe false im Fehlerfall
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
                btReturn, btAdd
        );
    }

    /**
     * sets color of the GUI
     */
    public void addColor() {
        SetLayout.setAddEditColor(contentPane, panel1, panel2, btAdd, btReturn);
    }
}


