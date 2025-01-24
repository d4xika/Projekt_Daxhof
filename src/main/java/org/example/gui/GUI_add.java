package org.example.gui;

import org.example.model.Gender;
import org.example.model.Insurance;
import org.example.model.Nationality;
import org.example.model.Patient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
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

    GUI_add() {
        setTitle("Add Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setLayout();
        addColor();

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

        return Patient.savePatient(0, tfFirstName.getText(), tfLastName.getText(), tfSVN.getText(), tfBirthDate.getText(), tfStreet.getText(),
                tfStreetNumber.getText(), tfPostalCode.getText(), tfCity.getText(), cbGender, cbNationality, cbInsurance);
    }

    public void setLayout (){

        setSize(370,460);
        setLocationRelativeTo(null);

        setAddEditLayout(panel1, lFirstName, lLastName, lSVN, lBirthDate, lStreet, lStreetNumber, lPostalCode, lCity, lGender, lNationality, lInsurance, panel2, tfFirstName, tfLastName, tfSVN, tfBirthDate, tfStreet, tfStreetNumber, tfPostalCode, tfCity, cbGender, cbNationality, cbInsurance, contentPane, btReturn, btAdd);
    }

    public static void setAddEditLayout(JPanel panel1, JLabel lFirstName, JLabel lLastName, JLabel lSVN, JLabel lBirthDate, JLabel lStreet, JLabel lStreetNumber, JLabel lPostalCode, JLabel lCity, JLabel lGender, JLabel lNationality, JLabel lInsurance, JPanel panel2, JTextField tfFirstName, JTextField tfLastName, JTextField tfSVN, JTextField tfBirthDate, JTextField tfStreet, JTextField tfStreetNumber, JTextField tfPostalCode, JTextField tfCity, JComboBox<Gender> cbGender, JComboBox<Nationality> cbNationality, JComboBox<Insurance> cbInsurance, JPanel contentPane, JButton btReturn, JButton btAdd) {
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
        contentPane.add(btAdd, gbc);
    }

    public void addColor (){

        setAddEditColor(contentPane, panel1, panel2, btAdd, btReturn);
        Color cBorder = null;
        btAdd.setBorder(BorderFactory.createLineBorder(cBorder));
        btReturn.setBorder(BorderFactory.createLineBorder(cBorder));

    }

    public static void setAddEditColor(JPanel contentPane, JPanel panel1, JPanel panel2, JButton btAdd, JButton btReturn) {
        Color cBackground = new Color(202,255,112);
        contentPane.setBackground(cBackground);
        panel1.setBackground(cBackground);
        panel2.setBackground(cBackground);

        Color cButton = new Color (162,205,90);
        btAdd.setBackground(cButton);
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

