package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI_Login extends JFrame {

    private JPanel contentPane;
    private JTextField tfUser;
    private JLabel lUser;
    private JLabel lPassword;
    private JPasswordField pfPassword;
    private JButton btLogin;
    private JLabel lWelcome;

    GUI_Login() {
        setTitle("Login");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout();
        addColor();

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkData()) {
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        GUI_SelectOption gui = new GUI_SelectOption();
                        gui.setVisible(true);
                    });
                }else {
                    JOptionPane.showMessageDialog(contentPane, "falsche Zugangsdaten", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean checkData () {
        boolean check = false;
        String user = "doctor";
        char [] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        String tfUserText = tfUser.getText();
        char [] pfPasswordText = pfPassword.getPassword();

        if (tfUserText.equals(user) && Arrays.equals(pfPasswordText, password)) {
            check = true;
        }
        return check;
    }

    public void setLayout (){

        setSize(350,200);
        setLocationRelativeTo(null);
        pfPassword.setEchoChar('*');
        Color cBackground = new Color(188,238,104);
        contentPane.setBackground(cBackground);

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
    public void addColor(){
        Color cBackground = new Color(202,255,112);
        contentPane.setBackground(cBackground);

        Color cButton = new Color (162,205,90);
        btLogin.setBackground(cButton);

        Color cBorder = new Color (110,139,61);
        btLogin.setBorder(new CompoundBorder(
                new LineBorder(cBorder),
                new EmptyBorder(1,10,1,10)));
        tfUser.setBorder(BorderFactory.createLineBorder(cBorder));
        pfPassword.setBorder(BorderFactory.createLineBorder(cBorder));


    }

}

