package org.example;

public class Gender {

    int genderId;
    String genderName;

    public Gender(int genderId, String genderName) {
        this.genderId = genderId;
        this.genderName = genderName;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "genderId=" + genderId +
                ", genderName='" + genderName + '\'' +
                '}';
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
