package org.example;

import javax.swing.*;

public class GUI_SelectOption extends JFrame {
    private JPanel contentPane;
    private JButton btAdd;
    private JButton btDelete;
    private JButton btEdit;

    GUI_SelectOption() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Select option");
        pack();
    }
}
