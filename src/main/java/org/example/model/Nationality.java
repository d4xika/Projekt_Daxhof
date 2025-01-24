package org.example.model;

public class Nationality {

    int nationalityId;
    String nationalityName;

    public Nationality(int nationalityId, String nationalityName) {
        this.nationalityId = nationalityId;
        this.nationalityName = nationalityName;
    }

    @Override
    public String toString() {
        return nationalityName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nationality nationality = (Nationality) obj;
        return this.nationalityId == nationality.nationalityId;
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
