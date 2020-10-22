/**
 * File: Database.java
 * Date: March 28, 2020
 * @Author: Brian Rease, Nour Debiat, Rebekah Qu
 * Main POC: Brian Rease 
 * Purpose: This class acts as the model for the SQLite database and contains the direct methods for specific interactions.
 */
package vetportal;

import org.apache.commons.codec.digest.DigestUtils;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteErrorCode;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private String errorMessage;

    public static final String DB_NAME = "VetPortal.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";

    public static final String TABLE_CLIENTS = "clients";
    public static final String COLUMN_CLIENT_ID = "client_id";
    public static final String COLUMN_CLIENT_FIRST_NAME = "first_name";
    public static final String COLUMN_CLIENT_LAST_NAME = "last_name";
    public static final String COLUMN_CLIENT_PHONE_NUMBER = "phone";
    public static final String COLUMN_CLIENT_EMAIL = "email";

    public static final String TABLE_PETS = "pets";
    public static final String COLUMN_PET_ID = "pet_id";
    public static final String COLUMN_PET_NAME = "name";
    public static final String COLUMN_PET_SPECIES = "species";
    public static final String COLUMN_PET_GENDER = "gender";
    public static final String COLUMN_PET_DATE_OF_BIRTH = "dob";
    public static final String COLUMN_PET_OWNER = "owner";

    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String COLUMN_APPOINTMENT_DATE = "date";
    public static final String COLUMN_APPOINTMENT_TIME = "time";
    public static final String COLUMN_APPOINTMENT_FORMATTED_TIME = "time(appointments.time)";
    public static final String COLUMN_APPOINTMENT_CLIENT = "client";
    public static final String COLUMN_APPOINTMENT_PET = "pet";
    public static final String COLUMN_APPOINTMENT_REASON = "reason";

    private Connection conn;
    private Statement statement;

    //This method attempts to open a connection with the database
    public boolean open() {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(CONNECTION_STRING, config.toProperties());
            return true;
        } catch (SQLException eS) {
            System.out.println("SQLException: " + eS.getMessage());
            return false;
        }
    } //end of open()

    //This method will close the connection to the database if it is not null
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException eS) {
            System.out.println("Couldn't close connection: " + eS.getMessage());
        }
    } //end of close()

    private void setErrorMessage(String message) {
        errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    //Method to get a client ID by phone number:
    public int getClientID(String phoneNumber) {
        String sql = "SELECT " + COLUMN_CLIENT_ID
            + " FROM " + TABLE_CLIENTS + " WHERE "
            + COLUMN_CLIENT_PHONE_NUMBER + "=?";
        
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phoneNumber);
            ResultSet idResult = pstmt.executeQuery();
            return idResult.getInt("client_id");
        } catch (SQLException e) {
            return -1;
        }
    } //end of getClientID()

    //Method to get a client's full name by ID number:
    public String getClientFullName(int id) {
        String sql = "SELECT " + COLUMN_CLIENT_FIRST_NAME
                + ", " + COLUMN_CLIENT_LAST_NAME
                + " FROM " + TABLE_CLIENTS + " WHERE "
                + COLUMN_CLIENT_ID + "=?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet idResult = pstmt.executeQuery();
            return idResult.getString("first_name") + " " + idResult.getString("last_name");
        } catch (SQLException e) {
            return null;
        }
    } //end of getClientID()

    public int getPetID(String name, String species, String gender, String dob) {
        String sql = "SELECT " + COLUMN_PET_ID
                + " FROM " + TABLE_PETS + " WHERE "
                + COLUMN_PET_NAME + "=?" + " AND "
                + COLUMN_PET_SPECIES + "=?" + " AND "
                + COLUMN_PET_GENDER + "=?" + " AND "
                + COLUMN_PET_DATE_OF_BIRTH + "=DATE(?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, species);
            pstmt.setString(3, gender);
            pstmt.setString(4, dob);
            ResultSet idResult = pstmt.executeQuery();
            return idResult.getInt("pet_id");
        } catch (SQLException e) {
            return -1;
        }
    }

    public int getPetOwner(String name, String species, String gender, String dob) {
        String sql = "SELECT " + COLUMN_PET_OWNER
                + " FROM " + TABLE_PETS + " WHERE "
                + COLUMN_PET_NAME + "=?" + " AND "
                + COLUMN_PET_SPECIES + "=?" + " AND "
                + COLUMN_PET_GENDER + "=?" + " AND "
                + COLUMN_PET_DATE_OF_BIRTH + "=DATE(?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, species);
            pstmt.setString(3, gender);
            pstmt.setString(4, dob);
            ResultSet idResult = pstmt.executeQuery();
            return idResult.getInt("owner");
        } catch (SQLException e) {
            return -1;
        }
    }

    public ArrayList<Pets> getPetsByOwnerID(int ownerID) {
            ArrayList<Pets> allPetsOwned;
            String sql = "SELECT * FROM " + TABLE_PETS + " WHERE "
                    + COLUMN_PET_OWNER + "=?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, ownerID);
                ResultSet petResults = pstmt.executeQuery();
                allPetsOwned = new ArrayList<>();
                while (petResults.next()) {
                    Pets pet = new Pets(petResults.getInt(COLUMN_PET_ID), petResults.getString(COLUMN_PET_NAME),
                                        petResults.getString(COLUMN_PET_SPECIES), petResults.getString(COLUMN_PET_GENDER),
                                        petResults.getString(COLUMN_PET_DATE_OF_BIRTH), petResults.getString(COLUMN_PET_OWNER));
                    allPetsOwned.add(pet);
                }
                pstmt.close();
                return allPetsOwned;
        } catch (SQLException e) {
            setErrorMessage("Could not find any pets.");
            ArrayList<Pets> emptyPets = new ArrayList<>();
            return emptyPets;
        }
    }

    public ArrayList<Appointments> getAppointmentsByClientID(int clientID) {
        ArrayList<Appointments> allScheduledAppointments;
        String sql = "SELECT " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE
                + ", time(" + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_TIME
                + "), " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_CLIENT
                + ", " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_PET
                + ", " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_REASON
                + ", " + TABLE_CLIENTS + "." + COLUMN_CLIENT_FIRST_NAME
                + ", " + TABLE_CLIENTS + "." + COLUMN_CLIENT_LAST_NAME
                + ", " + TABLE_PETS + "." + COLUMN_PET_NAME
                + ", " + TABLE_PETS + "." + COLUMN_PET_SPECIES
                + " FROM " + TABLE_APPOINTMENTS + " INNER JOIN " + TABLE_CLIENTS + " ON " + TABLE_APPOINTMENTS
                + "." + COLUMN_APPOINTMENT_CLIENT + "=" + TABLE_CLIENTS + "." + COLUMN_CLIENT_ID + " INNER JOIN "
                + TABLE_PETS + " ON " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_PET + "=" + TABLE_PETS
                + "." + COLUMN_PET_ID + " WHERE " + COLUMN_APPOINTMENT_CLIENT + "=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientID);
            ResultSet appointmentResults = pstmt.executeQuery();
            allScheduledAppointments = new ArrayList<>();
            while (appointmentResults.next()) {
                String appointmentClient = appointmentResults.getString(COLUMN_CLIENT_FIRST_NAME) + " " + appointmentResults.getString(COLUMN_CLIENT_LAST_NAME);
                String appointmentPet = appointmentResults.getString(COLUMN_PET_NAME) + " (" + appointmentResults.getString(COLUMN_PET_SPECIES) + ")";
                Appointments appointment = new Appointments(appointmentResults.getString(COLUMN_APPOINTMENT_DATE), appointmentResults.getString(COLUMN_APPOINTMENT_FORMATTED_TIME),
                        appointmentClient, appointmentPet,
                        appointmentResults.getString(COLUMN_APPOINTMENT_REASON));
                allScheduledAppointments.add(appointment);
            }
            pstmt.close();
            return allScheduledAppointments;
        } catch (SQLException e) {
            setErrorMessage("Could not find any appointments.");
            ArrayList<Appointments> emptyAppointments = new ArrayList<>();
            return emptyAppointments;
        }
    }


    /*
    This method uses the Apache commons codec to hash a given 'password' String with sha256.
    The hash is then compared to a password hash in the sqlite database
     */
    public boolean authenticate(String username, String password) {
        // Create a hash of the password
        String passwordHash = DigestUtils.sha256Hex(password);
        // Get the password for the user in order to compare passwords
        String getUser = "SELECT " + COLUMN_USER_PASSWORD
            + " FROM " + TABLE_USERS + " WHERE "
            + COLUMN_USER_NAME + "=?";      
        try(PreparedStatement pstmt = conn.prepareStatement(getUser)){
            pstmt.setString(1, username);
            ResultSet matchedUser = pstmt.executeQuery();
            // If the hashed password for the selected user matches
            if(passwordHash.equals(matchedUser.getString(COLUMN_USER_PASSWORD))) {                
                pstmt.close();
                return true;
            // Otherwise, authentication failed
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    } //end of authenticate()

    // This method inserts a new client into the clients table in the database
    public boolean insertClient(String firstName, String lastName, String phoneNumber, String email) {
        String sql = "INSERT INTO " + TABLE_CLIENTS
            + " (" + COLUMN_CLIENT_FIRST_NAME
            + ", " + COLUMN_CLIENT_LAST_NAME
            + ", " + COLUMN_CLIENT_PHONE_NUMBER
            + ", " + COLUMN_CLIENT_EMAIL + ") VALUES(?,?,?,?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            if (SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE.code == 2067) {
                setErrorMessage("The phone number you entered is already in the database!");
            } else {
                setErrorMessage("Unable to create new client.");
            }
            return false;
        }
    } //end of insertClient()

    // This method deletes an existing client from the clients table in the database
    public boolean deleteClient(String phoneNumber) {
        String sql = "DELETE FROM " + TABLE_CLIENTS
            + " WHERE " + COLUMN_CLIENT_PHONE_NUMBER + "=?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phoneNumber);
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            setErrorMessage("Unable to delete client.");
            return false;
        }
    } //end of deleteClient()

    // This method selects all the clients from the clients table in the database and returns them as a list
    public ArrayList<Clients> selectAllClients() {
        try {
            statement = conn.createStatement();
            ArrayList<Clients> allClients;
            try (ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CLIENTS + " ORDER BY " + TABLE_CLIENTS + "." + COLUMN_CLIENT_LAST_NAME + " ASC")) {
                allClients = new ArrayList<>();
                while (results.next()) {
                    Clients client = new Clients(results.getInt(COLUMN_CLIENT_ID), results.getString(COLUMN_CLIENT_FIRST_NAME),
                            results.getString(COLUMN_CLIENT_LAST_NAME), results.getString(COLUMN_CLIENT_PHONE_NUMBER),
                            results.getString(COLUMN_CLIENT_EMAIL));
                    allClients.add(client);
                }
            }
            statement.close();
            return allClients;
        } catch (SQLException e) {
            setErrorMessage("Could not find any clients.");
            return null;
        }
    } //end of selectAllClients()

    // This method updates a client with edited information in the clients table in the database
    public boolean updateClient(int clientID, String firstName, String lastName, String phoneNumber, String email) {
        String sql = "UPDATE " + TABLE_CLIENTS
            + " SET " + COLUMN_CLIENT_FIRST_NAME + "=?, "
            + COLUMN_CLIENT_LAST_NAME + "=?, "
            + COLUMN_CLIENT_PHONE_NUMBER + "=?, "
            + COLUMN_CLIENT_EMAIL + "=? "
            + "WHERE " + COLUMN_CLIENT_ID + "=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, email);
            pstmt.setInt(5, clientID);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e)  {
            setErrorMessage("Unable to update client.");
            return false;
        }
    } //end of updateClient()

    // This method inserts a new pet into the pets table in the database
    public boolean insertPet(String name, String species, String gender, String dob, int owner) {
        String sql = "INSERT INTO " + TABLE_PETS
                + " (" + COLUMN_PET_NAME
                + ", " + COLUMN_PET_SPECIES
                + ", " + COLUMN_PET_GENDER
                + ", " + COLUMN_PET_DATE_OF_BIRTH
                + ", " + COLUMN_PET_OWNER + ") VALUES(?,?,?,DATE(?),?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, species);
            pstmt.setString(3, gender);
            pstmt.setString(4, dob);
            pstmt.setInt(5, owner);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            setErrorMessage("Unable to create new pet.");
            return false;
        }
    } //end of insertPet()

    // This method deletes an existing pet from the pets table in the database
    public boolean deletePet(String name, String species, String gender, String dob) {
        String sql = "DELETE FROM " + TABLE_PETS
                + " WHERE "
                + COLUMN_PET_NAME + "=?" + " AND "
                + COLUMN_PET_SPECIES + "=?" + " AND "
                + COLUMN_PET_GENDER + "=?" + " AND "
                + COLUMN_PET_DATE_OF_BIRTH + "=DATE(?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, species);
            pstmt.setString(3, gender);
            pstmt.setString(4, dob);
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            setErrorMessage("Unable to delete pet.");
            return false;
        }
    } //end of deletePet()

    // This method selects all the pets from the pets table in the database and returns them as a list
    public ArrayList<Pets> selectAllPets() {
        try {
            statement = conn.createStatement();
            ArrayList<Pets> allPets;
            // Select all pets and join the client last name on client id / owner id to get the owner last name
            try (ResultSet results = statement.executeQuery("SELECT " + TABLE_PETS + ".*, " + TABLE_CLIENTS + "." + COLUMN_CLIENT_LAST_NAME 
                    + " FROM " + TABLE_PETS + " INNER JOIN " + TABLE_CLIENTS + " ON " + TABLE_PETS + "." + COLUMN_PET_OWNER 
                    + "=" + TABLE_CLIENTS + "." + COLUMN_CLIENT_ID + " ORDER BY " + TABLE_PETS + "." + COLUMN_PET_NAME + " ASC")) {
                allPets = new ArrayList<>();
                while (results.next()) {
                    Pets pet = new Pets(results.getInt(COLUMN_PET_ID), results.getString(COLUMN_PET_NAME),
                            results.getString(COLUMN_PET_SPECIES), results.getString(COLUMN_PET_GENDER),
                            results.getString(COLUMN_PET_DATE_OF_BIRTH), results.getString(COLUMN_CLIENT_LAST_NAME));
                    allPets.add(pet);
                }
            }
            statement.close();
            return allPets;
        } catch (SQLException e) {
            setErrorMessage("Could not find any pets.");
            return null;
        }
    } //end of selectAllPets()

    // This method updates a pet with edited information in the pet table in the database
    public boolean updatePet(int petID, String name, String species, String gender, String dob) {
        String sql = "UPDATE " + TABLE_PETS
                + " SET " + COLUMN_PET_NAME + "=?, "
                + COLUMN_PET_SPECIES + "=?, "
                + COLUMN_PET_GENDER + "=?, "
                + COLUMN_PET_DATE_OF_BIRTH + "=? "
                + "WHERE " + COLUMN_PET_ID + "=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, species);
            pstmt.setString(3, gender);
            pstmt.setString(4, dob);
            pstmt.setInt(5, petID);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e)  {
            setErrorMessage("Unable to update pet.");
            return false;
        }
    } //end of updatePet()

    // This method inserts a new appointment into the appointments table in the database
    public boolean insertAppointment(String date, String time, int client, int pet, String reason) {
        String datetime = date + " " + time;
        String sql = "INSERT INTO " + TABLE_APPOINTMENTS
                + " (" + COLUMN_APPOINTMENT_DATE
                + ", " + COLUMN_APPOINTMENT_TIME
                + ", " + COLUMN_APPOINTMENT_CLIENT
                + ", " + COLUMN_APPOINTMENT_PET
                + ", " + COLUMN_APPOINTMENT_REASON + ") VALUES(date(?),datetime(?),?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, datetime);
            pstmt.setInt(3, client);
            pstmt.setInt(4, pet);
            pstmt.setString(5, reason);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            if (SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE.code == 2067) {
                setErrorMessage("Appointment date/time is already taken!");
            } else {
                setErrorMessage("Unable to create new appointment.");
            }
            return false;
        }
    } //end of insertAppointment()

    // This method deletes an existing appointment from the appointments table in the database
    public boolean deleteAppointment(String date, String time) {
        String datetime = date + " " + time;
        String sql = "DELETE FROM " + TABLE_APPOINTMENTS
                + " WHERE "
                + COLUMN_APPOINTMENT_DATE + "=date(?)" + " AND "
                + COLUMN_APPOINTMENT_TIME + "=datetime(?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, datetime);
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            setErrorMessage("Unable to delete appointment.");
            return false;
        }
    } //end of deleteAppointment()

    // This method selects all the appointments from the appointments table that occur today or later and returns them as a list
    public ArrayList<Appointments> selectUpcomingAppointments() {
        try {
            statement = conn.createStatement();
            ArrayList<Appointments> upcomingAppointments;
            //SELECT appointments.date, time(appointments.time), appointments.client, appointments.pet, appointments.reason, clients.first_name, clients.last_name, pets.name,
            // pets.species FROM appointments INNER JOIN clients ON appointments.client=clients.client_id INNER JOIN pets ON appointments.pet=pets.pet_id WHERE appointments.date >= getdate();
            try (ResultSet results = statement.executeQuery("SELECT " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE
                                                                + ", time(" + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_TIME
                                                                + "), " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_CLIENT
                                                                + ", " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_PET
                                                                + ", " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_REASON
                                                                + ", " + TABLE_CLIENTS + "." + COLUMN_CLIENT_FIRST_NAME
                                                                + ", " + TABLE_CLIENTS + "." + COLUMN_CLIENT_LAST_NAME
                                                                + ", " + TABLE_PETS + "." + COLUMN_PET_NAME
                                                                + ", " + TABLE_PETS + "." + COLUMN_PET_SPECIES
                    + " FROM " + TABLE_APPOINTMENTS + " INNER JOIN " + TABLE_CLIENTS + " ON " + TABLE_APPOINTMENTS
                    + "." + COLUMN_APPOINTMENT_CLIENT + "=" + TABLE_CLIENTS + "." + COLUMN_CLIENT_ID + " INNER JOIN "
                    + TABLE_PETS + " ON " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_PET + "=" + TABLE_PETS
                    + "." + COLUMN_PET_ID + " WHERE " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE + " >= DATE('now','localtime') ORDER BY " +
                    TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE + " ASC, " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_TIME + " ASC")) {
                upcomingAppointments = new ArrayList<>();
                while (results.next()) {
                    String appointmentClient = results.getString(COLUMN_CLIENT_FIRST_NAME) + " " + results.getString(COLUMN_CLIENT_LAST_NAME);
                    String appointmentPet = results.getString(COLUMN_PET_NAME) + " (" + results.getString(COLUMN_PET_SPECIES) + ")";
                    Appointments appointment = new Appointments(results.getString(COLUMN_APPOINTMENT_DATE), results.getString(COLUMN_APPOINTMENT_FORMATTED_TIME),
                            appointmentClient, appointmentPet,
                            results.getString(COLUMN_APPOINTMENT_REASON));
                    upcomingAppointments.add(appointment);
                }
            }
            statement.close();
            return upcomingAppointments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            setErrorMessage("Could not find any appointments.");
            return null;
        }
    } //end of selectUpcomingAppointments()

    // This method updates an appointment with edited information in the appointment table in the database
    public boolean updateAppointment(String currentDate, String currentTime, String updatedDate, String updatedTime, String updatedReason) {
        String currentTimeDate = currentDate + " " + currentTime;
        String updatedTimeDate = updatedDate + " " + updatedTime;

        String sql = "UPDATE " + TABLE_APPOINTMENTS
                + " SET " + COLUMN_APPOINTMENT_DATE + "=date(?), "
                + COLUMN_APPOINTMENT_TIME + "=datetime(?), "
                + COLUMN_APPOINTMENT_REASON + "=? "
                + "WHERE " + COLUMN_APPOINTMENT_DATE + "=date(?) "
                + "AND " + COLUMN_APPOINTMENT_TIME + "=datetime(?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedDate);
            pstmt.setString(2, updatedTimeDate);
            pstmt.setString(3, updatedReason);
            pstmt.setString(4, currentDate);
            pstmt.setString(5, currentTimeDate);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            if (SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE.code == 2067) {
                setErrorMessage("Appointment date/time is already taken!");
            } else {
                setErrorMessage("Unable to update appointment.");
            }
            return false;
        }
    } //end of updateAppointment()

} //end of Database
