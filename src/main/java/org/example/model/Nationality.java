package org.example.model;

public class Nationality {

    private int nationalityId;
    private String nationalityName;

    /**
     * constructor of Nationality
     * @param nationalityId nationality ID
     * @param nationalityName nationality name
     */
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

    /**
     * gets nationality ID
     * @return nationality ID
     */
    public int getNationalityId() {
        return nationalityId;
    }

    /**
     * sets nationality ID
     * @param nationalityId nationality ID
     */
    public void setNationalityId(int nationalityId) {
        this.nationalityId = nationalityId;
    }

    /**
     * get nationality name
     * @return nationality name
     */
    public String getNationalityName() {
        return nationalityName;
    }

    /**
     * set nationality name
     * @param nationalityName nationalityname
     */
    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    // Beispiel-Thread, um Nationalitäten parallel zu laden

    /**
     * example to load nationalities parallel
     * @param args
     */
    public static void main(String[] args) {
        // Erstellen von Threads, die gleichzeitig Nationalitäten laden
        Thread thread1 = new Thread(() -> {
            Nationality nationality1 = new Nationality(1, "German");
            System.out.println("Loaded nationality: " + nationality1);
        });

        Thread thread2 = new Thread(() -> {
            Nationality nationality2 = new Nationality(2, "American");
            System.out.println("Loaded nationality: " + nationality2);
        });

        // Starten der Threads
        thread1.start();
        thread2.start();

        // Du kannst auch einen weiteren Thread hinzufügen, der eine andere Aufgabe erledigt
        Thread thread3 = new Thread(() -> {
            System.out.println("Processing another task concurrently...");
        });

        thread3.start();
    }
}


/*public class Nationality {

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
}*/
