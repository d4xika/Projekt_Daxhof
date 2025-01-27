package org.example.gui;

import org.example.model.Gender;
import org.example.model.Insurance;
import org.example.model.Nationality;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class SetLayout {

    public static final Color cBackground = new Color(202,255,112);
    static final Color cButton = new Color (162,205,90);
    static final Color cBorder = new Color (110,139,61);

    public static void setLoginLayout (JPanel contentPane, JLabel lWelcome, JLabel lUser, JTextField tfUser,
                                JLabel lPassword, JPasswordField pfPassword, JButton btLogin) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(btLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(15,10,5,10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(lWelcome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,35,5,20);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lUser, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,70,5,20);
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(tfUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,10,5,20);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,70,5,20);
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(pfPassword, gbc);
    }
    public static void setLoginColor (JPanel contentPane, JTextField tfUser, JPasswordField pfPassword, JButton btLogin){

        contentPane.setBackground(cBackground);
        btLogin.setBackground(cButton);
        btLogin.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(1,10,1,10)));
        tfUser.setBorder(BorderFactory.createLineBorder(cBorder));
        pfPassword.setBorder(BorderFactory.createLineBorder(cBorder));
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
    public static void setAddEditColor(JPanel contentPane, JPanel panel1, JPanel panel2, JButton btAdd, JButton btReturn) {

        contentPane.setBackground(cBackground);
        panel1.setBackground(cBackground);
        panel2.setBackground(cBackground);

        btAdd.setBackground(cButton);
        btReturn.setBackground(cButton);
        btAdd.setBorder(BorderFactory.createLineBorder(cBorder));
        btReturn.setBorder(BorderFactory.createLineBorder(cBorder));

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
    public static void setSOLayout (JPanel contentPane, JTextField tfPatientName, JScrollPane spPatientsFound, JLabel lEnter,
                                    JPanel pButton, JButton btSearch, JButton btAdd, JButton btEdit, JButton btDelete) {

        GridBagConstraints gbc = new GridBagConstraints();
        contentPane.setBackground(cBackground);
        pButton.setBackground(cBackground);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 15, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lEnter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 15, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(tfPatientName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.ipadx = 10;
        gbc.insets = new Insets(5, 0, 5, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(btSearch, gbc);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weighty = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 5, 15, 5);
        pButton.add(btAdd, gbc);
        pButton.add(btEdit, gbc);
        pButton.add(btDelete, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(15, 7, 15, 15);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        contentPane.add(pButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 15, 15, 8);
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(spPatientsFound, gbc);
    }
    public static void setSOColor (JPanel contentPane, JScrollPane spPatientsFound, JPanel pButton,
                                   JButton btSearch, JButton btAdd, JButton btEdit, JButton btDelete){

        contentPane.setBackground(cBackground);
        pButton.setBackground(cBackground);

        spPatientsFound.getViewport().setBackground(cButton);
        btSearch.setBackground(cButton);
        btAdd.setBackground(cButton);
        btEdit.setBackground(cButton);
        btDelete.setBackground(cButton);

        btSearch.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,3,2,3)));
        btAdd.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,3,2,3)));
        btEdit.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,3,2,3)));
        btDelete.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(2,5,2,5)));
    }
    public static void customizeTable(JTable tPatients) {

        TableColumnModel columnModel = tPatients.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);//ID
        columnModel.getColumn(1).setPreferredWidth(160);//firtName
        columnModel.getColumn(2).setPreferredWidth(175);//lastName
        columnModel.getColumn(3).setPreferredWidth(150);//SVN
        columnModel.getColumn(4).setPreferredWidth(150);//Birthdate
        columnModel.getColumn(5).setPreferredWidth(200);//Street Name
        columnModel.getColumn(6).setPreferredWidth(80);//Street Number
        columnModel.getColumn(7).setPreferredWidth(110);//Postalcode
        columnModel.getColumn(8).setPreferredWidth(175);//City
        columnModel.getColumn(9).setPreferredWidth(100);//Gender
        columnModel.getColumn(10).setPreferredWidth(150);//Natiionality
        columnModel.getColumn(11).setPreferredWidth(125);//Insurance


        tPatients.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                JTableHeader header = tPatients.getTableHeader();
                tPatients.getTableHeader().setPreferredSize(new Dimension(0, 30));
                header.setBackground(cButton);
                header.setFont(new Font("Arial", Font.BOLD, 12));
                return c;
            }
        });

    }


}
