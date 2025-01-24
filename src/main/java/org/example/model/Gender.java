package org.example.model;

public class Gender {

    int genderId;
    String genderName;

    public Gender(int genderId, String genderName) {
        this.genderId = genderId;
        this.genderName = genderName;
    }

    @Override
    public String toString() {
        return genderName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Gender gender = (Gender) obj;
        return this.genderId == gender.genderId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
