package org.example;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {

    /**
     * Attribute des Patienten
     */
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

    private static final String url = "jdbc:mysql://localhost:3306/projekt_daxhof";
    private static final String user = "root";
    private static final String password = "mOrtible4827!#";


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

        Connection connection = null;
        List<Gender> genders = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT idGender, genderPatients FROM gender");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idGender");
                String gender = resultSet.getString("genderPatients");

                genders.add(new Gender(id, gender));
            }

            connection.close();
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genders;
    }

    public static Gender getGender(int id) {
        Connection connection = null;
        Gender g;

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT idGender, genderPatients FROM gender WHERE idGender = ?");
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            g = new Gender(resultSet.getInt("idGender"), resultSet.getString("genderPatients"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return g;
    }

    public static List<Nationality> getNationalityList() {
        Connection connection = null;
        List<Nationality> nationalities = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT idNationality, nationalityPatients FROM nationality");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idNationality");
                String nationality = resultSet.getString("nationalityPatients");

                nationalities.add(new Nationality(id, nationality));
            }

            connection.close();
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nationalities;
    }

    public static Nationality getNationality(int id) {
        Connection connection = null;
        Nationality n;

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT idNationality, nationalityPatients FROM nationality WHERE idNationality = ?");
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            n = new Nationality(resultSet.getInt("idNationality"), resultSet.getString("nationalityPatients"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }

    public static List<Insurance> getInsuranceList() {
        Connection connection = null;
        List<Insurance> insurances = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT idInsurance, insurancePatients FROM insurance");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idInsurance");
                String insurance = resultSet.getString("insurancePatients");

                insurances.add(new Insurance(id, insurance));
            }

            connection.close();
            ps.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insurances;
    }

    public static Insurance getInsurance(int id) {
        Connection connection = null;
        Insurance i;

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT idInsurance, insurancePatients FROM insurance WHERE idInsurance = ?");
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            i = new Insurance(resultSet.getInt("idInsurance"), resultSet.getString("insurancePatients"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public static List<Patient> getAllPatients() {

        List<Patient> patients;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM patients");
            patients = returnPatients(ps);

            connection.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patients;
    }

    public static Patient getPatient (int id) {

        Connection connection = null;
        Patient p;

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM patients WHERE idPatients = ?");
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            p = new Patient (resultSet.getInt("idPatients"),
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public static List<Patient> searchPatients(String namePatient) {

        Connection connection = null;
        List<Patient> patients;

        try {
            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM patients WHERE firstNamePatients LIKE ? " +
                    "OR lastNamePatients LIKE ? " +
                    "OR concat(firstNamePatients, ' ', lastNamePatients) LIKE ?");
            namePatient = "%" + namePatient + "%";
            ps.setString(1, namePatient);
            ps.setString(2, namePatient);
            ps.setString(3, namePatient);
            patients = returnPatients(ps);

            connection.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    private static List<Patient> returnPatients(PreparedStatement ps) throws SQLException {

        List<Patient> patients = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("idPatients");
            String firstName = resultSet.getString("firstNamePatients");
            String lastName = resultSet.getString("lastNamePatients");
            long svn = Long.parseLong(resultSet.getString("svnPatients").replace("\\s", ""));
            Date birthDate = resultSet.getDate("birthDatePatients");
            String street = resultSet.getString("streetPatients");
            int streetNumber = resultSet.getInt("streetNumberPatients");
            int postalCode = resultSet.getInt("postalCodePatients");
            String city = resultSet.getString("cityPatients");
            int idGender = resultSet.getInt("idGender");
            int idNationality = resultSet.getInt("idNationality");
            int idInsurance = resultSet.getInt("idInsurance");

            patients.add(new Patient(id, firstName, lastName, svn, birthDate, street, streetNumber, postalCode,
                    city, idGender, idNationality, idInsurance));
        }

        ps.close();
        resultSet.close();
        return patients;

    }

    public static void addPatient(String firstNamePatients, String lastNamePatients, long svnPatients, Date birthDatePatients,
                                  String streetPatients, int streetNumberPatients, int postalCodePatients, String cityPatients,
                                  int idGender, int idNationality, int idInsurance) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO patients (firstNamePatients, lastNamePatients, " +
                    "svnPatients, birthDatePatients, streetPatients, streetNumberPatients, postalCodePatients, cityPatients," +
                    "idGender, idNationality, idInsurance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            setPatient(firstNamePatients, lastNamePatients, svnPatients, (java.sql.Date) birthDatePatients, streetPatients, streetNumberPatients, postalCodePatients, cityPatients, idGender, idNationality, idInsurance, connection, ps);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void editPatient(int id, String firstNamePatients, String lastNamePatients, long svnPatients, Date birthDatePatients,
                                   String streetPatients, int streetNumberPatients, int postalCodePatients, String cityPatients,
                                   int idGender, int idNationality, int idInsurance) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement("UPDATE patients SET firstNamePatients = ?, lastNamePatients = ?, " +
                    "svnPatients = ?, birthDatePatients = ?, streetPatients = ?, streetNumberPatients = ?, postalCodePatients = ?, " +
                    "cityPatients = ?, idGender = ?, idNationality = ?, idInsurance = ? WHERE idPatients = ?");

            ps.setInt(12, id);
            setPatient(firstNamePatients, lastNamePatients, svnPatients, (java.sql.Date) birthDatePatients, streetPatients, streetNumberPatients, postalCodePatients, cityPatients, idGender, idNationality, idInsurance, connection, ps);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void setPatient(String firstNamePatients, String lastNamePatients, long svnPatients,
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

        int rows = ps.executeUpdate();

        connection.close();
        ps.close();
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