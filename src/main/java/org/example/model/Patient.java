package org.example.model;

import org.example.gui.SetLayout;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Patient {

    private int idPatients;
    private String firstNamePatients;
    private String lastNamePatients;
    private long svnPatients;
    private Date birthDatePatients;
    private String streetPatients;
    private int streetNumberPatients;
    private int postalCodePatients;
    private String cityPatients;
    private int idGender;
    private int idNationality;
    private int idInsurance;

    private static final String url = "jdbc:mysql://10.25.2.145:3306/23daxberger";
    private static final String user = "23daxberger";
    private static final String password = "geb23";

    /**
     * constructor of Patient
     * @param idPatients patient ID
     * @param firstNamePatients first name of patient
     * @param lastNamePatients last name of patient
     * @param svnPatients SVN of patient
     * @param birthDatePatients birthdate of patient
     * @param streetPatients street of patient
     * @param streetNumberPatients street number of patient
     * @param postalCodePatients postal code of patient
     * @param cityPatients city of patient
     * @param idGender gender of patient
     * @param idNationality nationality of patient
     * @param idInsurance insurance of patient
     */
    public Patient(int idPatients, String firstNamePatients, String lastNamePatients, long svnPatients,
                   Date birthDatePatients, String streetPatients, int streetNumberPatients,
                   int postalCodePatients, String cityPatients, int idGender, int idNationality,
                   int idInsurance) {
        this.idPatients = idPatients;
        this.firstNamePatients = firstNamePatients;
        this.lastNamePatients = lastNamePatients;
        this.svnPatients = svnPatients;
        this.birthDatePatients = birthDatePatients;
        this.streetPatients = streetPatients;
        this.streetNumberPatients = streetNumberPatients;
        this.postalCodePatients = postalCodePatients;
        this.cityPatients = cityPatients;
        this.idGender = idGender;
        this.idNationality = idNationality;
        this.idInsurance = idInsurance;

    }

    /**
     * gets the list of genders from the database
     * @return list of genders
     */
    public static List<Gender> getGenderList() {

        final List<Gender> genders = new ArrayList<>();

        //Thread für Datenbankabfrage
        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT idGender, genderPatients FROM gender");
                    ResultSet resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("idGender");
                        String gender = resultSet.getString("genderPatients");

                        genders.add(new Gender(id, gender));  // Füge die Daten zur Liste hinzu
                    }
                    resultSet.close();
                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start();  // Starte den Thread
        try {
            workerThread.join();  // Warte darauf, dass der Thread fertig ist
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return genders;  // Gib die Liste zurück, nachdem der Thread fertig ist
    }

    /**
     * gets a gender object
     * @param id gender ID
     * @return gender object
     */
    public static Gender getGender(int id) {
        // Definiere die Variable für das Gender-Objekt
        final Gender[] g = new Gender[1];

        // Erstelle den Worker-Thread für die Datenbankabfrage
        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT idGender, genderPatients FROM gender WHERE idGender = ?");
                    ps.setInt(1, id);

                    ResultSet resultSet = ps.executeQuery();
                    if (resultSet.next()) {
                        g[0] = new Gender(resultSet.getInt("idGender"), resultSet.getString("genderPatients"));
                    }

                    ps.close();
                    resultSet.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start();  // Starte den Thread
        try {
            workerThread.join();  // Warte darauf, dass der Thread fertig ist
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return g[0];  // Gib das Gender-Objekt zurück, nachdem der Thread fertig ist
    }

    /**
     * gets the list of nationalities from the database
     * @return nationality list
     */
    public static List<Nationality> getNationalityList() {
        final List<Nationality> nationalities = new ArrayList<>();

        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT idNationality, nationalityPatients FROM nationality");
                    ResultSet resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("idNationality");
                        String nationality = resultSet.getString("nationalityPatients");

                        nationalities.add(new Nationality(id, nationality)); // Daten hinzufügen
                    }

                    resultSet.close();
                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
        try {
            workerThread.join(); // Auf Abschluss des Threads warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return nationalities; // Liste zurückgeben, nachdem der Thread abgeschlossen wurde

    }

    /**
     * gets a nationality object
     * @param id nationality ID
     * @return nationality object
     */
    public static Nationality getNationality(int id) {
        final Nationality[] n = new Nationality[1]; // Array zum Speichern des Ergebnisses

        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT idNationality, nationalityPatients FROM nationality WHERE idNationality = ?");
                    ps.setInt(1, id);

                    ResultSet resultSet = ps.executeQuery();
                    if (resultSet.next()) {
                        n[0] = new Nationality(resultSet.getInt("idNationality"), resultSet.getString("nationalityPatients"));
                    }

                    resultSet.close();
                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
        try {
            workerThread.join(); // Auf Abschluss des Threads warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return n[0]; // Das gefundene Nationality-Objekt zurückgeben
    }

    /**
     * gets the list of insurances from the database
     * @return insurance list
     */
    public static List<Insurance> getInsuranceList() {
        final List<Insurance> insurances = new ArrayList<>();

        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT idInsurance, insurancePatients FROM insurance");
                    ResultSet resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("idInsurance");
                        String insurance = resultSet.getString("insurancePatients");

                        insurances.add(new Insurance(id, insurance)); // Daten hinzufügen
                    }

                    resultSet.close();
                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
        try {
            workerThread.join(); // Auf Abschluss des Threads warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return insurances; // Liste zurückgeben, nachdem der Thread abgeschlossen wurde
    }

    /**
     * gets an insurance object
     * @param id insurance ID
     * @return insurance object
     */
    public static Insurance getInsurance(int id) {
        final Insurance[] i = new Insurance[1]; // Array zum Speichern des Ergebnisses

        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT idInsurance, insurancePatients FROM insurance WHERE idInsurance = ?");
                    ps.setInt(1, id);

                    ResultSet resultSet = ps.executeQuery();
                    if (resultSet.next()) {
                        i[0] = new Insurance(resultSet.getInt("idInsurance"), resultSet.getString("insurancePatients"));
                    }

                    resultSet.close();
                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
        try {
            workerThread.join(); // Auf Abschluss des Threads warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return i[0]; // Das gefundene Insurance-Objekt zurückgeben
    }

    /**
     * gets all saved patients from the database
     * @return list of all patients
     */
    public static List<Patient> getAllPatients() {
        final List<Patient>[] patients = new List[]{List.of()}; // Array für die Liste von Patienten

        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT * FROM patients");
                    patients[0] = returnPatients(ps); // Patientenliste zuweisen

                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
        try {
            workerThread.join(); // Auf Abschluss des Threads warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return patients[0]; // Rückgabe der Patientenliste
    }

    /**
     * gets a certain patient from the database
     * @param id patient id of certain patient
     * @return certain patient
     */
    public static Patient getPatient(int id) {
        final Patient[] p = new Patient[1]; // Array für das Patient-Objekt

        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT * FROM patients WHERE idPatients = ?");
                    ps.setInt(1, id);

                    ResultSet resultSet = ps.executeQuery();
                    resultSet.next();

                    p[0] = new Patient(resultSet.getInt("idPatients"),
                            resultSet.getString("firstNamePatients"),
                            resultSet.getString("lastNamePatients"),
                            resultSet.getLong("svnPatients"),
                            resultSet.getDate("birthDatePatients"),
                            resultSet.getString("streetPatients"),
                            resultSet.getInt("streetNumberPatients"),
                            resultSet.getInt("postalCodePatients"),
                            resultSet.getString("cityPatients"),
                            resultSet.getInt("idGender"),
                            resultSet.getInt("idNationality"),
                            resultSet.getInt("idInsurance"));

                    ps.close();
                    resultSet.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
        try {
            workerThread.join(); // Auf Abschluss des Threads warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return p[0]; // Rückgabe des Patientenobjekts
    }

    /**
     * searches in the database for a patient containing the parameter
     * @param namePatient name of the patient that is searched
     * @return list of found patients
     */
    public static List<Patient> searchPatients(String namePatient) {
        final List<Patient>[] patients = new List[]{new ArrayList<>()}; // Array für die Patientenliste

        // Worker-Thread für die Suche
        Thread workerThread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("SELECT * FROM patients WHERE firstNamePatients LIKE ? " +
                            "OR lastNamePatients LIKE ? " +
                            "OR concat(firstNamePatients, ' ', lastNamePatients) LIKE ?");
                    String modifiedName = "%" + namePatient + "%"; // Kopie der Eingabe, um sie zu ändern
                    ps.setString(1, modifiedName);
                    ps.setString(2, modifiedName);
                    ps.setString(3, modifiedName);

                    // Patientenliste im Array speichern
                    synchronized (patients) { // Synchronisierung des Zugriffs auf patients[0]
                        patients[0] = returnPatients(ps); // Patientenliste zuweisen
                    }

                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten

        try {
            workerThread.join(); // Warten auf Abschluss des Threads
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return patients[0]; // Rückgabe der gefundenen Patientenliste
    }

    /**
     * executes PreparedStatement for patient lists
     * @param ps PreparedStatement wanted to use
     * @return list of patients
     * @throws SQLException if error with database
     */
    private static List<Patient> returnPatients(PreparedStatement ps) throws SQLException {
        final List<Patient>[] patients = new List[]{new ArrayList<>()}; // Array für die Liste von Patienten

        // Thread zum Ausführen der Datenbankabfrage
        Thread workerThread = new Thread(() -> {
            ResultSet resultSet = null;
            try {
                resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("idPatients");
                    String firstName = resultSet.getString("firstNamePatients");
                    String lastName = resultSet.getString("lastNamePatients");
                    long svn = resultSet.getLong("svnPatients");
                    Date birthDate = resultSet.getDate("birthDatePatients");
                    String street = resultSet.getString("streetPatients");
                    int streetNumber = resultSet.getInt("streetNumberPatients");
                    int postalCode = resultSet.getInt("postalCodePatients");
                    String city = resultSet.getString("cityPatients");
                    int idGender = resultSet.getInt("idGender");
                    int idNationality = resultSet.getInt("idNationality");
                    int idInsurance = resultSet.getInt("idInsurance");

                    patients[0].add(new Patient(id, firstName, lastName, svn, birthDate, street, streetNumber, postalCode,
                            city, idGender, idNationality, idInsurance));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        workerThread.start(); // Thread starten
        try {
            workerThread.join(); // Auf Abschluss des Threads warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return patients[0]; // Rückgabe der Patientenliste
    }

    /**
     * adds patient to the database
     * @param firstNamePatients first name of patient
     * @param lastNamePatients last name of patient
     * @param svnPatients SVN of patient
     * @param birthDatePatients birthdate of patient
     * @param streetPatients street of patient
     * @param streetNumberPatients street number of patient
     * @param postalCodePatients postal code of patient
     * @param cityPatients city of patient
     * @param idGender gender of patient
     * @param idNationality nationality of patient
     * @param idInsurance insurance of patient
     */
    public static void addPatient(String firstNamePatients, String lastNamePatients, long svnPatients, Date birthDatePatients,
                                  String streetPatients, int streetNumberPatients, int postalCodePatients, String cityPatients,
                                  int idGender, int idNationality, int idInsurance) {

        Thread workerThread = new Thread(() -> {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO patients (firstNamePatients, lastNamePatients, " +
                            "svnPatients, birthDatePatients, streetPatients, streetNumberPatients, postalCodePatients, cityPatients," +
                            "idGender, idNationality, idInsurance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                    setPreparedStatementPatient(firstNamePatients, lastNamePatients, svnPatients, (java.sql.Date) birthDatePatients,
                            streetPatients, streetNumberPatients, postalCodePatients, cityPatients, idGender, idNationality,
                            idInsurance, ps);

                    ps.close();
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
    }

    /**
     * edits patient from the database
     * @param id ID of patient
     * @param firstNamePatients first name of patient
     * @param lastNamePatients last name of patient
     * @param svnPatients SVN of patient
     * @param birthDatePatients birthdate of patient
     * @param streetPatients street of patient
     * @param streetNumberPatients street number of patient
     * @param postalCodePatients postal code of patient
     * @param cityPatients city of patient
     * @param idGender gender of patient
     * @param idNationality nationality of patient
     * @param idInsurance insurance of patient
     */
    public static void editPatient(int id, String firstNamePatients, String lastNamePatients, long svnPatients, Date birthDatePatients,
                                   String streetPatients, int streetNumberPatients, int postalCodePatients, String cityPatients,
                                   int idGender, int idNationality, int idInsurance) {

        Thread workerThread = new Thread(() -> {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("UPDATE patients SET firstNamePatients = ?, lastNamePatients = ?, " +
                            "svnPatients = ?, birthDatePatients = ?, streetPatients = ?, streetNumberPatients = ?, postalCodePatients = ?, " +
                            "cityPatients = ?, idGender = ?, idNationality = ?, idInsurance = ? WHERE idPatients = ?");

                    ps.setInt(12, id);
                    setPreparedStatementPatient(firstNamePatients, lastNamePatients, svnPatients, (java.sql.Date) birthDatePatients,
                            streetPatients, streetNumberPatients, postalCodePatients, cityPatients, idGender,
                            idNationality, idInsurance, ps);

                    ps.close();
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
    }

    /**
     * sets parameters of PreparedStatements of patient querys
     * @param firstNamePatients first name of patient
     * @param lastNamePatients last name of patient
     * @param svnPatients SVN of patient
     * @param birthDatePatients birthdate of patient
     * @param streetPatients street of patient
     * @param streetNumberPatients street number of patient
     * @param postalCodePatients postal code of patient
     * @param cityPatients city of patient
     * @param idGender gender of patient
     * @param idNationality nationality of patient
     * @param idInsurance insurance of patient
     * @param ps PreparedStatement to execute
     * @throws SQLException if error with database
     */
    private static void setPreparedStatementPatient(String firstNamePatients, String lastNamePatients, long svnPatients,
                                                    java.sql.Date birthDatePatients, String streetPatients, int streetNumberPatients,
                                                    int postalCodePatients, String cityPatients, int idGender, int idNationality,
                                                    int idInsurance, PreparedStatement ps) throws SQLException {
        ps.setString(1, firstNamePatients);
        ps.setString(2, lastNamePatients);
        ps.setLong(3, svnPatients);
        ps.setDate(4, birthDatePatients);
        ps.setString(5, streetPatients);
        ps.setInt(6, streetNumberPatients);
        ps.setInt(7, postalCodePatients);
        ps.setString(8, cityPatients);
        ps.setInt(9, idGender);
        ps.setInt(10, idNationality);
        ps.setInt(11, idInsurance);

        ps.executeUpdate();

        ps.close();
    }

    /**
     * when adding or editing patient gets saved in database
     * @param idPatients ID of patient
     * @param firstNamePatients first name of patient
     * @param lastNamePatients last name of patient
     * @param svnPatients SVN of patient
     * @param birthDatePatients birthdate of patient
     * @param streetPatients street of patient
     * @param streetNumberPatients street number of patient
     * @param postalCodePatients postal code of patient
     * @param cityPatients city of patient
     * @param cbGender combobox gender
     * @param cbNationality combobox nationality
     * @param cbInsurance combobox insurance
     * @return boolean if saving was successful
     */
    public static boolean savePatient(int idPatients, String firstNamePatients, String lastNamePatients, String svnPatients, String birthDatePatients,
                                      String streetPatients, String streetNumberPatients, String postalCodePatients, String cityPatients,
                                      JComboBox<Gender> cbGender, JComboBox<Nationality> cbNationality, JComboBox<Insurance> cbInsurance) {

        AtomicBoolean success = new AtomicBoolean(false);

        if (firstNamePatients.isEmpty() || lastNamePatients.isEmpty() || svnPatients.isEmpty() || birthDatePatients == null || streetPatients.isEmpty() ||
                streetNumberPatients.isEmpty() || postalCodePatients.isEmpty() || cityPatients.isEmpty() || cbGender.getSelectedItem() == null ||
                cbNationality.getSelectedItem() == null || cbInsurance.getSelectedItem() == null) {
            UIManager.put("OptionPane.background", SetLayout.cBackground);
            UIManager.put("Panel.background", SetLayout.cBackground);
            JOptionPane.showMessageDialog(null, "Please enter all information");
        } else {

            try {
                long SVN = Long.parseLong(svnPatients);
                java.sql.Date birthDate = java.sql.Date.valueOf(birthDatePatients);
                int streetNumber = Integer.parseInt(streetNumberPatients);
                int postalCode = Integer.parseInt(postalCodePatients);

                //Überprüfung Übereinstimmung von Geburtsdaten-Teil der SVN (ddMMyy) mit dem Geburtsdatum
               String[] birthDateParts = birthDatePatients.split("-"); //Geburtstdatum in yyyy-mm-dd zerlegen
                String birthDateFormatted = birthDateParts[2] + birthDateParts[1] + birthDateParts[0].substring(2); //Format ddMMyy
                String svnBirthDatePart = svnPatients.substring(svnPatients.length() - 6); //Letzte 6 Ziffern der SVN

                if (!svnBirthDatePart.equals(birthDateFormatted)) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "The birth date part of the SVN (ddMMyy) must match the birth date!");
                    return false;
                }

                // Worker-Thread für add und edit
                Thread workerThread = new Thread(() -> {
                    if (idPatients == 0) {
                        Patient.addPatient(firstNamePatients, lastNamePatients, SVN, birthDate, streetPatients,
                                streetNumber, postalCode, cityPatients, ((Gender) cbGender.getSelectedItem()).getGenderId(),
                                ((Nationality) cbNationality.getSelectedItem()).getNationalityId(),
                                ((Insurance) cbInsurance.getSelectedItem()).getInsuranceId());
                        UIManager.put("OptionPane.background", SetLayout.cBackground);
                        UIManager.put("Panel.background", SetLayout.cBackground);
                        JOptionPane.showMessageDialog(null, "Patient added successfully");
                        success.set(true);
                    } else {
                        Patient.editPatient(idPatients, firstNamePatients, lastNamePatients, SVN, birthDate, streetPatients,
                                streetNumber, postalCode, cityPatients, ((Gender) cbGender.getSelectedItem()).getGenderId(),
                                ((Nationality) cbNationality.getSelectedItem()).getNationalityId(),
                                ((Insurance) cbInsurance.getSelectedItem()).getInsuranceId());
                        UIManager.put("OptionPane.background", SetLayout.cBackground);
                        UIManager.put("Panel.background", SetLayout.cBackground);
                        JOptionPane.showMessageDialog(null, "Patient edited successfully");
                        success.set(true);
                    }
                });

                workerThread.start(); //Thread starten

            } catch (NumberFormatException e) {
                UIManager.put("OptionPane.background", SetLayout.cBackground);
                UIManager.put("Panel.background", SetLayout.cBackground);
                JOptionPane.showMessageDialog(null, "Please enter a valid number format in one of the fields! Don't use letters, special characters or space!");
            } catch (IllegalArgumentException e) {
                UIManager.put("OptionPane.background", SetLayout.cBackground);
                UIManager.put("Panel.background", SetLayout.cBackground);
                JOptionPane.showMessageDialog(null, "Please enter a valid birth date: use the format yyyy-mm-dd!");
            }
        }
        return success.get();
    }

    /**
     * deletes patient from database
     * @param id ID of patient to be deleted
     */
    public static void deletePatient(int id) {

        Thread workerThread = new Thread(() -> {
            Connection connection;

            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection == null) {
                    UIManager.put("OptionPane.background", SetLayout.cBackground);
                    UIManager.put("Panel.background", SetLayout.cBackground);
                    JOptionPane.showMessageDialog(null, "Could not connect to database");
                } else {
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM patients WHERE idPatients = ?");
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    ps.close();
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); //Thread starten
    }

    @Override
    public String toString() {
        return idPatients + firstNamePatients + lastNamePatients + svnPatients + birthDatePatients + streetPatients +
                streetNumberPatients + postalCodePatients + cityPatients + idGender + idNationality + idInsurance;
    }

    /**
     * gets patient ID
     * @return patient ID
     */
    public int getIdPatients() {
        return idPatients;
    }

    /**
     * sets patient ID
     * @param idPatients patient ID
     */
    public void setIdPatients(int idPatients) {
        this.idPatients = idPatients;
    }

    /**
     * gets first name of patient
     * @return first name of patient
     */
    public String getFirstNamePatients() {
        return firstNamePatients;
    }

    /**
     * sets first name of patient
     * @param firstNamePatients first name of patient
     */
    public void setFirstNamePatients(String firstNamePatients) {
        this.firstNamePatients = firstNamePatients;
    }

    /**
     * gets last name of patient
     * @return last name of patient
     */
    public String getLastNamePatients() {
        return lastNamePatients;
    }

    /**
     * sets last name of patient
     * @param lastNamePatients last name of patient
     */
    public void setLastNamePatients(String lastNamePatients) {
        this.lastNamePatients = lastNamePatients;
    }

    /**
     * gets SVN of patient
     * @return SVN of patient
     */
    public long getSvnPatients() {
        return svnPatients;
    }

    /**
     * sets SVN of patient
     * @param svnPatients SVN of patient
     */
    public void setSvnPatients(long svnPatients) {
        this.svnPatients = svnPatients;
    }

    /**
     * gets birthdate of patient
     * @return birthdate of patient
     */
    public Date getBirthDatePatients() {
        return birthDatePatients;
    }

    /**
     * sets birthdate of patient
     * @param birthDatePatients birthdate of patient
     */
    public void setBirthDatePatients(Date birthDatePatients) {
        this.birthDatePatients = birthDatePatients;
    }

    /**
     * gets street of patient
     * @return street of patient
     */
    public String getStreetPatients() {
        return streetPatients;
    }

    /**
     * sets street of patient
     * @param streetPatients street of patient
     */
    public void setStreetPatients(String streetPatients) {
        this.streetPatients = streetPatients;
    }

    /**
     * gets street number of patient
     * @return street number of patient
     */
    public int getStreetNumberPatients() {
        return streetNumberPatients;
    }

    /**
     * sets street number of patient
     * @param streetNumberPatients street number of patient
     */
    public void setStreetNumberPatients(int streetNumberPatients) {
        this.streetNumberPatients = streetNumberPatients;
    }

    /**
     * gets postal code of patient
     * @return postal code of patient
     */
    public int getPostalCodePatients() {
        return postalCodePatients;
    }

    /**
     * sets postal code of patient
     * @param postalCodePatients postal code of patient
     */
    public void setPostalCodePatients(int postalCodePatients) {
        this.postalCodePatients = postalCodePatients;
    }

    /**
     * gets city of patient
     * @return city of patient
     */
    public String getCityPatients() {
        return cityPatients;
    }

    /**
     * sets city of patient
     * @param cityPatients city of patient
     */
    public void setCityPatients(String cityPatients) {
        this.cityPatients = cityPatients;
    }

    /**
     * gets gender ID
     * @return gender ID
     */
    public int getIdGender() {
        return idGender;
    }

    /**
     * sets gender ID
     * @param idGender gender ID
     */
    public void setIdGender(int idGender) {
        this.idGender = idGender;
    }

    /**
     * gets nationality ID
     * @return nationality ID
     */
    public int getIdNationality() {
        return idNationality;
    }

    /**
     * sets nationality ID
     * @param idNationality nationality ID
     */
    public void setIdNationality(int idNationality) {
        this.idNationality = idNationality;
    }

    /**
     * gets insurance ID
     * @return insurance ID
     */
    public int getIdInsurance() {
        return idInsurance;
    }

    /**
     * sets insurance ID
     * @param idInsurance insurance ID
     */
    public void setIdInsurance(int idInsurance) {
        this.idInsurance = idInsurance;
    }

}
