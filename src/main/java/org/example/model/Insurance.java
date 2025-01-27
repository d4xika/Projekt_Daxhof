package org.example.model;

public class Insurance {

    private int insuranceId;
    private String insuranceName;

    /**
     * constructor of Insurance
     * @param insuranceId insurance ID
     * @param insuranceName insurance name
     */
    public Insurance(int insuranceId, String insuranceName) {
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
    }

    @Override
    public String toString() {
        return insuranceName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Insurance insurance = (Insurance) obj;
        return this.insuranceId == insurance.insuranceId;
    }

    /**
     * gets insurance ID
     * @return insurance ID
     */
    public int getInsuranceId() {
        return insuranceId;
    }

    /**
     * sets insurance ID
     * @param insuranceId insurance ID
     */
    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    /**
     * gets insurance name
     * @return insurance name
     */
    public String getInsuranceName() {
        return insuranceName;
    }

    /**
     * sets insurance name
     * @param insuranceName insurance name
     */
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    // Beispiel-Thread zur gleichzeitigen Erstellung von mehreren Insurance-Objekten

    /**
     * example for creating multiple insurance objects at the same time
     * @param args
     */
    public static void main(String[] args) {
        // Erstellen eines Threads zur Verarbeitung mehrerer Versicherungsobjekte
        Thread thread = new Thread(() -> {
            Insurance insurance1 = new Insurance(1, "Health Insurance");
            Insurance insurance2 = new Insurance(2, "Life Insurance");
            System.out.println("Insurance 1: " + insurance1);
            System.out.println("Insurance 2: " + insurance2);
        });

        // Thread starten
        thread.start();

        // Ein weiterer Thread könnte gleichzeitig laufen, um eine andere Aufgabe zu erledigen
        Thread anotherThread = new Thread(() -> {
            System.out.println("Processing another task concurrently...");
        });

        anotherThread.start();
    }
}


/*public class Insurance {

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Insurance insurance = (Insurance) obj;
        return this.insuranceId == insurance.insuranceId;
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
}*/
