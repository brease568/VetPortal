/**
 * File: Appointments.java
 * Date: April 16, 2020
 * @Author: Brian Rease, Nour Debiat
 * Main POC: Brian Rease
 * Purpose: This class is used to create new Appointments.
 */
package vetportal;

public class Appointments {

    private String aptDate;
    private String aptTime;
    private String client;
    private String pet;
    private String aptReason;

    public Appointments(String aptDate, String aptTime, String client, String pet, String aptReason) {
        this.aptDate = aptDate;
        this.aptTime = aptTime;
        this.client = client;
        this.pet = pet;
        this.aptReason = aptReason;
    } //end of constructor

    public String getAptDate() {
        return aptDate;
    }

    public String getAptTime() {
        return aptTime;
    }

    // Returns client's first and last names together
    public String getClient() {
        return client;
    }
    
    // Split the client name and return only the last name
    public String getClientLastName() {
        String[] splitClientInfo = client.split("\\s");
        return splitClientInfo[1];
    }
    
    // Returns the pet's name and species as Name (Species)
    public String getPet() {
        return pet;
    }
    
    // Split the pet name from the species and return only the name
    public String getPetName() {
        String[] splitPetInfo = pet.split("\\s");
        return splitPetInfo[0];
    }

    public String getAptReason() {
        return aptReason;
    }
} //end of Appointments
