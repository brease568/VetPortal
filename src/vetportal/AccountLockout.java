/**
 * File: AccountLockout.java
 * Date: April 16, 2020
 * @Author: Rebekah Qu 
 * Main POC: Rebekah Qu 
 * Purpose: Perform account lockout actions like incrementing failed logins, locking accounts, and checking lockout status
 *
 */
package vetportal;

import java.util.ArrayList;

public class AccountLockout {

    private static final Integer LOCKOUTDURATION = 300000; // 300000 miliseconds = 5 minutes
    private static ArrayList<FailedUser> users = new ArrayList<FailedUser>();

    // Add failed logins and lockout users with too many failures
    public static void addFailedLogin(String username) {
        Integer matchedIndex = -1;

        // Check to see if the user has failed previously
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                matchedIndex = i;
            }
        }
        // If the user previously failed
        if (matchedIndex > -1) {
            // If the user is not yet locked out
            if (users.get(matchedIndex).getLoginAttempts() < 4) {
                // Increment the failed login attempts
                users.get(matchedIndex).setLoginAttempts(users.get(matchedIndex).getLoginAttempts() + 1);
            // If the user should be locked out
            } else {
                // Set lockout time
                users.get(matchedIndex).setLockoutTime(System.currentTimeMillis());
            }
        // If the user has no previous failed attempts
        } else {
            users.add(new FailedUser(username, 1, 0));
        }
    }

    // Check to see if a user is locked out
    public static Boolean isLocked(String username) {
        // Loop through the existing users list
        for (int i = 0; i < users.size(); i++) {
            // If the current user is in the users list
            if (users.get(i).getUsername().equals(username)) {
                // If the user has been locked out
                if (users.get(i).getLockoutTime() > 0) {
                    // If the user was locked out, but the duration has expired
                    if (System.currentTimeMillis() - LOCKOUTDURATION > users.get(i).getLockoutTime()) {
                        // Reset the user state so they can log in again
                        users.get(i).setLockoutTime(0);
                        users.get(i).setLoginAttempts(0);
                        return false;
                    // The user is still locked out
                    } else {
                        return true;
                    }
                // The user has never been locked out
                } else {
                    return false;
                }
            }
        }
        // There are no users in the list
        return false;
    }
}
           

