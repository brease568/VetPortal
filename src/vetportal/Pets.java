/**
 * File: Pets.java
 * Date: March 26, 2020
 * @Author: Brian Rease, Nour Debiat
 * Main POC: Brian Rease
 * Purpose: This class is used to create new Pets.
 */
package vetportal;

public class Pets {

    private int petID;
    private String petName;
    private String petSpecies;
    private String petGender;
    private String petDateOfBirth;
    private String petOwner;

    public Pets(int petID, String petName, String petSpecies, String petGender, String petDateOfBirth, String petOwner) {
        this.petID = petID;
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.petGender = petGender;
        this.petDateOfBirth = petDateOfBirth;
        this.petOwner = petOwner;
    } //end of constructor

    public int getPetID() {
        return petID;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetSpecies() {
        return petSpecies;
    }

    public String getPetGender() {
        return petGender;
    }

    public String getPetDateOfBirth() {
        return petDateOfBirth;
    }

    public String getPetOwner() {
        return petOwner;
    }
} //end of Pets
