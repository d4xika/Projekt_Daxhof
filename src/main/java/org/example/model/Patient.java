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
                            idInsurance, connection, ps);

                    ps.close();
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
    }

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
                            idNationality, idInsurance, connection, ps);

                    ps.close();
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        workerThread.start(); // Thread starten
    }

    private static void setPreparedStatementPatient(String firstNamePatients, String lastNamePatients, long svnPatients,
                                                    java.sql.Date birthDatePatients, String streetPatients, int streetNumberPatients,
                                                    int postalCodePatients, String cityPatients, int idGender, int idNationality,
                                                    int idInsurance, Connection connection, PreparedStatement ps) throws SQLException {
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

        connection.close();
        ps.close();
    }

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

                // Worker-Thread für die Patientenbearbeitung oder -einfügung
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

                workerThread.start(); // Thread starten

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

        workerThread.start(); // Thread starten
    }

    @Override
    public String toString() {
        return idPatients + firstNamePatients + lastNamePatients + svnPatients + birthDatePatients + streetPatients +
                streetNumberPatients + postalCodePatients + cityPatients + idGender + idNationality + idInsurance;
    }

    public int getIdPatients() {
        return idPatients;
    }

    public void setIdPatients(int idPatients) {
        this.idPatients = idPatients;
    }

    public String getFirstNamePatients() {
        return firstNamePatients;
    }

    public void setFirstNamePatients(String firstNamePatients) {
        this.firstNamePatients = firstNamePatients;
    }

    public String getLastNamePatients() {
        return lastNamePatients;
    }

    public void setLastNamePatients(String lastNamePatients) {
        this.lastNamePatients = lastNamePatients;
    }

    public long getSvnPatients() {
        return svnPatients;
    }

    public void setSvnPatients(long svnPatients) {
        this.svnPatients = svnPatients;
    }

    public Date getBirthDatePatients() {
        return birthDatePatients;
    }

    public void setBirthDatePatients(Date birthDatePatients) {
        this.birthDatePatients = birthDatePatients;
    }

    public String getStreetPatients() {
        return streetPatients;
    }

    public void setStreetPatients(String streetPatients) {
        this.streetPatients = streetPatients;
    }

    public int getStreetNumberPatients() {
        return streetNumberPatients;
    }

    public void setStreetNumberPatients(int streetNumberPatients) {
        this.streetNumberPatients = streetNumberPatients;
    }

    public int getPostalCodePatients() {
        return postalCodePatients;
    }

    public void setPostalCodePatients(int postalCodePatients) {
        this.postalCodePatients = postalCodePatients;
    }

    public String getCityPatients() {
        return cityPatients;
    }

    public void setCityPatients(String cityPatients) {
        this.cityPatients = cityPatients;
    }

    public int getIdGender() {
        return idGender;
    }

    public void setIdGender(int idGender) {
        this.idGender = idGender;
    }

    public int getIdNationality() {
        return idNationality;
    }

    public void setIdNationality(int idNationality) {
        this.idNationality = idNationality;
    }

    public int getIdInsurance() {
        return idInsurance;
    }

    public void setIdInsurance(int idInsurance) {
        this.idInsurance = idInsurance;
    }

}
