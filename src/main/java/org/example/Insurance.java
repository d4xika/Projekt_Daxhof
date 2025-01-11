package org.example;

public class Insurance {

    int insuranceId;
    String insuranceName;

    public Insurance(int insuranceId, String insuranceName) {
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
    }

    @Override
    public String toString() {
        return insuranceName;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
}
