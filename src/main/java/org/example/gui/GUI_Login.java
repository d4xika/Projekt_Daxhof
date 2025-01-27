package org.example.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI_Login extends JFrame {
    private JPanel contentPane;
    private JTextField tfUser;
    private JPasswordField pfPassword;
    private JButton btLogin;
    private JLabel lWelcome, lUser, lPassword;

    /**
     * constructor of the GUI_Login
     * calls methods for the GUI and contains ActionListeners for buttons
     */
    public GUI_Login() {
        setTitle("Login");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout();
        addColor();

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btLogin.setEnabled(false); //Button deaktiviert, verhindert Doppelklick

                SwingWorker<Boolean, Void> loginWorker = new SwingWorker<>() { //Login Prüfung in Thread
                    @Override
                    protected Boolean doInBackground() {
                        return checkData();
                    }

                    @Override
                    protected void done() {
                        try {
                            boolean success = get(); // Ergebnis von doInBackground()
                            if (success) {
                                dispose();
                                SwingUtilities.invokeLater(() -> {
                                    GUI_SelectOption gui = new GUI_SelectOption();
                                    gui.setVisible(true);
                                });
                            } else {
                                UIManager.put("OptionPane.background", SetLayout.cBackground);
                                UIManager.put("Panel.background", SetLayout.cBackground);
                                JOptionPane.showMessageDialog(
                                        contentPane,
                                        "Wrong login data",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    contentPane,
                                    "An error occurred: " + ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } finally {
                            btLogin.setEnabled(true); //Button wieder aktiviert
                        }
                    }
                };
                loginWorker.execute();
            }
        });
    }

    /**
     * checks if user and password written in to the fields is correct
     * @return boolean if data is correct
     */
    private boolean checkData() {
        boolean check = false;
        String user = "doctor";
        char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        String tfUserText = tfUser.getText();
        char[] pfPasswordText = pfPassword.getPassword();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (tfUserText.equals(user) && Arrays.equals(pfPasswordText, password)) {
            check = true;
        }
        return check;
    }

    /**
     * sets layout of the GUI
     */
    public void setLayout() {
        setSize(350, 200);
        setLocationRelativeTo(null);
        pfPassword.setEchoChar('*');
        SetLayout.setLoginLayout(contentPane, lWelcome, lUser, tfUser, lPassword, pfPassword, btLogin);
    }

    /**
     * sets color of the GUI
     */
    public void addColor() {
        SetLayout.setLoginColor(contentPane, tfUser, pfPassword, btLogin);
    }
}


