package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI_Login extends JFrame {

    private JPanel contentPane;
    private JTextField tfUser;
    private JButton btLogin;
    private JTextField tfWelcome;
    private JLabel lUser;
    private JLabel lPassword;
    private JPasswordField pfPassword;

    GUI_Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Login");
        pack();
        pfPassword.setEchoChar('*');

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

