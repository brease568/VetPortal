/**
 * File: Search.java
 * Date: April 20, 2020
 * @Author: Rebekah Qu
 * Purpose: This class is used to filter table data based on passed search criteria
 */
package vetportal;

import java.util.List;
import java.util.stream.Collectors;

public class Search {   
    
    /** 
     * Search for appointments based on any of the three criteria, missing criteria is passed an empty string
     * @param appointments: the list of appointments to be searched through
     * @param date: the appointment date to be searched
     * @param petOwner: the full name of the client to be searched
     * @param petName: the name of the pet to be searched
     * @return matches: the list of appointments that were matched
     */
    public static List<Appointments> searchAppointments(List<Appointments> appointments, String date, String petOwner, String petName) {        
        // Search on Date, Owner Name, and Pet Name
        if (!"".equals(date) && !"".equals(petName) && !"".equals(petOwner)) {
            List<Appointments> matches = appointments.stream()
                    .filter(a -> a.getAptDate().equals(date) && a.getPetName().equalsIgnoreCase(petName) && a.getClientLastName().equalsIgnoreCase(petOwner))
                    .collect(Collectors.toList());
            return matches;
            
        // Search on Date and Owner Name
        } else if (!"".equals(date) && "".equals(petName) && !"".equals(petOwner)) {
            List<Appointments> matches = appointments.stream()
                    .filter(a -> a.getAptDate().equals(date) && a.getClientLastName().equalsIgnoreCase(petOwner))
                    .collect(Collectors.toList());
            return matches;
        
        // Search on Date and Pet Name
        } else if (!"".equals(date) && !"".equals(petName) && "".equals(petOwner)) {
            List<Appointments> matches = appointments.stream()
                    .filter(a -> a.getAptDate().equals(date) && a.getPetName().equalsIgnoreCase(petName))
                    .collect(Collectors.toList());
            return matches;
        
        // Search on Pet Name and Owner Name      
        } else if ("".equals(date) && !"".equals(petName) && !"".equals(petOwner)) {
            List<Appointments> matches = appointments.stream()
                    .filter(a -> a.getPetName().equalsIgnoreCase(petName) && a.getClientLastName().equalsIgnoreCase(petOwner))
                    .collect(Collectors.toList());
            return matches;
        
        // Search on Date only
        } else if (!"".equals(date) && "".equals(petName) && "".equals(petOwner)) {
            List<Appointments> matches = appointments.stream()
                    .filter(a -> a.getAptDate().equals(date))
                    .collect(Collectors.toList());
            return matches; 
        
        // Search on Pet Name only    
        } else if ("".equals(date) && !"".equals(petName) && "".equals(petOwner)) {
            List<Appointments> matches = appointments.stream()
                    .filter(a -> a.getPetName().equalsIgnoreCase(petName))
                    .collect(Collectors.toList());
            return matches; 
        
        // Search on Owner Name only        
        } else if ("".equals(date) && "".equals(petName) && !"".equals(petOwner)){
            List<Appointments> matches = appointments.stream()
                    .filter(a -> a.getClientLastName().equalsIgnoreCase(petOwner))
                    .collect(Collectors.toList());
            return matches; 
        }         
        return null;
    }
    

    /**
     * Search for pets based on any of the two criteria, missing criteria is passed an empty string
     * @param pets: the list of pets to be searched through
     * @param petName: the name of the pet to be searched
     * @param petOwner: the last name of the client to be searched
     * @return matches: the list of pets that were matched
     */
    public static List<Pets> searchPets(List<Pets> pets, String petName, String petOwner) {       
        // Search on Pet Name and Owner Name      
        if (!"".equals(petName) && !"".equals(petOwner)) {
            List<Pets> matches = pets.stream()
                    .filter(p -> p.getPetName().equalsIgnoreCase(petName) && p.getPetOwner().equalsIgnoreCase(petOwner))
                    .collect(Collectors.toList());
            return matches;
        
        // Search on Pet Name only    
        } else if (!"".equals(petName) && "".equals(petOwner)) {
            List<Pets> matches = pets.stream()
                    .filter(p -> p.getPetName().equalsIgnoreCase(petName))
                    .collect(Collectors.toList());
            return matches; 
        
        // Search on Owner Name only        
        } else if ("".equals(petName) && !"".equals(petOwner)){
            List<Pets> matches = pets.stream()
                    .filter(p -> p.getPetOwner().equalsIgnoreCase(petOwner))
                    .collect(Collectors.toList());
            return matches; 
        }         
        return null;
    }

    /**
     * Search for clients based on any of the three criteria, missing criteria is passed as an empty string
     * @param clients: the list of clients to be searched through
     * @param lastName: the last name of the client to be searched
     * @param email: the email to be searched
     * @param phone: the phone number to be searched
     * @return matches: the list of clients that were matched
     */
    public static List<Clients> searchClients(List<Clients> clients, String lastName, String email, String phone) {       
        // Search on Last Name, Email, and Phone Number
        if (!"".equals(lastName) && !"".equals(email) && !"(   )    -    ".equals(phone)) {
            List<Clients> matches = clients.stream()
                .filter(c -> c.getClientLastName().equalsIgnoreCase(lastName) && c.getClientEmail().equalsIgnoreCase(email) && c.getClientPhoneNumber().equalsIgnoreCase(phone))
                .collect(Collectors.toList());       
            return matches;
        // Search on Last Name and Email only
        } else if (!"".equals(lastName) && !"".equals(email) && "(   )    -    ".equals(phone)) {
            List<Clients> matches = clients.stream()
                .filter(c -> c.getClientLastName().equalsIgnoreCase(lastName) && c.getClientEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());       
            return matches;
        // Search on Last Name and Phone Only
        } else if (!"".equals(lastName) && "".equals(email) && !"(   )    -    ".equals(phone)) {
            List<Clients> matches = clients.stream()
                .filter(c -> c.getClientLastName().equalsIgnoreCase(lastName) && c.getClientPhoneNumber().equalsIgnoreCase(phone))
                .collect(Collectors.toList());       
            return matches;
        // Search on Email and Phone Only
        } else if ("".equals(lastName) && !"".equals(email) && !"(   )    -    ".equals(phone)) {
            List<Clients> matches = clients.stream()
                .filter(c -> c.getClientEmail().equalsIgnoreCase(email) && c.getClientPhoneNumber().equalsIgnoreCase(phone))
                .collect(Collectors.toList());       
            return matches;
        // Search on Last Name Only
        } else if (!"".equals(lastName) && "".equals(email) && "(   )    -    ".equals(phone)) {
            List<Clients> matches = clients.stream()
                .filter(c -> c.getClientLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());       
            return matches;
        // Search on Email Only
        } else if ("".equals(lastName) && !"".equals(email) && "(   )    -    ".equals(phone)) {
            List<Clients> matches = clients.stream()
                .filter(c -> c.getClientEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());       
            return matches;
        // Search on Phone Number Only
        } else if ("".equals(lastName) && "".equals(email) && !"(   )    -    ".equals(phone)) {
            List<Clients> matches = clients.stream()
                .filter(c -> c.getClientPhoneNumber().equalsIgnoreCase(phone))
                .collect(Collectors.toList());       
            return matches;
        }
    return null;
    }
}
