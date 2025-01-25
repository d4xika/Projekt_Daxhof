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

    // Beispiel für eine parallele Verarbeitung (als Thread)
    public static void main(String[] args) {
        // Beispiel-Thread zum Erstellen von mehreren Gender-Objekten parallel
        Thread thread = new Thread(() -> {
            Gender gender1 = new Gender(1, "Male");
            Gender gender2 = new Gender(2, "Female");
            System.out.println("Gender 1: " + gender1);
            System.out.println("Gender 2: " + gender2);
        });

        // Thread starten
        thread.start();

        // Ein weiterer Thread könnte gleichzeitig laufen, um etwas anderes zu tun.
        Thread anotherThread = new Thread(() -> {
            System.out.println("Doing some other task in parallel...");
        });

        anotherThread.start();
    }
}


/*public class Gender {

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
}*/
