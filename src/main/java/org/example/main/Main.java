package org.example.main;

import org.example.gui.GUI_Login;

import javax.swing.*;
import java.util.Locale;

public class Main {

    /**
     * starts the program with the login window
     * @param args
     */
    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);
        SwingUtilities.invokeLater(() -> {
            GUI_Login gui = new GUI_Login();
            gui.setVisible(true);
        });

    }

}