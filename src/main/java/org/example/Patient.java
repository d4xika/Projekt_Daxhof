package org.example;

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

    @Override
    public String toString() {
        return "Patient{" +
                "idPatients=" + idPatients +
                ", firstNamePatients='" + firstNamePatients + '\'' +
                ", lastNamePatients='" + lastNamePatients + '\'' +
                ", svnPatients=" + svnPatients +
                ", birthDatePatients=" + birthDatePatients +
                ", streetPatients='" + streetPatients + '\'' +
                ", streetNumberPatients=" + streetNumberPatients +
                ", postalCodePatients=" + postalCodePatients +
                ", cityPatients='" + cityPatients + '\'' +
                ", idGender=" + idGender +
                ", idNationality=" + idNationality +
                ", idInsurance=" + idInsurance +
                '}';
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

    static final String url = "jdbc:mysql://localhost:3306/projekt_daxhof";
    static final String user = "root";
    static final String password = "mOrtible4827!#";

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
            genders.forEach(System.out::println);

            connection.close();
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genders;
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
            nationalities.forEach(System.out::println);

            connection.close();
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nationalities;
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
            insurances.forEach(System.out::println);

            connection.close();
            ps.close();
            resultSet.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insurances;
    }

    public static void addPatient (String firstNamePatients, String lastNamePatients, long svnPatients, Date birthDatePatients,
        String streetPatients, int streetNumberPatients, int postalCodePatients, String cityPatients,
        int idGender, int idNationality, int idInsurance) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO patients (firstNamePatients, lastNamePatients, " +
                    "svnPatients, birthDatePatients, streetPatients, streetNumberPatients, postalCodePatients, cityPatients," +
                    "idGender, idNationality, idInsurance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, firstNamePatients);
            ps.setString(2, lastNamePatients);
            ps.setLong(3, svnPatients);
            ps.setDate(4, (java.sql.Date) birthDatePatients);
            ps.setString(5, streetPatients);
            ps.setInt(6, streetNumberPatients);
            ps.setInt(7, postalCodePatients);
            ps.setString(8, cityPatients);
            ps.setInt(9, idGender);
            ps.setInt(10, idNationality);
            ps.setInt(11, idInsurance);

            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " rows");

            connection.close();
            ps.close();

        }catch (Exception e) {
            throw new RuntimeException();
        }

    }

}