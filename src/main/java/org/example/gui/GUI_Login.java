package org.example.gui;

import javax.swing.*;
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

    public GUI_Login() {
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
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(contentPane, "wrong login data", "Error", JOptionPane.ERROR_MESSAGE);
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
        SetLayout.setLoginLayout(contentPane, lWelcome, lUser, tfUser, lPassword, pfPassword, btLogin);
    }

    public void addColor(){

        SetLayout.setLoginColor(contentPane, tfUser, pfPassword, btLogin);
    }

}

