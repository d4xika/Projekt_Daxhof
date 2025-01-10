package org.example;

public class Nationality {

    int nationalityId;
    String nationalityName;

    public Nationality(int nationalityId, String nationalityName) {
        this.nationalityId = nationalityId;
        this.nationalityName = nationalityName;
    }

    @Override
    public String toString() {
        return "Nationality{" +
                "nationalityId=" + nationalityId +
                ", nationalityName='" + nationalityName + '\'' +
                '}';
    }

    public int getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(int nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }
}
