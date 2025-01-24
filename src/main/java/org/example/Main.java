package org.example;

import javax.swing.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);
        SwingUtilities.invokeLater(() -> {
            GUI_Login gui = new GUI_Login();
            gui.setVisible(true);
        });

    }

}