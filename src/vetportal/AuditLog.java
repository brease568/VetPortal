/**
 * File: AuditLog.java
 * Date: April 16, 2020
 * @Author: Rebekah Qu
 * Main POC:  Rebekah Qu
 * Purpose: This class takes events triggered in the application and logs them
 *
 */
package vetportal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class AuditLog {
     public static void logWriter(String event, String info) {
        // Try-with-resources write to a file with append being true, so the log retains old events
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("auditLog.txt", true))) {
            // Depending on the event, write a different log to the log file
            switch(event){
                case ("successfulLogin"): 
                    writer.write(Instant.now() + " - Type: Login - Location: VetPortal Login " 
                        + "- Outcome: Successful Login - " + info + "\n");
                    break;
                case ("failedLogin"):
                    writer.write(Instant.now() + " - Type: Login - Location: VetPortal Login " 
                        + "- Outcome: Failed Login - " + info + "\n");
                    break;    
                case ("accountLockout"):
                    writer.write(Instant.now() + " - Type: Login - Location: VetPortal Login " 
                        + "- Outcome: Account Lockout - " + info + "\n");
                    break; 
                case ("successfulClientAdd"):
                    writer.write(Instant.now() + " - Type: Add Client - Location: VetPortal New Client " 
                        + "- Outcome: Successful New Client Creation - " + info + "\n");
                    break;
                case ("successfulClientEdit"):
                    writer.write(Instant.now() + " - Type: Edit Client - Location: VetPortal Edit Client " 
                        + "- Outcome: Successful Client Edit - " + info + "\n");
                    break;
                case ("successfulClientDelete"):
                    writer.write(Instant.now() + " - Type: Delete Client - Location: VetPortal Clients Dashboard " 
                        + "- Outcome: Successful Client Delete - " + info + "\n");
                    break;
                case ("successfulPetAdd"):
                    writer.write(Instant.now() + " - Type: Add Pet - Location: VetPortal New Pet " 
                        + "- Outcome: Successful New Pet Creation - " + info + "\n");
                    break;
                case ("successfulPetEdit"):
                    writer.write(Instant.now() + " - Type: Edit Pet - Location: VetPortal Edit Pet " 
                        + "- Outcome: Successful Pet Edit - " + info + "\n");
                    break;
                case ("successfulPetDelete"):
                    writer.write(Instant.now() + " - Type: Delete Pet - Location: VetPortal Pets Dashboard " 
                        + "- Outcome: Successful Pet Delete - " + info + "\n");
                    break;
                case ("successfulAppointmentAdd"):
                    writer.write(Instant.now() + " - Type: Add Appointment - Location: VetPortal New Appointment " 
                        + "- Outcome: Successful New Appointment Creation - " + info + "\n");
                    break;
                case ("successfulAppointmentEdit"):
                    writer.write(Instant.now() + " - Type: Edit Appointment - Location: VetPortal Edit Appointment " 
                        + "- Outcome: Successful Appointment Edit - " + info + "\n");
                    break;
                case ("successfulAppointmentDelete"):
                    writer.write(Instant.now() + " - Type: Delete Appointment - Location: VetPortal Appointments Dashboard " 
                        + "- Outcome: Successful Appointment Delete - " + info + "\n");
                    break;
            }            
        // Catch errors
        } catch (IOException io) {
            System.out.println("File IO Exception");
        }
    }
}
