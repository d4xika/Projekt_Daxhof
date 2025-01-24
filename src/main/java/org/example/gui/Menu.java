package org.example.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Menu {

    public static void exportToCSV(JTable tPatients) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export as CSV");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter fw = new FileWriter(fileToSave)) {

                DefaultTableModel model = (DefaultTableModel) tPatients.getModel();

                for (int i = 0; i < model.getColumnCount(); i++) {
                    fw.write(model.getColumnName(i) + ",");
                }
                fw.write("\n");

                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        fw.write(model.getValueAt(i, j).toString() + ",");
                    }
                    fw.write("\n");
                }

                JOptionPane.showMessageDialog(null, "Export successful!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error during export: " + e.getMessage());
            }
        }
    }

    public static void exportPatientToCSV(JTable tPatients) {

        int selectedRow = tPatients.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a patient");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) {

                for (int i = 0; i < tPatients.getColumnCount(); i++) {
                    bw.write(tPatients.getColumnName(i));
                    if (i < tPatients.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();

                for (int i = 0; i < tPatients.getColumnCount(); i++) {
                    Object cellValue = tPatients.getValueAt(selectedRow, i);
                    bw.write(cellValue != null ? cellValue.toString() : "");
                    if (i < tPatients.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();

                JOptionPane.showMessageDialog(null, "Export successful!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error during export: " + e.getMessage());
            }
        }
    }

    public static void printTable(JTable tPatients) {
        try {
            if (!tPatients.print()) {
                JOptionPane.showMessageDialog(null, "Printing cancelled");
            }
        }catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Error during print: " + e.getMessage());
        }
    }
}
