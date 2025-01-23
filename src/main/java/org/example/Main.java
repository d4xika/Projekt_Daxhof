package org.example;

import javax.swing.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);
        SwingUtilities.invokeLater(() -> {
            GUI_SelectOption gui = new GUI_SelectOption();
            gui.setVisible(true);
        });





    }


}