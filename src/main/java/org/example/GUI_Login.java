package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI_Login extends JFrame {

    private JPanel contentPane;
    private JTextField tfUser;
    private JTextField tfWelcome;
    private JLabel lUser;
    private JLabel lPassword;
    private JPasswordField pfPassword;
    private JButton btLogin;

    GUI_Login() {
        setTitle("Login");
        setContentPane(contentPane);
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pfPassword.setEchoChar('*');


        GridBagConstraints gbc = new GridBagConstraints(); /**um Layout bei GUI_Login individuell zu gestalten*/

        //Button Login
        gbc.gridx = 0; //Position im Raster - welche Spalte (x-Achse)
        gbc.gridy = 3; //welche Zeile (y-Achse)
        gbc.gridwidth = 2; //wie viele Spalten die Komponente einnimmt
        gbc.fill = GridBagConstraints.NONE; //wie oben zugewiesener Platz ausgefüllt wird - gar nicht
        gbc.insets = new Insets(5,5,5,5); //Abstand um Komponente herum
        gbc.anchor = GridBagConstraints.CENTER; //Position der Komponente innerhalb der Zelle
        contentPane.add(btLogin, gbc); //die GridBagConstraints von der Komponenten zum contentPane adden - ohne ändert er gar nichts!!

        //Textfiel Welcome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(15,10,5,10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(tfWelcome, gbc);

        //Label User
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,35,5,20);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lUser, gbc);

        //Textfield User
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,70,5,20);
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(tfUser, gbc);

        //Label Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,10,5,20);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lPassword, gbc);

        //Passwordfield Password
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,70,5,20);
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(pfPassword, gbc);


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

}

