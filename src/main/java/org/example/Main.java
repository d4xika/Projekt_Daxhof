package org.example;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            GUI_Login gui = new GUI_Login();
            gui.setVisible(true);
        });


    }


}