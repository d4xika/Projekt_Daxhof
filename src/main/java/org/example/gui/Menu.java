package org.example.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.ExecutionException;

public class Menu {

    /**
     * exports a table of patient to a CSV file
     * after a selected location writes a file with the patients and saves it
     * @param tPatients table of patients
     */
    public static void exportToCSV(JTable tPatients) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setBackground(SetLayout.cBackground);
        fileChooser.setDialogTitle("Export as CSV");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            //SwingWorker für CSV-Export
            SwingWorker<Void, Void> exportWorker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try (FileWriter fw = new FileWriter(fileToSave)) {
                        DefaultTableModel model = (DefaultTableModel) tPatients.getModel();

                        //Spaltennamen
                        for (int i = 0; i < model.getColumnCount(); i++) {
                            fw.write(model.getColumnName(i) + ",");
                        }
                        fw.write("\n");

                        //Zeilen
                        for (int i = 0; i < model.getRowCount(); i++) {
                            for (int j = 0; j < model.getColumnCount(); j++) {
                                fw.write(model.getValueAt(i, j).toString() + ",");
                            }
                            fw.write("\n");
                        }
                    }
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        get(); //Ergebnis prüfen
                        UIManager.put("OptionPane.background", SetLayout.cBackground);
                        UIManager.put("Panel.background", SetLayout.cBackground);
                        JOptionPane.showMessageDialog(null, "Export successful!");
                    } catch (Exception e) {
                        UIManager.put("OptionPane.background", SetLayout.cBackground);
                        UIManager.put("Panel.background", SetLayout.cBackground);
                        JOptionPane.showMessageDialog(null, "Error during export!");
                    }
                }
            };
            exportWorker.execute();// Startet SwingWorker
        }
    }

    /**
     * exports a single patient to a CSV file
     * after a selected location writes a file with the patient and saves it
     * @param tPatients table of patients
     */
    public static void exportPatientToCSV(JTable tPatients) {
        int selectedRow = tPatients.getSelectedRow();
        if (selectedRow == -1) {
            UIManager.put("OptionPane.background", SetLayout.cBackground);
            UIManager.put("Panel.background", SetLayout.cBackground);
            JOptionPane.showMessageDialog(null, "Please select a patient!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setBackground(SetLayout.cBackground);
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // SwingWorker für Patienten-Export
            SwingWorker<Void, Void> exportWorker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) {
                        //Spaltennamen
                        for (int i = 0; i < tPatients.getColumnCount(); i++) {
                            bw.write(tPatients.getColumnName(i));
                            if (i < tPatients.getColumnCount() - 1) {
                                bw.write(",");
                            }
                        }
                        bw.newLine();

                        //gewählte Zeile
                        for (int i = 0; i < tPatients.getColumnCount(); i++) {
                            Object cellValue = tPatients.getValueAt(selectedRow, i);
                            bw.write(cellValue != null ? cellValue.toString() : "");
                            if (i < tPatients.getColumnCount() - 1) {
                                bw.write(",");
                            }
                        }
                        bw.newLine();
                    }
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        get(); // Ergebnis prüfen
                        UIManager.put("OptionPane.background", SetLayout.cBackground);
                        UIManager.put("Panel.background", SetLayout.cBackground);
                        JOptionPane.showMessageDialog(null, "Export successful!");
                    } catch (Exception e) {
                        UIManager.put("OptionPane.background", SetLayout.cBackground);
                        UIManager.put("Panel.background", SetLayout.cBackground);
                        JOptionPane.showMessageDialog(null, "Error during export!");
                    }
                }
            };
            exportWorker.execute(); //Startet SwingWorker
        }
    }

    /**
     * prints the table of patients
     * uses the integrated print method
     * @param tPatients table of patients
     */
    public static void printTable(JTable tPatients) {
        //SwingWorker für Drucken von Tabelle
        SwingWorker<Void, Void> printWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    if (!tPatients.print()) {
                        throw new PrinterException("Printing cancelled");
                    }
                } catch (PrinterException e) {
                    throw e;
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); //Ergebnis prüfen
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Printing completed successfully!");
                } catch (InterruptedException | ExecutionException e) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Error during print!");
                }
            }
        };
        printWorker.execute(); //startet SwingWorker
    }
}

